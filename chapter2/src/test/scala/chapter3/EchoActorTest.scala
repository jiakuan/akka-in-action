package chapter3

import akka.actor.{ActorSystem, Props}
import akka.testkit.TestKit
import com.goticks.StopSystemAfterAll
import org.scalatest.{MustMatchers, WordSpecLike}

/**
 * @author jiakuanwang
 */
class EchoActorTest extends TestKit(ActorSystem("testsystem"))
                            with WordSpecLike
                            with MustMatchers
                            with StopSystemAfterAll {

  "The EchoActor" must {
    "reply with the same message it receives without ask" in {
      val echo = system.actorOf(Props[EchoActor], "echo-002")
      echo.tell("Hello World!", testActor)
      expectMsg("Hello World!")
    }
  }
}
