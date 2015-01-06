package chapter3

import akka.actor.{ActorLogging, ActorRef, Actor}
import chapter3.GreeterProtocol.Greeting

/**
 * @author jiakuanwang
 */
class Greeter(listener: Option[ActorRef]) extends Actor with ActorLogging {

  def receive: Receive = {
    case Greeting(who) =>
      val message = "Hello " + who + "!"
      log.info(message)
      listener.foreach(_ ! message)
  }
}

object GreeterProtocol {

  case class Greeting(msg: String)

}
