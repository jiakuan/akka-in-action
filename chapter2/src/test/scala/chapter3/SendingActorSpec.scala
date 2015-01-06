package chapter3

import akka.actor.{Props, ActorSystem}
import akka.testkit.TestKit
import com.goticks.StopSystemAfterAll
import org.scalatest.{MustMatchers, WordSpecLike}

/**
 * @author jiakuanwang
 */
class SendingActorSpec extends TestKit(ActorSystem("testsystem"))
                               with WordSpecLike
                               with MustMatchers
                               with StopSystemAfterAll {

  "A Sending Actor" must {
    "send a message to an actor when it has finished" in {
      import Kiosk01Protocol._
      val props = Props(new Kiosk01(testActor))
      val sendingActor = system.actorOf(props, "kiosk1")
      val tickets = Vector(Ticket(1), Ticket(2), Ticket(3))
      val game = Game("Lakers vs Bulls", tickets)
      sendingActor ! game
      expectMsgPF() {
        case Game(_, tickets) => tickets.size must be(game.tickets.size - 1)
      }
    }
  }

}
