import sbt._
import Defaults._

// Comment to get more information during initialization
logLevel := Level.Info

ivyLoggingLevel := UpdateLogging.Full

resolvers ++= Seq(
    DefaultMavenRepository,
    "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/",
    "Sonatype snapshots" at "http://oss.sonatype.org/content/repositories/snapshots/",
    Classpaths.typesafeReleases
)

addSbtPlugin("com.typesafe.sbt" % "sbt-scalariform" % "1.2.1")

