package com.yukihirai0505

object Main extends App {

  import scala.io.Source.stdin

  var firstErrorLineNum = 0
  var lineNum = 0
  var startTag = Array.empty[String]
  for (line <- stdin.getLines) {
    lineNum += 1
    val input = line
    val oneLineTagStartPattern = "<(.*)>".r
    val oneLineTagEndPattern = "</(.*)>".r

    def checkOneLineTagPattern(s: String): Unit = {
      oneLineTagStartPattern.findFirstIn(s) match {
        case Some(x) => oneLineTagEndPattern.findFirstIn(s) match {
          case Some(y) =>
            if (!x.equals(y.replaceFirst("/", "")))
              if (firstErrorLineNum.equals(0)) firstErrorLineNum = lineNum
              else checkOneLineTagPattern(s.replace(x, "").replace(y, ""))
          case _ =>
        }
        case _ =>
      }
    }

    def checkMultiLineTagPattern(s: String) = {
      oneLineTagStartPattern.findFirstIn(s) match {
        case Some(x) => if (!x.contains("li")) startTag :+ x
        case _ =>
      }
      oneLineTagEndPattern.findFirstIn(s) match {
        case Some(x) =>
          if (!x.contains("li") && startTag.nonEmpty && !startTag.last.equals(x.replace("/", ""))) {
            if (firstErrorLineNum.equals(0))
              firstErrorLineNum = lineNum
              startTag.drop(1).dropRight(1)
          }
        case _ =>
      }
    }

    checkOneLineTagPattern(input)
    checkMultiLineTagPattern(input)
  }
  println(firstErrorLineNum)
}