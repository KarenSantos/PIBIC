name := "projeto"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache,
  "org.mindrot" % "jbcrypt" % "0.3m",
  "postgresql" % "postgresql" % "9.1-901-1.jdbc4",
  "commons-io" % "commons-io" % "2.3"
)     

val appDependencies = Seq(
  javaEbean
)

play.Project.playJavaSettings

ebeanEnabled := true