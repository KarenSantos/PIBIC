name := "Projeto"

version := "1.0-SNAPSHOT"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache,
  "org.mindrot" % "jbcrypt" % "0.3m",
  "postgresql" % "postgresql" % "9.1-901-1.jdbc4"
)     

val appDependencies = Seq(
  javaEbean,
  "commons-io" % "commons-io" % "2.3"
)

play.Project.playJavaSettings

ebeanEnabled := true