import collection.JavaConverters._
import scalafx.Includes._

import java.io.File
import java.io.FileInputStream
import java.io.IOException
import java.security.MessageDigest

import javafx.{concurrent => jfxc}
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.beans.property.StringProperty
import scalafx.collections.ObservableBuffer
import scalafx.concurrent.Task
import scalafx.scene.Scene
import scalafx.scene.control.TableColumn
import scalafx.scene.control.TableView
import scalafx.scene.control.cell.TextFieldTableCell
import scalafx.scene.input.DragEvent
import scalafx.scene.input.TransferMode
import scalafx.scene.layout.BorderPane


class FileInfo(path_ : String, sha1sum_ : String) {
  val path = new StringProperty(this, "path", path_)
  val sha1sum = new StringProperty(this, "sha1sum", sha1sum_)
}

/*
object SHA1CalculatorSFX extends JFXApp {


  private val pane = new BorderPane()

  stage = new PrimaryStage {
    scene = new Scene(600, 400) {
      title = "SHA-1 Calculator SFX"
      root = pane
    }
  }

  configureTable(pane)

  private def configureTable(root: BorderPane) = {
    val data = ObservableBuffer[FileInfo]()
    val table = createTableView(data)
    table.columnResizePolicy = TableView.ConstrainedResizePolicy
    root.center = table
  }

  private def createTableView(data: ObservableBuffer[FileInfo]) = {
    new TableView[FileInfo] {
      editable = true
      columns ++= List(
        new TableColumn[FileInfo, String] {
          text = "File Path"
          cellValueFactory = { _.value.path }
          cellFactory = TextFieldTableCell.forTableColumn[FileInfo]()
        }.delegate,
        new TableColumn[FileInfo, String] {
          text = "sha1sum"
          cellValueFactory = { _.value.sha1sum }
          cellFactory = TextFieldTableCell.forTableColumn[FileInfo]()
        }.delegate
      )
      items = data

      onDragOver = (event: DragEvent) => {
        val db = event.getDragboard()
        if (db.hasFiles()) {
          event.acceptTransferModes(TransferMode.COPY)
        } else {
          event.consume()
        }
      }

      onDragDropped = (event: DragEvent) => {
        val db = event.getDragboard()
        var success = false
        if (db.hasFiles()) {
          success = true
          for (file <- db.getFiles().asScala) {
            val fileinfo = new FileInfo(file.getAbsolutePath(), "")
            data.add(fileinfo)
            val task = new SHA1CalculationTask(fileinfo)
            fileinfo.sha1sum <== task.message
            new Thread(task).start()
          }
        }
        event.setDropCompleted(success)
        event.consume()
      }
    }
  }
}

class SHA1CalculationTask(fileinfo: FileInfo) extends Task(new jfxc.Task[Unit] {
  protected def call() = {
    val md = MessageDigest.getInstance("SHA-1")

    // update digest with each 8MB chunk
    val path = fileinfo.path.value
    val istream = new FileInputStream(path)
    val totalBytes = (new File(path)).length()
    var currentBytes = 0L
    try {
      val buf = new Array[Byte](8*1024*1024)
      Iterator.continually(istream.read(buf, 0, buf.length))
        .takeWhile(_ != -1)
        .foreach { n =>
          md.update(buf.slice(0, n))
          currentBytes += n
          val message = "calculating %d%%...".format(100 * currentBytes / totalBytes)
          updateMessage(message)
        }
    } catch {
      case e: IOException => e.printStackTrace()
    } finally {
      istream.close()
    }

    val hexdigest = md.digest().map("%02x".format(_)).mkString
    updateMessage(hexdigest)
  }
})
*/
