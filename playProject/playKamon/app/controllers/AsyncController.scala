package controllers

import javax.inject.{Inject, Singleton}

import akka.actor.ActorSystem
import kamon.play.action.TraceName
import play.api.mvc.{Action, Controller}

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class AsyncController @Inject() (actorSystem: ActorSystem) extends Controller {

  def message = Action.async {
    implicit val myExecutionContext: ExecutionContext = actorSystem.dispatchers.lookup("myDispatcher")

    Thread.sleep(1000)
    Future{"hallo"}.map { msg => Ok(msg) }
  }

  def message2 = TraceName("my-trace-name") {
    Action.async {
      implicit val myExecutionContext: ExecutionContext = actorSystem.dispatchers.lookup("myDispatcher")

      Thread.sleep(500)
      Future{"hallo"}.map { msg => Ok(msg) }
    }
  }

}
