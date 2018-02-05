package backbonedegree

import java.io.{File, PrintWriter}

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.RDD
import realCommunity.RealCommunity
import util.CBCommon
import util.Common
/**
  * Created by james on 17-6-7.
  */
object BackboneDegree {


  def calResult(master:String,realFile:String,evaluateFile:String,outputFile:String): Unit ={

    val sc = Common.getSparkContent(master,"CalResult")
    RealCommunity.readfile(sc,realFile);
    val realPairs = RealCommunity.nodePairsInReal
    val cpmPairs = CBCommon.readFile(sc,evaluateFile).toList;
    Common.calResult(realPairs,cpmPairs,outputFile);

  }
}
