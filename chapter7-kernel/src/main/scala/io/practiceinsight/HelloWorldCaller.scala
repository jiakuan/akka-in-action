package io.practiceinsight

import akka.actor.{Actor, ActorLogging, ActorRef}

import scala.concurrent.duration._

/**
 * @author jiakuanwang
 */
class HelloWorldCaller(timer: FiniteDuration, actor: ActorRef) extends Actor with ActorLogging {

  case class TimerTick(msg: String)

  override def preStart(): Unit = {
    super.preStart()

    val system = akka.actor.ActorSystem("system")
    //Use the system's dispatcher as ExecutionContext
    import system.dispatcher

    context.system.scheduler.schedule(
      timer, timer, self, new TimerTick("everybody")
    )
  }

  def receive = {
    case msg: String => log.info("received {}", msg)
    case tick: TimerTick => actor ! tick.msg
  }
}
