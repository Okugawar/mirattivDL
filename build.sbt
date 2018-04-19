name := "mirrativDL"

organization := "org.oriver"

version := "0.1-SNAPSHOT"

scalaVersion := "2.12.5"

//unmanagedJars in Compile += Attributed.blank(file(System.getenv("JAVA_HOME") + "/jre/lib/ext/jfxrt.jar"))

libraryDependencies ++= Seq(
  "org.scalafx" %% "scalafx" % "8.0.144-R12",
  "org.scalaj" %% "scalaj-http" % "2.3.0",
  "com.typesafe.play" %% "play-json" % "2.6.9",
  "org.scala-lang.modules" %% "scala-xml" %  "1.1.0",
  "nu.validator.htmlparser" % "htmlparser" % "1.4",
)

libraryDependencies ++= Seq(
  "io.circe" %% "circe-core",
  "io.circe" %% "circe-generic",
  "io.circe" %% "circe-parser",
  "io.circe" %% "circe-optics"
).map(_ % "0.8.0")


fork := true
