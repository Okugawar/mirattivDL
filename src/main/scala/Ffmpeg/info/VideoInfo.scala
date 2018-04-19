package Ffmpeg.info

import Ffmpeg.Size

case class VideoInfo(codec: String,
                     width: Int,
                     height: Int,
                     fps: Double,
                     tags: Tags) extends Size {
  override def toString = {
    s"VideoInfo(codec: $codec, width: $width, height: $height, fps: $fps, tags: $tags)"
  }
}