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

  val defaultZipUrl = zipUrl <<= (version, baseUrl) { (v, url) =>
    val ver = if(v.endsWith("-SNAPSHOT")) v.substring(0, v.length-9) else v
    val base = if(url.endsWith("/")) url else url + "/"
    s"$base$ver/angular-$ver.zip"
  }

  val defaultFetch = fetch <<= (zipUrl, streams) map { (zip, s) =>
    s.log.info(s"Fetching $zip")
    val f = unpackZip(fetchZip(zip))
    Await.result(f, 60.seconds)
  }

  private def fetchZip(zipUrl:String): Future[Array[Byte]] = {
    import dispatch._
    Http(url(zipUrl) OK as.Bytes)
  }

  private def unpackZip(bytes:Future[Array[Byte]]):Future[Seq[File]] = {
    bytes.map( b =>
      Seq()
    )
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
