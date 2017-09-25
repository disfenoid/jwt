name := "jwt"

organization := "io.igl"

version := "1.2.2"

scalaVersion := "2.12.1"
crossScalaVersions := Seq("2.11.7", "2.12.1")

val circeVersion = "0.9.0-M1"

libraryDependencies ++= Seq(
  "commons-codec" % "commons-codec" % "1.10",
  scalatest(scalaVersion.value)
) ++ circeDeps(circeVersion)

def circeDeps(circeVersion: String) = Seq(
  "io.circe" %% "circe-core",
  "io.circe" %% "circe-generic",
  "io.circe" %% "circe-parser"
) map (_ % circeVersion)

def scalatest(scalaVersion: String) = scalaVersion match {
  case "2.12.1" => "org.scalatest" % "scalatest_2.12" % "3.0.1" % "test"
  case "2.11.7" => "org.scalatest" % "scalatest_2.11" % "2.2.4" % "test"
}

publishMavenStyle := true

publishArtifact in Test := false

publishTo := {
  val nexus = "https://oss.sonatype.org/"
  if (isSnapshot.value)
    Some("snapshots" at nexus + "content/repositories/snapshots")
  else
    Some("releases"  at nexus + "service/local/staging/deploy/maven2")
}

pomExtra :=
  <url>github.com/disfenoid/jwt</url>
  <licenses>
    <license>
      <name>MIT License</name>
      <url>http://www.opensource.org/licenses/mit-license.php</url>
      <distribution>repo</distribution>
    </license>
  </licenses>
  <scm>
    <url>git@github.com:disfenoid/jwt.git</url>
    <connection>scm:git:git@github.com:disfenoid/jwt.git</connection>
  </scm>
  <developers>
    <developer>
      <id>iain-logan</id>
      <name>Iain Logan</name>
      <url>igl.io</url>
    </developer>
    <developer>
      <id>disfenoid</id>
      <name>Alexandr</name>
    </developer>
  </developers>
