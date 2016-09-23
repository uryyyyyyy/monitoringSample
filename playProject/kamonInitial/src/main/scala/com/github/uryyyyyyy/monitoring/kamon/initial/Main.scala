package com.github.uryyyyyyy.monitoring.kamon.initial

import kamon.Kamon

object Main {

  def main (args: Array[String] ): Unit = {
    Kamon.start()

    val someHistogram = Kamon.metrics.histogram("some-histogram")
    val someCounter = Kamon.metrics.counter("some-counter")

    someHistogram.record(42)
    someHistogram.record(50)
    someCounter.increment()

    // This application wont terminate unless you shutdown Kamon.
    Kamon.shutdown()
  }
}


