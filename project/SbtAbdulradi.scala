import sbt.Keys._
import sbt.{Def, _}
import sbtspiewak.SpiewakPlugin.autoImport._

/* My default sane sbt settings */
object SbtAbdulradiProjectPlugin extends AutoPlugin {
  override def requires: Plugins = sbtspiewak.SpiewakPlugin
  override def trigger: PluginTrigger = allRequirements

  override def projectSettings = Seq(
    ThisBuild / organization := "com.abdulradi",
    ThisBuild / publishGithubUser := "tabdulradi",
    ThisBuild / publishFullName := "Tamer Abdulradi",
    
    scalacOptions -= "-source:3.0-migration",
    scalacOptions ++= Seq(
      "-source:future",
      "-Yexplicit-nulls"
    )
  )
}