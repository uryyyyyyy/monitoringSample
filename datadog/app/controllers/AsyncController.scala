package controllers

import javax.inject.{Inject, Singleton}

import akka.actor.ActorSystem
import com.newrelic.api.agent.{NewRelic, Trace}
import org.joda.time.DateTime
import play.api.mvc.{Action, Controller}

import scala.concurrent.duration._
import scala.concurrent.{Await, ExecutionContext, Future}

@Singleton
class AsyncController @Inject() (actorSystem: ActorSystem) extends Controller {

  NewRelic.setTransactionName(null, "/myTransaction")

  def message = Action.async {
    implicit val myExecutionContext: ExecutionContext = actorSystem.dispatchers.lookup("myDispatcher")

    val fList = (1 to 10).map(_ => Future{Thread.sleep(1000);println(DateTime.now)})
    Await.result(Future.sequence(fList), Duration.Inf)
    println("finish")
    Future{"hallo"}.map { msg => Ok(msg) }
  }

  def message2 = Action.async {
    implicit val myExecutionContext: ExecutionContext = actorSystem.dispatchers.lookup("myDispatcher")

    Future{func1(100)}.map { msg => Ok(msg) }
  }

  @Trace
  def func1(time: Int) = {
    Thread.sleep(time)
    "world"
  }

}
