package MainFrame

import Download.{Directory, Download}
import javafx.beans.property.ObjectProperty
import javafx.event.EventHandler
import javafx.scene.input.MouseEvent
import scalafx.Includes._
import scalafx.scene.control.Button

class DownloadButton(text: String, videoId: String) extends Button(text) {
  onMouseClicked = handle({
    println(videoId)
    Directory(videoId) match {
      case Left(e) => e.printStackTrace()
      case Right(video) => {
        println(video)
        println("start download...")
        new Download(video.title + "(" + video.actor + ").mp4", video.url).run()
        println("completed!")
      }
    }
  })
}
