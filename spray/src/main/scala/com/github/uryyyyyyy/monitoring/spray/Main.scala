package com.github.uryyyyyyy.monitoring.spray

import akka.actor.{ActorSystem, Props}
import akka.io.IO
import akka.pattern.ask
import akka.util.Timeout
import kamon.Kamon
import spray.can.Http

object Main {

  def main(args: Array[String]): Unit = {

    implicit val system = ActorSystem("tracker-spray-system")

    // 終了時の処理
    system.registerOnTermination {
      Kamon.shutdown()
    }

    val service = system.actorOf(Props[RoutesActor], "tracker-service")

    implicit val timeout = Timeout(ApplicationConf.boot.http.timeout)

    IO(Http) ? Http.Bind(service, interface = ApplicationConf.boot.http.interface, port = ApplicationConf.boot.http.port)
  }
}