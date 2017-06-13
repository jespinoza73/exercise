name := """play-java"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.11"

libraryDependencies += javaJdbc
libraryDependencies += cache
libraryDependencies += javaWs
libraryDependencies += "org.mongodb" % "mongo-java-driver" % "3.2.2"
libraryDependencies += "org.jongo" % "jongo" % "1.3.0"

