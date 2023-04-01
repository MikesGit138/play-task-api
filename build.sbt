name := """first-play-proj"""
organization := "com.artuvic"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava, PlayEbean)


scalaVersion := "2.13.10"


libraryDependencies ++= Seq(
  guice,
  javaJdbc,
   "mysql" % "mysql-connector-java" % "8.0.19",	
   evolutions,  
   jdbc
)
