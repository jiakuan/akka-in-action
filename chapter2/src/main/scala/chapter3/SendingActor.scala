package chapter3

import akka.actor.{ActorRef, Actor}

/**
 * @author jiakuanwang
 */
object Kiosk01Protocol {

  case class Ticket(seat: Int)

  case class Game(name: String, tickets: Seq[Ticket])

}

class Kiosk01(nextKiosk: ActorRef) extends Actor {

  import Kiosk01Protocol._

  def receive = {
    case game@Game(_, tickets) => nextKiosk ! game.copy(tickets = tickets.tail)
  }
}
