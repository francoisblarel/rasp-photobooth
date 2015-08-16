name := """mariage"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.1"

libraryDependencies ++= Seq( javaJdbc , javaEbean , cache , javaWs )


resolvers += "bintray" at "http://dl.bintray.com/shinsuke-abe/maven"

libraryDependencies += "com.github.Shinsuke-Abe" %% "dropbox4s" % "0.2.0"

libraryDependencies += "com.typesafe.play" %% "play-mailer" % "2.4.1"