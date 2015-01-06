package chapter3

import akka.actor.{Props, ActorSystem}
import akka.testkit.{TestActorRef, TestKit}
import com.goticks.StopSystemAfterAll
import org.scalatest.{MustMatchers, WordSpecLike}

/**
 * @author jiakuanwang
 */
class SilentActorSpec extends TestKit(ActorSystem("testsystem"))
                              with WordSpecLike
                              with MustMatchers
                              with StopSystemAfterAll {

  "A Silent Actor" must {
    "change internal state when it receives a message, single" in {
      import SilentActorProtocol._
      val silentActor = TestActorRef[SilentActor]
      silentActor ! SilentMessage("whisper")
      silentActor.underlyingActor.state must (contain("whisper"))
    }

    "change internal state when it receives a message, multi" in {
      import SilentActorProtocol._
      val silentActor = system.actorOf(Props[SilentActor], "s3")
      silentActor ! SilentMessage("whisper1")
      silentActor ! SilentMessage("whisper2")
      silentActor ! SilentMessage("whisper3")
      silentActor ! GetState(testActor)
      expectMsg(Vector("whisper1", "whisper2", "whisper3"))
    }
  }
}
