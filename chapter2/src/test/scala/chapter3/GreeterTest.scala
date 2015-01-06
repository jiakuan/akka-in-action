package chapter3

import akka.actor.{ActorSystem, Props}
import akka.testkit.TestKit
import chapter3.GreeterProtocol.Greeting
import com.goticks.StopSystemAfterAll
import org.scalatest.{MustMatchers, WordSpecLike}

/**
 * @author jiakuanwang
 */
class GreeterTest extends TestKit(ActorSystem("testsystem"))
                          with WordSpecLike
                          with MustMatchers
                          with StopSystemAfterAll {

  "The Greeter" must {
    "say Hello World! when a Greeting(\"World\") is sent to it" in {
      val props = Props(new Greeter(Some(testActor)))
      val greeter = system.actorOf(props, "greeter")
      greeter ! Greeting("World")
      expectMsg("Hello World!")
    }
  }
}
