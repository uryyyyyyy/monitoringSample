name := """nettySample"""

version := "1.0"

val sprayVersion = "1.3.3"
val kamonVersion = "0.6.2"
val akkaVersion = "2.3.13"

lazy val commonSettings = Seq(
  organization := "com.github.uryyyyyyy",
  scalaVersion := "2.11.8",
  libraryDependencies ++= Seq(
    "org.scalatest" %% "scalatest" % "3.0.0" % "test"
  )
)

lazy val kamonInitial = (project in file("kamonInitial"))
  .settings(commonSettings: _*)
  .settings(
    libraryDependencies ++= Seq(
      "org.slf4j" % "slf4j-api" % "1.7.12",
      "ch.qos.logback" % "logback-classic" % "1.1.3",
      "io.kamon" %% "kamon-core" % kamonVersion,
      "io.kamon" %% "kamon-system-metrics" % kamonVersion,
      "io.kamon" %% "kamon-datadog" % kamonVersion
    ),
    mainClass in Compile := Some("com.github.uryyyyyyy.monitoring.kamon.initial.Main")
  )

lazy val sprayInitial = (project in file("sprayInitial"))
  .settings(commonSettings: _*)
  .settings(
    libraryDependencies ++= Seq(
      "com.typesafe.akka" %% "akka-actor" % akkaVersion,
      "com.typesafe.akka" %% "akka-slf4j" % akkaVersion,
      "io.kamon" %% "kamon-core" % kamonVersion,
      "io.kamon" %% "kamon-system-metrics" % kamonVersion,
      "io.kamon" %% "kamon-spray" % kamonVersion,
      "io.kamon" %% "kamon-datadog" % kamonVersion,
      //"io.kamon" %% "kamon-log-reporter" % kamonVersion,
      "org.slf4j" % "slf4j-api" % "1.7.12",
      "ch.qos.logback" % "logback-classic" % "1.1.3",
      "io.spray" %% "spray-routing" % sprayVersion,
      "io.spray" %% "spray-can" % sprayVersion
    ),
    mainClass in Compile := Some("com.github.uryyyyyyy.monitoring.kamon.spray.initial.Main"),
    aspectjSettings,
    javaOptions in run <++= AspectjKeys.weaverOptions in Aspectj,
    fork in run := true
  )

lazy val spraySample = (project in file("spraySample"))
  .settings(commonSettings: _*)
  .settings(
    libraryDependencies ++= Seq(
      "io.kamon" %% "kamon-core" % kamonVersion,
      "io.kamon" %% "kamon-system-metrics" % kamonVersion,
      "io.kamon" %% "kamon-spray" % kamonVersion,
      "io.kamon" %% "kamon-datadog" % kamonVersion,
      "io.kamon" %% "kamon-statsd" % kamonVersion,
      "com.typesafe.akka" %% "akka-slf4j" % "2.3.13",
      "org.slf4j" % "slf4j-api" % "1.7.12",
      "ch.qos.logback" % "logback-classic" % "1.1.3",
      "io.spray" %% "spray-routing" % sprayVersion,
      "io.spray" %% "spray-can" % sprayVersion,
      "io.spray" %% "spray-testkit" % sprayVersion % Test
    ),
    mainClass in Compile := Some("com.github.uryyyyyyy.monitoring.spray.Main"),
    aspectjSettings,
    javaOptions in run <++= AspectjKeys.weaverOptions in Aspectj,
    fork in run := true
  )

lazy val kamonSample = (project in file("kamonSample"))
  .settings(commonSettings: _*)
  .settings(
    libraryDependencies ++= Seq(
      "io.kamon" %% "kamon-core" % kamonVersion,
      "com.typesafe.akka" %% "akka-slf4j" % "2.3.13",
      "org.slf4j" % "slf4j-api" % "1.7.12",
      "ch.qos.logback" % "logback-classic" % "1.1.3"
    ),
    mainClass in Compile := Some("com.github.uryyyyyyy.monitoring.kamon.Main"),
    aspectjSettings,
    javaOptions in run <++= AspectjKeys.weaverOptions in Aspectj,
    fork in run := true
  )