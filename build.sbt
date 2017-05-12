name := """scala-competitive-programming"""

version := "1.0"

scalaVersion in ThisBuild := "2.11.8"
run <<= run in Compile in core

lazy val macros = (project in file("macros")).settings(
  libraryDependencies += "org.scala-lang" % "scala-reflect" % scalaVersion.value
)

lazy val core = (project in file("core")) dependsOn macros