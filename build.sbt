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
      "com.datadoghq" % "java-dogstatsd-client" % "2.2",
      "com.newrelic.agent.java" % "newrelic-api" % "3.32.0"
    ),
    routesGenerator := InjectedRoutesGenerator
  )

lazy val newRelic = (project in file("newRelic"))
  .settings(commonSettings: _*)
  .enablePlugins(PlayScala)
  .settings(
    libraryDependencies ++= Seq(
      "com.newrelic.agent.java" % "newrelic-api" % "3.32.0"
    ),
    routesGenerator := InjectedRoutesGenerator
  )

lazy val helloWorld = (project in file("helloWorld"))
  .settings(commonSettings: _*)
  .settings(
    libraryDependencies ++= Seq(
      "com.datadoghq" % "java-dogstatsd-client" % "2.2",
      "com.newrelic.agent.java" % "newrelic-api" % "3.32.0"
    ),
    mainClass in Compile := Some("com.github.uryyyyyyy.monitoring.hello.Main")
  )

val sprayVersion = "1.3.3"
val kamonVersion = "0.6.1"

lazy val spray = (project in file("spray"))
  .settings(commonSettings: _*)
  .settings(
    libraryDependencies ++= Seq(
      "io.kamon" %% "kamon-core" % kamonVersion,
      "io.kamon" %% "kamon-akka" % kamonVersion,
      "io.kamon" %% "kamon-system-metrics" % kamonVersion,
      "io.kamon" %% "kamon-spray" % kamonVersion,
      "io.kamon" %% "kamon-datadog" % kamonVersion,
      "io.spray" %% "spray-http" % sprayVersion,
      "io.spray" %% "spray-routing-shapeless2" % sprayVersion,
      "io.spray" %% "spray-can" % sprayVersion,
      "io.spray" %% "spray-testkit" % sprayVersion % Test
    ),
    mainClass in Compile := Some("com.github.uryyyyyyy.monitoring.spray.Main")
  )