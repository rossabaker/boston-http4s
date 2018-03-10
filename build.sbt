lazy val baseSettings: Seq[Setting[_]] = Seq(
  scalaVersion       := "2.12.4",
  scalacOptions     ++= Seq(
    "-deprecation",
    "-encoding", "UTF-8",
    "-feature",
    "-language:higherKinds",
    "-language:implicitConversions", "-language:existentials",
    "-unchecked",
    "-Yno-adapted-args",
    "-Ywarn-numeric-widen",
    "-Ywarn-value-discard",
    "-Xfuture",
    "-Ypartial-unification"
  ),
  resolvers += Resolver.sonatypeRepo("releases"),
  addCompilerPlugin("org.spire-math" % "kind-projector" % "0.9.4" cross CrossVersion.binary),
  addCompilerPlugin("org.spire-math" % "kind-projector" % "0.9.4" cross CrossVersion.binary)  
)

lazy val `boston-http4s` = project.in(file("."))
  .settings(moduleName := "boston-http4s")
  .settings(baseSettings: _*)
  .aggregate(core, slides)
  .dependsOn(core, slides)

lazy val core = project
  .settings(moduleName := "boston-http4s-core")
  .settings(baseSettings: _*)
  .settings(
    libraryDependencies ++= Seq(
      "org.http4s" %% "http4s-blaze-server" % "0.18.1",
      "org.http4s" %% "http4s-blaze-client" % "0.18.1",
      "org.http4s" %% "http4s-circe" % "0.18.1",      
      "org.http4s" %% "http4s-dsl" % "0.18.1",      
      "org.http4s" %% "http4s-server" % "0.18.1",
      "org.http4s" %% "jawn-fs2" % "0.12.1",
      "org.tpolecat" %% "doobie-h2" % "0.5.1",
      "com.github.pureconfig" %% "pureconfig-cats-effect" % "0.9.0",
      "com.ccadllc.cedi" %% "dtrace-logging" % "1.2.0",
      "io.circe" %% "circe-jawn" % "0.9.1",          
      "io.monix" %% "monix-eval" % "3.0.0-M3"      
    ))

lazy val slides = project
  .settings(moduleName := "boston-http4s-slides")
  .settings(baseSettings: _*)
  .settings(
    tutSourceDirectory := baseDirectory.value / "tut",
    tutTargetDirectory := baseDirectory.value / "../docs",
    watchSources ++= (tutSourceDirectory.value ** "*.html").get
  ).dependsOn(core)
  .enablePlugins(TutPlugin)

shellPrompt := { _ => "> " }
