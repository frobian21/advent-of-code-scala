lazy val root = project
  .in(file("."))
  .settings(
    name := "advent-of-code",
    version := "0.1.0",
    scalaVersion := "3.2.2",

    libraryDependencies += "com.github.sbt" % "junit-interface" % "0.13.3" % "test",
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.15" % "test"
  )
