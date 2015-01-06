package io.practiceinsight

import org.scalatest.{WordSpecLike, MustMatchers, BeforeAndAfterAll}
import akka.testkit.{TestActorRef, ImplicitSender, TestKit}
import akka.actor.ActorSystem

/**
 * @author jiakuanwang
 */
class HelloWorldTest extends TestKit(ActorSystem("HelloWorldTest"))
                             with ImplicitSender
                             with WordSpecLike
                             with MustMatchers
                             with BeforeAndAfterAll {

  val actor = TestActorRef[HelloWorld]

  "HelloWorld" must {
    "must reply when sending a string" in {
      actor.tell("everybody", testActor)
      expectMsg("Hello everybody")
    }
    "must reply when sending an object" in {
      actor.tell(this, testActor)
      expectMsg("Hello world")
    }
  }

  override protected def afterAll() = {
    system.shutdown()
  }
}
