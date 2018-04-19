package Download

import play.api.libs.json.Json
import scala.util.control.NonFatal
import scalaj.http._

class RequestFailureException(statusCode: Int) extends Throwable

case class VideoData(url: String, title: String, actor: String)

object Directory {
  def apply(id: String): Either[Throwable, VideoData] = {
    val url = "https://www.mirrativ.com/api/live/live"
    val res = Http(url)
      .params(Map("live_id" -> id))
      .asString

    if(!res.isSuccess) {
      println(s"Http request did not succeed(StatusCode:${res.code})")
      Left(new RequestFailureException(res.code))
    }
    else {
      try {
        val js = Json.parse(res.body)
        val playlistUrl = (js \ "archive_url_hls").as[String]
        val title = (js \ "title").as[String]
        val actor = (js \ "owner" \ "name").as[String]
        Right(VideoData(playlistUrl, title, actor))
      } catch {
        case NonFatal(e) => {
          Left(e)
        }
      }
    }
  }
}
