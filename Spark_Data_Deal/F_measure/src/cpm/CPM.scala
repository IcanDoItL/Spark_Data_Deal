package cpm

import realCommunity.RealCommunity
import util.CBCommon
import util.Common

/**
  * Created by james on 17-6-5.
  */
object CPM {

  def calResult(master:String,realFile:String,evaluateFile:String,outputFile:String): Unit ={

    val sc = Common.getSparkContent(master,"CalResult")
    RealCommunity.readfile(sc,realFile);
    val realPairs = RealCommunity.nodePairsInReal
    val cpmPairs = CBCommon.readFile(sc,evaluateFile).toList;
    Common.calResult(realPairs,cpmPairs,outputFile);

  }
}
