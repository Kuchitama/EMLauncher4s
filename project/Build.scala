import sbt._
import Keys._
import scalariform.formatter.preferences._
import com.typesafe.sbt.SbtScalariform._

object BuildSettings {
  val buildName = "EMLauncher4S"
  val buildOrganization = "com.github.kuchitama"
  val buildVersion = "1.0-SNAPSHOT"
  val buildScalaVersion = "2.11.1"

  val buildSettings = Defaults.defaultSettings ++ Seq (
    name := buildName,
    organization := buildOrganization,
    version := buildVersion,
    scalaVersion := buildScalaVersion
  )
}

object EMLauncher4SBuild extends Build {
  import BuildSettings._

  val testDependencies = Seq(
    "org.scalatest"           %% "scalatest"       % "2.2.0"   % "test"
  )
  
  lazy val main = Project (
    id = "emlaunhcer4s",
    base = file(".")
  ).settings(buildSettings: _*).settings(
    libraryDependencies ++= Seq(
      "org.skinny-framework" %% "skinny-http-client" % "1.1.8",
      "org.json4s"    %% "json4s-jackson"     % "3.2.10"  % "compile",
      "org.json4s"    %% "json4s-ext"         % "3.2.10"  % "compile"
    ) ++ testDependencies
  ).settings(
    scalariformSettings : _*
  ).settings(ScalariformKeys.preferences := FormattingPreferences()
      .setPreference(IndentWithTabs, false)
      .setPreference(DoubleIndentClassDeclaration, true)
      .setPreference(PreserveDanglingCloseParenthesis, true)
  )
}
