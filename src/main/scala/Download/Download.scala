package Download

import java.io.File
import java.nio.file.{Files, Paths}
import java.nio.file.StandardCopyOption.REPLACE_EXISTING

import Ffmpeg.transcode.{FFMPEGTranscoder, TranscodeListener}
import MainFrame.MessageLog
import scalaj.http._

import scala.util.control.NonFatal

class Download(filename: String, url: String) {
  def run() {
    val output = Paths.get(filename)
    if (Files.notExists(output)) Files.createFile(output)
    var prg: Double = 0.0
    val listener = new TranscodeListener {
      override def progress(percentage: Double, frame: Int, fps: Double, q: Double, size: Long, time: Double, bitRate: Long, elapsed: Double, finished: Boolean): Unit = {
        prg = percentage
      }

      override def log(message: String): Unit = {
        println(message)
        MessageLog.put(message)
      }
    }

    try {
      val t = FFMPEGTranscoder()
        .input(url)
        .webH264()
        .output(output.toFile)
      t.execute(Some(listener))

    } catch {
      case NonFatal(e) => e.printStackTrace()
    }
  }
}
