package io.practiceinsight

import akka.actor.{Actor, ActorLogging}

/**
 * @author jiakuanwang
 */
class HelloWorld extends Actor with ActorLogging {

  def receive = {
    case msg: String =>
      val hello = "Hello %s".format(msg)
      sender ! hello
      log.info("Sent response {}", hello)
    case obj =>
      val hello = "Hello world"
      sender ! hello
      log.info("Sent response {}", hello)
  }
}
