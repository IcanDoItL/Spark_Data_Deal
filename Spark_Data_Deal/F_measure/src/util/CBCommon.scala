package util

import java.io.{File, PrintWriter}

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

/**
  * Created by james on 17-6-7.
  */
object CBCommon {


  def readFile(sc:SparkContext,inputFile:String) : Array[(String,String)]={
    val fileData = sc.textFile(inputFile)
    val pairs = lines(fileData)
    val distinctPairs=pairs.distinct();
    return distinctPairs.collect();
  }
  def lines(rdd:RDD[String]) : RDD[(String,String)] = {
    rdd.flatMap(splitPairs)
  }

  def splitPairs(line:String) : Array[(String,String)] = {
    var collects :List[(String,String)] = List()
    var array = line.split(": ")

    if(array.length != 2)
      {
        array = line.split(":")
      }
    var nodes = array(1).split(" ")
    if(nodes.length <= 1 && array.length >= 2)
      {
        nodes = array(1).split(",")
      }

    val length = nodes.length
    for(i<-0 until(length))
    {
      val first = nodes(i)
      for(j<-i+1 until(length))
      {
        val  second = nodes(j)
        if(first>second)
        {
          val pair=(first,second)
          //添加元素
          collects=pair+:collects
        }
        else if(first<second)
        {
          val  pair=(second,first)
          collects=pair+:collects
        }
      }

    }
    return collects.toArray
  }
}
