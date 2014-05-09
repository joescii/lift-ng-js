import sbt._
import sbt.Keys._

object LiftNgJsBuild extends Build {
  val liftVersion = SettingKey[String]("liftVersion", "Full version number of the Lift Web Framework")
  val liftEdition = SettingKey[String]("liftEdition", "Lift Edition (short version number to append to artifact name)")

  val fetchKey = TaskKey[Seq[File]]("fetch", "Fetch the angular modules for the configured version from https://code.angularjs.org")

  val fetch = fetchKey <<= (version, streams) map { (v, s) =>
    val ver = if(v.endsWith("-SNAPSHOT")) v.substring(0, v.length-9) else v
    s.log.info(s"Fetching https://code.angularjs.org/$ver")
    Seq()
  }

  lazy val project = Project(
    id = "lift-ng-js", 
    base = file("."),
    settings = Project.defaultSettings ++ Seq(fetch)
  )
}
