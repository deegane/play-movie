name := """play-movie"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  javaJdbc,
  cache,
  javaWs,
  javaJdbc,
  "mysql" % "mysql-connector-java" % "5.1.36",
  "org.scalatestplus.play" %% "scalatestplus-play" % "2.0.0" % "test",
  "org.mockito" % "mockito-core" % "2.7.22"
)