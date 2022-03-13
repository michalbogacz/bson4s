ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.8"

val mongoDriverVersion = "4.5.0"

lazy val bson4s = (project in file("."))
  .settings(
    name := "bson4s",
    libraryDependencies ++= Seq(
      "org.mongodb" % "bson" % mongoDriverVersion,
      "com.softwaremill.magnolia1_2" %% "magnolia" % "1.1.1",
      "org.scalatest" %% "scalatest" % "3.2.11",
      "org.mongodb.scala" %% "mongo-scala-driver" % mongoDriverVersion % Test,
    ),
    scalacOptions ++= Seq(
      "-Ywarn-unused", // Warn if an a construct is unused.
      "-Ywarn-dead-code", // Warn when dead code is identified.
      "-Xlint:inaccessible", // Warn about inaccessible types in method signatures.
      "-language:postfixOps",
      "-deprecation",
      "-Ymacro-annotations",
      "-Xfatal-warnings" // All warnings are treated as compilation errors
    ),
  )
