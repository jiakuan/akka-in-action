import com.typesafe.sbt.SbtStartScript
import sbtassembly.Plugin.AssemblyKeys._
import com.typesafe.sbt.SbtNativePackager._
import NativePackagerKeys._

name := "chapter7-kernel"

version := "0.1-SNAPSHOT"

organization := "io.practiceinsight"

scalaVersion := "2.11.1"

packageArchetype.java_application

resolvers ++= Seq("Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/",
  "Sonatype snapshots" at "http://oss.sonatype.org/content/repositories/snapshots/",
  "Spray Repository" at "http://repo.spray.io",
  "Spray Nightlies" at "http://nightlies.spray.io/")

libraryDependencies ++= {
  val akkaVersion = "2.3.4"
  val sprayVersion = "1.3.1"
  Seq(
    "com.typesafe.akka" %% "akka-actor" % akkaVersion,
    "com.typesafe.akka" %% "akka-kernel" % akkaVersion,
    "io.spray" %% "spray-can" % sprayVersion,
    "io.spray" %% "spray-routing" % sprayVersion,
    "io.spray" %% "spray-json" % "1.2.6",
    "com.typesafe.akka" %% "akka-slf4j" % akkaVersion,
    "ch.qos.logback" % "logback-classic" % "1.1.2",
    "com.typesafe.akka" %% "akka-testkit" % akkaVersion % "test",
    "org.scalatest" %% "scalatest" % "2.2.3" % "test"
  )
}

// Assembly settings
mainClass in Global := Some("io.practiceinsight.Main")

jarName in assembly := "chapter7-kernel.jar"

assemblySettings

// StartScript settings
seq(SbtStartScript.startScriptForClassesSettings: _*)
