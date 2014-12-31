package chapter3

import akka.actor.{ActorSystem, Props}
import akka.testkit.TestKit
import org.scalatest.{MustMatchers, WordSpecLike}

/**
 * @author jiakuanwang
 */
class FilteringActorSpec extends TestKit(ActorSystem("testsystem"))
                                 with WordSpecLike with MustMatchers {

  "A Filtering Actor" must {
    "filter out particular message" in {
      import FilteringActorProtocol._
      val props = Props(new FilteringActor(testActor, 5))
      val filter = system.actorOf(props, "filter-1")
      filter ! Event(1)
      filter ! Event(2)
      filter ! Event(1)
      filter ! Event(3)
      filter ! Event(1)
      filter ! Event(4)
      filter ! Event(5)
      filter ! Event(5)
      filter ! Event(6)
      val eventIds = receiveWhile() {
        case Event(id) if id <= 5 => id
      }
      eventIds must be(List(1, 2, 3, 4, 5))
      expectMsg(Event(6))
    }

    "filter out particular messages using "
  }

}
