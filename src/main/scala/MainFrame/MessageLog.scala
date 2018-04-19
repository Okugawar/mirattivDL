package MainFrame

import scalafx.scene.control.Label
import scala.collection.immutable.Queue

object MessageLog extends Label {
  var buf = Queue[String]()
  val maxLength = 5
  val lab = new Label()

  def put(msg: String): Unit = {
    buf = buf.enqueue(msg)
    if(buf.size > maxLength) buf.dequeue

    lab.text = buf.reduce(_ + "\n" + _)
  }
}
