package com.github.uryyyyyyy.monitoring.hello

import com.newrelic.api.agent.Trace
import com.newrelic.api.agent.NewRelic

object Main {

  def main (args: Array[String] ): Unit = {
    NewRelic.setTransactionName(null, "/myTransaction")

    Thread.sleep(1000)

    for(i <- 1 to 1000){
      val r = func1(1000)
      println("hello" + r)
    }
  }


  @Trace
  def func1(time: Int) = {
    Thread.sleep(time)
    "world"
  }
}


