package util

import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD

/**
  * Created by james on 17-6-7.
  */
object RLCommon {

  def readFile(sc:SparkContext,inputFile:String) : Array[(String,String)] = {
    val dataFile = sc.textFile(inputFile);
    val array = lines(dataFile);
    val arrays = array.combineByKey(List(_),(x:List[String], y:String) => y :: x, (x:List[String], y:List[String]) => x ::: y)
    val nodeList = arrays.values
    val nodePairs = nodeList.map(n=>formPairs(n))
    val sumPairs = nodePairs.reduce((a,b)=>{val list=a:::b;list})
    val endResult = sumPairs.distinct
    return endResult.toArray
  }
  def lines(rdd:RDD[String]):RDD[(String,String)]=
  {
    rdd.flatMap(split)  // 主要还是理解 flatmap的特性 把多个数组合并成一个数组 或者把多个分区合并
  }
  def split(line:String):Array[(String,String)]=
  {
    val array=line.split(" ")
    val inter=(array(1),array(0))
    val result=Array(inter)
    return result
  }
  def formPairs(list:List[String]):List[(String,String)]=
  {
    var pairs :List[(String,String)]=List()
    val length=list.length

    for(i<-0 until(length))
    {
      val first=list(i)
      for(j<-i+1 until(length))
      {
        val second=list(j)
        if(first>second)
        {
          val pair=(first,second);
          //添加元素
          pairs=pair+:pairs
        }
        else
        {
          val  pair=(second,first)
          pairs=pair+:pairs
        }
      }
    }
    return pairs

  }
}
