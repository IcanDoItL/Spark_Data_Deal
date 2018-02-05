package louvain

import realCommunity.RealCommunity
import util.{Common, RLCommon}
/**
  * Created by james on 17-6-7.
  */
object Louvain {

  def calResult(master:String,realFile:String,evaluateFile:String,outputFile:String): Unit ={

    val sc = Common.getSparkContent(master,"CalResult")
    RealCommunity.readfile(sc,realFile);
    val realPairs = RealCommunity.nodePairsInReal
    val cpmPairs = RLCommon.readFile(sc,evaluateFile).toList;
    Common.calResult(realPairs,cpmPairs,outputFile);

  }
}
