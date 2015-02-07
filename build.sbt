name := """play-with-scalajs-example"""

version := "1.0-SNAPSHOT"

scalaVersion in Global := "2.11.5"

lazy val root = (project in file(".")).aggregate(sjsJs, sjsJvm)

lazy val shared = (crossProject in file("."))
  .jvmSettings(
    libraryDependencies ++= Seq(
      cache,
      ws
    ),
    sourceGenerators in Assets <+= copyJsTask
  )
  .jvmConfigure(_.enablePlugins(PlayScala))
  .jsSettings(
  )

lazy val sjsJs = shared.js

lazy val sjsJvm = shared.jvm

val copyJsTask = TaskKey[Seq[File]]("copyJs")

copyJsTask in sjsJvm := {
  val jsPath = (fastOptJS in (sjsJs, Compile)).value.data
  val mapping = Seq(jsPath) pair relativeTo (file(jsPath.getParent))
  val target = (resourceManaged in (sjsJvm, Assets)).value
  val copy = mapping map { case (file, path) => file -> target / path }
  IO.copy(copy)
  copy map (_._2)
}

scalaJSStage in Global := FastOptStage

scalaVersion := "2.11.1"

