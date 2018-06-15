package MainFrame

import collection.JavaConverters._
import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.Scene
import scalafx.scene.control.Button
import scalafx.scene.layout.{BorderPane, VBox}
import Download._

object MainFrame extends JFXApp {
  stage = new PrimaryStage {
    title = "Hello"
    scene = new Scene(800.0, 600.0) {
      root = new BorderPane {
        val vl = new VideoList
        left = new MemberTab(vl)
        center = vl
        bottom = MessageLog
      }
    }
  }
}
