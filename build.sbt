name := "ng-js"

organization := "net.liftmodules"

homepage := Some(url("https://github.com/joescii/lift-ng-js"))

pluginVersion := "0.1"

ngVersion := "1.2.22"

LiftNgJsBuild.snapshot := false

liftVersion <<= liftVersion ?? "2.5.1"

liftEdition <<= liftVersion { _.substring(0,3) }

name <<= (name, liftEdition) { (n, e) =>  n + "_" + e }

scalaVersion <<= scalaVersion ?? "2.9.1"  // This project's scala version is purposefully set at the lowest common denominator to ensure each version compiles.

crossScalaVersions := Seq("2.11.2", "2.10.4", "2.9.2", "2.9.1-1", "2.9.1")

// sbt 0.13 does some dumb stuff with the name that replaces the liftEdition's . with a -, e.g. 2.5 => 2-5.  This fixes that.
moduleName := name.value

resolvers ++= Seq(
  "sonatype-snapshots" at "https://oss.sonatype.org/content/repositories/snapshots",
  "sonatype-releases"  at "https://oss.sonatype.org/content/repositories/releases"
)

libraryDependencies <++= liftVersion { v =>
  Seq(
    "net.liftweb"   %% "lift-webkit"  % v     % "provided"
  )
}

scalacOptions <<= scalaVersion map { v: String =>
  val opts = "-deprecation" :: "-unchecked" :: Nil
  if (v.startsWith("2.9.")) opts 
  else opts ++ ("-feature" :: "-language:postfixOps" :: "-language:implicitConversions" :: Nil)
}

buildInfoSettings

sourceGenerators in Compile <+= buildInfo

buildInfoKeys := Seq[BuildInfoKey](ngVersion)

buildInfoPackage := "net.liftmodules.ng.js"

// Publishing stuff for sonatype
publishTo <<= version { _.endsWith("SNAPSHOT") match {
    case true  => Some("snapshots" at "https://oss.sonatype.org/content/repositories/snapshots")
    case false => Some("releases" at "https://oss.sonatype.org/service/local/staging/deploy/maven2")
  }
}

credentials += Credentials( file("sonatype.credentials") )

publishMavenStyle := true

publishArtifact in Test := false

pomIncludeRepository := { _ => false }

pomExtra := (
        <scm>
            <url>git@github.com:joescii/lift-ng-js.git</url>
            <connection>scm:git:git@github.com:joescii/lift-ng-js.git</connection>
        </scm>
        <developers>
            <developer>
              <id>joescii</id>
              <name>Joe Barnes</name>
              <url>https://github.com/joescii</url>
            </developer>
        </developers>
 )
 
licenses += ("Apache-2.0", url("http://www.apache.org/licenses/LICENSE-2.0.html"))

seq(lsSettings :_*)

(LsKeys.tags in LsKeys.lsync) := Seq("lift", "angular", "javascript")

(description in LsKeys.lsync) := "lift-ng-js is a convenient packaging of AngularJS modules for Lift"

(LsKeys.ghUser in LsKeys.lsync) := Some("joescii")

(LsKeys.ghRepo in LsKeys.lsync) := Some("lift-ng-js")

(LsKeys.ghBranch in LsKeys.lsync) := Some("master")
