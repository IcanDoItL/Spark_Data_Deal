package realCommunity

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.RDD
/**
  * Created by james on 17-6-5.
  */
object RealCommunity {
  var nodePairsInReal:List[(String,String)]=Nil;
  def readfile(sc:SparkContext,realFile: String)=
  {
    val FileDate=sc.textFile(realFile)
    val array=lines(FileDate)
    val arrays=array.combineByKey(List(_),(x:List[String], y:String) => y :: x, (x:List[String], y:List[String]) => x ::: y)
    //返回 value 的 list 链表 RDD[List[String]]
    val iterator=arrays.values;
    val collectPairs=iterator.map(n=>collect(n))
    val allPairs=collectPairs.reduce((a,b)=>join(a,b))
    val distinctPairs=allPairs.distinct//去重
    nodePairsInReal=distinctPairs;
    /*
    map 操作针对的是集合里面的每一个单个元素 进行操作 不能对里面的元素进行结合操作
     */
  }

  def lines(rdd:RDD[String]):RDD[(String,String)]=
  {
    rdd.flatMap(split)
  }
  def split(line:String):Array[(String,String)]=
  {
    val array=line.split("\t")
    val nodes = array(1).split(" ")
    val result = nodes.map { node => (node,array(0)) }
    return result
  }
  //主要理解他的 单个元素代表什么
  def collect(list:List[String]):List[(String,String)]= //能否直接返回 节点对
  {
    //var 动态可以变化的 val 固定元素 不可以改变
    var collects :List[(String,String)]=List()
    //用for循环吧
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
          collects=pair+:collects
        }
        else
        {
          val  pair=(second,first)
          collects=pair+:collects
        }
      }
    }
    return collects
  }

  def join(a:List[(String,String)],b:List[(String,String)]):List[(String,String)]=
  {
    val list=a ::: b
    return list
  }
}
