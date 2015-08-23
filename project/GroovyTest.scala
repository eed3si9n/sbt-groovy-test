package sbt

import sbt._
import Keys._
import sbt.classpath.ClasspathUtilities
import sbt.classfile.Analyze
import xsbti.AnalysisCallback
import sbt.inc.{ IncrementalCompile, Analysis, IncOptions }
import xsbti.compile.SingleOutput
import org.softnetwork.sbt.plugins.GroovyPlugin.testGroovy._

object GroovyTest {
  val groovyTests = Def.task {
    val old = (definedTests in Test).value
    val out = (resourceManaged in Test).value
    val srcs = ((groovySource in Test).value ** "*.groovy").get.toList
    val xs = (out ** "*.class").get.toList
    val loader = ClasspathUtilities.toLoader((fullClasspath in Test).value map {_.data})
    val log = sLog.value
    val a0 = IncrementalCompile(
      srcs.toSet, s => None,
      (fs, changs, callback) => {
        def readAPI(source: File, classes: Seq[Class[_]]): Set[String] = {
          val (api, inherits) = ClassToAPI.process(classes)
          callback.api(source, api)
          inherits.map(_.getName)
        }
        Analyze(xs, srcs, log)(callback, loader, readAPI)
      },
      Analysis.Empty, f => None,
      new SingleOutput {
        def outputDirectory = out
      },
      log,
      IncOptions.Default)._2
    val frameworks = (loadedTestFrameworks in Test).value.values.toList
    old ++ Tests.discover(frameworks, a0, log)._1
  }
}

