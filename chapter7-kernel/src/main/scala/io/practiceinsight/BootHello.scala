package io.practiceinsight

import akka.actor.{Props, ActorSystem}
import akka.kernel.Bootable
import scala.concurrent.duration._

/**
 * @author jiakuanwang
 */
class BootHello extends Bootable {

  val system = ActorSystem("hellokernel")

  override def startup(): Unit = {
    val actor = system.actorOf(Props[HelloWorld])
    val config = system.settings.config
    val timer = config.getInt("helloWorld.timer")
    system.actorOf(Props(new HelloWorldCaller(timer millis, actor)))
  }

  override def shutdown(): Unit = {
    system.shutdown()
  }
}
