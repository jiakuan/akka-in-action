package chapter3

import akka.actor.Actor

/**
 * @author jiakuanwang
 */
class EchoActor extends Actor {

  def receive = {
    case msg =>
      sender ! msg
      println(msg)
  }
}
