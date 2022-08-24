import sbt.Keys.libraryDependencies

ThisBuild / version := "0.1.0"

ThisBuild / organization := "ru.digitalleague"

ThisBuild / scalaVersion := "2.12.16"

assemblyMergeStrategy in assembly := {
  case PathList("META-INF", xs @ _*) => MergeStrategy.discard
  case x => MergeStrategy.first
}

val sparkVersion = "3.1.0"
val core = "org.apache.spark" %% "spark-core" % sparkVersion
val sql = "org.apache.spark" %% "spark-sql" % sparkVersion
val pgDriver = "org.postgresql" % "postgresql" % "42.3.6"

lazy val root = (project in file("."))
  .settings(
    name := "WeatherAccuracy",
    assembly / mainClass := Some("Boot"),
    libraryDependencies := Seq(core, sql, pgDriver)
  )
