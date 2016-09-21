name := """nettySample"""

version := "1.0"

lazy val commonSettings = Seq(
  organization := "com.github.uryyyyyyy",
  scalaVersion := "2.11.8",
  libraryDependencies ++= Seq(
    "org.scalatest" %% "scalatest" % "3.0.0" % "test"
  )
)

lazy val datadog = (project in file("datadog"))
  .settings(commonSettings: _*)
  .enablePlugins(PlayScala)
  .settings(
    libraryDependencies ++= Seq(
    )
  )

lazy val newRelic = (project in file("newRelic"))
  .settings(commonSettings: _*)
  .enablePlugins(PlayScala)
  .settings(
    libraryDependencies ++= Seq(
    )
  )

lazy val helloWorld = (project in file("helloWorld"))
  .settings(commonSettings: _*)
  .settings(
    libraryDependencies ++= Seq(
    )
  )