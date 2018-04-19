package MainFrame

import scalafx.scene.control.{RadioButton, ToggleGroup}
import scalafx.scene.layout.{BorderPane, HBox}

abstract class Members(name: String, pageUrl: String) {
  def getName = name
  def getPageUrl = pageUrl
}

// 一期生
case object Mito extends Members("月ノ美兎", "2331446")
case object Chihiro extends Members("勇気ちひろ", "2332313")
case object Eru extends Members("える", "2331006")
case object Deron extends Members("樋口楓", "2328706")
case object Shizurin extends Members("静凛", "2331816")
case object Hajime extends Members("渋谷ハジメ", "2370347")
case object Aki extends Members("鈴谷アキ", "2329734")
case object Moimoi extends Members("モイラ", "2328064")

// 二期生
case object Utako extends Members("鈴鹿詩子", "2578026")
case object Ichigo extends Members("宇志海いちご", "2584554")
case object Mugimugi extends Members("家長むぎ", "2572637")
case object Riri extends Members("夕陽リリ", "2558470")
case object Alice extends Members("物述有栖", "2574970")
case object Cat extends Members("文野環", "2595644")
case object Gaku extends Members("伏見ガク", "2573925")
case object Gil extends Members("ギルザレンⅢ世", "2580900")
case object Rikiya extends Members("剣持刀也", "2573343")
case object Kaza extends Members("森中花咲", "2641794")


class MemberTab(vl: VideoList) extends BorderPane {
  val Members1: List[Members] = List(Mito, Chihiro, Eru, Deron, Shizurin, Hajime, Aki, Moimoi)
  val Members2: List[Members] = List(Utako, Ichigo, Mugimugi, Riri, Alice, Cat, Gaku, Gil, Rikiya, Kaza)
  val allMembers: List[(String, List[Members])] = List(("一期生", Members1),
    ("二期生", Members2))

  def getGroupNum = allMembers.length
  def getNum(group: Int) = allMembers(group)._2.length
  def getMaxNum = allMembers.maxBy(e => e._2.length)

  val toggleMember: Map[String, HBox] = {
    var ret: Map[String, HBox] = Map()
    allMembers.foreach(g => {
      val hbox = new HBox
      val tg = new ToggleGroup()
      g._2.foreach(m => {
        val rb = new RadioButton(m.getName)
        rb.setToggleGroup(tg)
        hbox.children.add(rb)
        rb.onMouseClicked = {_ =>
          vl.update(m.getPageUrl)
        }
      })
      ret += (g._1 -> hbox)
    })
    ret
  }

  val boxGroup = new HBox
  val toggleGroup: ToggleGroup = {
    val ret = new ToggleGroup()
    allMembers.foreach(f = e => {
      val rb = new RadioButton(e._1)
      rb.setToggleGroup(ret)
      rb.onMouseClicked = { _ =>
        bottom = new HBox {
          children = toggleMember(e._1)
        }
      }
      if (ret.selectedToggle == null) {
        ret.delegate.selectToggle(rb)
      }
      boxGroup.children.add(rb)
    })
    ret
  }

  top = boxGroup
  bottom = toggleMember(allMembers.head._1)
}
