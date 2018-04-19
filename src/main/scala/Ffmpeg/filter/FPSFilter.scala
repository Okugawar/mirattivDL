package Ffmpeg.filter

case class FPSFilter(framesPerSecond: Double) extends VideoFilter {
  val value = s"fps=$framesPerSecond"
}
