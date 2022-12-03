lazy val root = project
  .in(file("."))
  .settings(
    name := "advent-of-code",
    version := "0.1.0",

    scalaVersion := "3.2.1",

    libraryDependencies += "com.novocode" % "junit-interface" % "0.11" % "test",
    libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.14" % "test"
  )
