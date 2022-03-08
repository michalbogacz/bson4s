ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.8"

lazy val root = (project in file("."))
  .settings(
    name := "bson4s",
    libraryDependencies ++= Seq(
      "org.mongodb.scala" %% "mongo-scala-driver" % "4.4.1",
      "com.softwaremill.magnolia1_2" %% "magnolia" % "1.1.1",
      "org.scalatest" %% "scalatest" % "3.2.10"
    ),
    scalacOptions ++= Seq(
      "-Ywarn-unused", // Warn if an a construct is unused.
      "-Ywarn-dead-code", // Warn when dead code is identified.
      "-Xlint:inaccessible", // Warn about inaccessible types in method signatures.
      "-language:postfixOps",
      "-deprecation",
      "-Ymacro-annotations",
      "-Wconf:src=src_managed/.*:silent", // Ignore warning from generated files
      "-Xfatal-warnings" // All warnings are treated as compilation errors
    ),
  )
