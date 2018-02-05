package main

import backbonedegree.BackboneDegree
import cpm.CPM
import louvain.Louvain

/**
  * Created by james on 17-6-8.
  */
object Main {

  def main(args: Array[String]): Unit = {

    var inputRealFile: String = null
    var inputEvaluateFile: String = null
    var outputFile: String = null
    var method: String = null
    var master: String = null
    val argsIndex = args.zipWithIndex

    for (a<-argsIndex) a._1 match
    {
      case "-h" | "--help" =>
        println("Usage: -i <input file> [-o <output file>]\n" +
          "-ir,--input \t\t input realFile name.\n" +
          "-ie, --input \t\t input evaluateFile name.\n"+
          "-o,--output \t\t output file name.\n" +
          "-m, --method \t\t evaluation methos.\n"+
          "-master,      \t\t model .\n"+
          "-h,--help \t\t help doc.")
        sys.exit(0)
      case "-ir" | "--inputr" => inputRealFile = args.apply(a._2 + 1)
      case "-ie" | "--inpute" => inputEvaluateFile = args.apply(a._2 + 1)
      case "-o" | "--output" => outputFile = args.apply(a._2 + 1)
      case "-m" | "--method" => method = args.apply(a._2 + 1)
      case "-master" => master = args.apply(a._2+1)
      case _=>
    }

    println(inputRealFile)
    println(inputEvaluateFile)
    println(outputFile)
    println(method)
    println(master)

    method match {
      case "cpm" => CPM.calResult(master,inputRealFile,inputEvaluateFile,outputFile)
      case "bd" => BackboneDegree.calResult(master,inputRealFile,inputEvaluateFile,outputFile)
      case "louvain" => Louvain.calResult(master,inputRealFile,inputEvaluateFile,outputFile)
      case _=>
    }
  }
}
