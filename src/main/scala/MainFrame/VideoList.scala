package MainFrame

import java.text.SimpleDateFormat
import Download.RequestFailureException
import scalafx.scene.control.Label
import scalafx.scene.layout.{HBox, VBox}
import scalaj.http._
import play.api.libs.json.{JsArray, Json}

import scala.util.control.NonFatal


class VideoList extends VBox {
  def update(pageUrl: String): Unit = {
    children.clear()

    val res = Http("https://www.mirrativ.com/api/live/live_history")
      .params(Map("user_id" -> pageUrl, "page" -> "1"))
      .asString

    if (!res.isSuccess) {
      println(s"Http request did not succeed(StatusCode:${res.code})")
      throw new RequestFailureException(res.code)
    }
    else {
      try {
        val js = Json.parse(res.body)
        val lives = js \ "lives"
        lives.as[JsArray].value.foreach(v => {
          val liveid = (v \ "live_id").as[String]
          val title = (v \ "title").as[String]
          val stmp = (v \ "created_at").as[Long] * 1000
          val dfmt = new SimpleDateFormat("yyyy-MM-dd_HH:mm")

          children.add(new HBox {
            children = Seq(new DownloadButton("Download", liveid), Label(dfmt.format(stmp) + ":" + title))
          }.delegate)
        })
      } catch {
        case NonFatal(e) => {
          e.printStackTrace()
        }
      }
    }
  }
}
