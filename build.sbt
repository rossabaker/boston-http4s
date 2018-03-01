lazy val baseSettings: Seq[Setting[_]] = Seq(
  scalaVersion       := "2.12.4",
  scalacOptions     ++= Seq(
    "-deprecation",
    "-encoding", "UTF-8",
    "-feature",
    "-language:higherKinds",
    "-language:implicitConversions", "-language:existentials",
    "-unchecked",
    "-Xlint",
    "-Yno-adapted-args",
    "-Ywarn-numeric-widen",
    "-Ywarn-value-discard",
    "-Xfuture"
  ),
  resolvers += Resolver.sonatypeRepo("releases")
)

lazy val `boston-http4s` = project.in(file("."))
  .settings(moduleName := "boston-http4s")
  .settings(baseSettings: _*)
  .aggregate(core, slides)
  .dependsOn(core, slides)

lazy val core = project
  .settings(moduleName := "boston-http4s-core")
  .settings(baseSettings: _*)


lazy val slides = project
  .settings(moduleName := "boston-http4s-slides")
  .settings(baseSettings: _*)
  .settings(
    tutSourceDirectory := baseDirectory.value / "tut",
    tutTargetDirectory := baseDirectory.value / "../docs",
    watchSources ++= (tutSourceDirectory.value ** "*.html").get
  ).dependsOn(core)
  .enablePlugins(TutPlugin)
