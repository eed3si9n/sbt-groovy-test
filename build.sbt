lazy val root = (project in file(".")).
  settings(
    groovy.settings,
    testGroovy.settings,
    libraryDependencies += "org.codehaus.groovy" % "groovy-all" % "2.1.8",
    libraryDependencies += "com.novocode" % "junit-interface" % "0.11" % "test",
    definedTests in Test := GroovyTest.groovyTests.value
  )

