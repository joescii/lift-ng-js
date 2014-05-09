import dispatch.Future
import sbt._
import sbt.Keys._
import scala.concurrent.Await
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global

object LiftNgJsBuild extends Build {
  val liftVersion = SettingKey[String]("liftVersion", "Full version number of the Lift Web Framework")
  val liftEdition = SettingKey[String]("liftEdition", "Lift Edition (short version number to append to artifact name)")
  val baseUrl = SettingKey[String]("baseUrl", "The base URL for fetching angular code")
  val zipUrl  = SettingKey[String]("zipUrl", "The URL for the zip file containing the angular code")

  val fetch = TaskKey[Seq[File]]("fetch", "Fetch the angular modules for the configured version from https://code.angularjs.org")

  private def cleanVersion(v:String) = if(v.endsWith("-SNAPSHOT")) v.substring(0, v.length-9) else v

  val defaultZipUrl = zipUrl <<= (version, baseUrl) { (v, url) =>
    val ver = cleanVersion(v)
    val base = if(url.endsWith("/")) url else url + "/"
    s"$base$ver/angular-$ver.zip"
  }

  val defaultFetch = fetch <<= (version, zipUrl, resourceManaged in Compile, streams) map { (ver, zip, rsrc, s) =>
    val log = s.log

    def fetchZip(zipUrl:String): Future[Array[Byte]] = {
      import dispatch._
      Http(url(zipUrl) OK as.Bytes)
    }

    def unpackZip(bytes:Future[Array[Byte]], rsrc:File):Future[Seq[File]] = {
      import java.io._, java.util.zip._

      val zipRoot = "angular-"+cleanVersion(ver)+"/"

      bytes.map { b =>
        val s = new ZipInputStream(new ByteArrayInputStream(b))
        val entries = Stream.continually(s.getNextEntry).takeWhile(_!=null)
        for {
          e <- entries
          if !e.isDirectory
          if e.getName.split('/').length == 2
        } yield {
          val f = new File(rsrc, e.getName.substring(zipRoot.length))
          log.debug("Creating file "+f.getAbsolutePath)
          f.createNewFile()
          f
        }

      }
    }

    log.info(s"Fetching $zip")
    val root = rsrc / "toserve" / "net" / "liftmodules" / "ng" / "js"
    root.mkdirs()
    val f = unpackZip(fetchZip(zip), root)
    Await.result(f, 60.seconds)

  }

  val requireFetch = resourceGenerators in Compile <+= fetch

  lazy val project = Project(
    id = "lift-ng-js", 
    base = file("."),
    settings = Project.defaultSettings ++ Seq(
      defaultFetch,
      requireFetch,
      baseUrl := "https://code.angularjs.org/",
      defaultZipUrl
    )
  )
}
