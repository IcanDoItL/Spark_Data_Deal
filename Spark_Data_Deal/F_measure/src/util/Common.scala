package util

import java.io.{File, PrintWriter}

import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by james on 17-6-7.
  */
object Common {

  def getSparkContent(master:String,appName:String) : SparkContext = {
    val conf = new SparkConf()
    conf.setMaster(master).setAppName(appName)
    val sc = new SparkContext(conf)
    return sc
  }
  def calResult(realPairs:List[(String,String)],comparePairs:List[(String,String)],outputFile:String): Unit = {
    val Allpairs = realPairs.:::(comparePairs);//::: 和 ++一样 两个list合并
    val nodePairsInreal = realPairs.length
    val nodePairsCpm = comparePairs.length
    val nodePairsNumInreal = (Allpairs.length-Allpairs.distinct.length)
    val Recall = nodePairsNumInreal.toDouble/nodePairsInreal
    val Precision:Double = nodePairsNumInreal.toDouble/nodePairsCpm
    val f_measure = (2*Precision*Recall)/(Precision+Recall)
    val writer = new PrintWriter(new File(outputFile))
    writer.write(Precision.toString+","+Recall.toString+","+f_measure.toString+"\n")
    writer.close()
  }
}

