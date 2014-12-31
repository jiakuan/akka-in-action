package chapter3

import akka.actor.{Actor, ActorRef}

/**
 * @author jiakuanwang
 */
class FilteringActor(nextActor: ActorRef, bufferSize: Int) extends Actor {

  import FilteringActorProtocol._

  var lastMessages = Vector[Event]()

  def receive = {
    case msg: Event =>
      if (!lastMessages.contains(msg)) {
        lastMessages = lastMessages :+ msg
        nextActor ! msg
        if (lastMessages.size > bufferSize) {
          // discard the oldest
          lastMessages = lastMessages.tail
        }
      }
  }
}

object FilteringActorProtocol {

  case class Event(id: Long)

}
