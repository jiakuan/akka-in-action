package chapter3

import akka.actor.{ActorRef, Actor}

/**
 * @author jiakuanwang
 */
class SilentActor extends Actor {

  import SilentActorProtocol._

  var internalState = Vector[String]()

  def receive = {
    case SilentMessage(data) => internalState = internalState :+ data
    case GetState(receiver) => receiver ! internalState
  }

  def state = internalState
}

object SilentActorProtocol {

  case class SilentMessage(data: String)

  case class GetState(receiver: ActorRef)

}
