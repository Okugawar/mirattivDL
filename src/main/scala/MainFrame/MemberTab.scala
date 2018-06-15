package MainFrame

import scalafx.scene.control.{RadioButton, ToggleGroup}
import scalafx.scene.layout.{BorderPane, VBox}

abstract class Member(name: String, pageUrl: String) {
  def getName = name
  def getPageUrl = pageUrl
}

// 一期生
case object Mito extends Member("月ノ美兎", "2331446")
case object Chihiro extends Member("勇気ちひろ", "2332313")
case object Eru extends Member("える", "2331006")
case object Deron extends Member("樋口楓", "2328706")
case object Shizurin extends Member("静凛", "2331816")
case object Hajime extends Member("渋谷ハジメ", "2370347")
case object Aki extends Member("鈴谷アキ", "2329734")
case object Moimoi extends Member("モイラ", "2328064")

// 二期生
case object Utako extends Member("鈴鹿詩子", "2578026")
case object Ichigo extends Member("宇志海いちご", "2584554")
case object Mugimugi extends Member("家長むぎ", "2572637")
case object Riri extends Member("夕陽リリ", "2558470")
case object Alice extends Member("物述有栖", "2574970")
case object Cat extends Member("文野環", "2595644")
case object Gaku extends Member("伏見ガク", "2573925")
case object Gil extends Member("ギルザレンⅢ世", "2580900")
case object Rikiya extends Member("剣持刀也", "2573343")
case object Kaza extends Member("森中花咲", "2641794")

// SEEDS
case object Dola extends Member("ドーラ", "3254836")
case object Yaksa extends Member("海夜叉神", "3269165")
case object Azuma extends Member("名伽尾アズマ", "3254736")
case object Ikasumi extends Member("出雲霞", "3210908")
case object Kyoko extends Member("轟京子", "3254516")
case object Cleaire extends Member("クレア", "2886536")
case object Chaika extends Member("花畑チャイカ", "3306603")
case object Kizuq extends Member("社築", "3261125")
case object Momo extends Member("安土桃", "3254846")
case object DarknessEater extends Member("漆黒の捕食者", "3254428")
case object Midori extends Member("緑仙", "3295334")
case object Uduki extends Member("卯月コウ", "3255283")
case object Yuzu extends Member("八朔ゆず", "3254598")

// VOIZ
case object Kuroto extends Member("黒羽黒兎", "3315042")
case object Poaro extends Member("神成ポアロ", "3314510")
case object Naru extends Member("成瀬鳴", "3314398")
case object Air extends Member("春崎エアル", "3328152")



class MemberTab(vl: VideoList) extends BorderPane {
  val Members1: List[Member] = List(Mito, Chihiro, Eru, Deron, Shizurin, Hajime, Aki, Moimoi)
  val Members2: List[Member] = List(Utako, Ichigo, Mugimugi, Riri, Alice, Cat, Gaku, Gil, Rikiya, Kaza)
  val MembersS: List[Member] = List(Dola, Yaksa, Azuma, Ikasumi, Kyoko, Cleaire, Chaika, Kizuq, Momo, DarknessEater, Midori, Uduki, Yuzu)
  val MembersV: List[Member] = List(Kuroto, Poaro, Naru, Air)
  val allMembers: List[(String, List[Member])] = List(("一期生", Members1),
    ("二期生", Members2),
    ("SEEDS", MembersS),
    ("VOIZ", MembersV))

  def getGroupNum = allMembers.length
  def getNum(group: Int) = allMembers(group)._2.length
  def getMaxNum = allMembers.maxBy(e => e._2.length)

  val toggleMember: Map[String, VBox] = {
    var ret: Map[String, VBox] = Map()
    allMembers.foreach(g => {
      val vbox = new VBox
      val tg = new ToggleGroup()
      g._2.foreach(m => {
        val rb = new RadioButton(m.getName)
        rb.setToggleGroup(tg)
        vbox.children.add(rb)
        rb.onMouseClicked = {_ =>
          vl.update(m.getPageUrl)
        }
      })
      ret += (g._1 -> vbox)
    })
    ret
  }

  val boxGroup = new VBox
  val toggleGroup: ToggleGroup = {
    val ret = new ToggleGroup()
    allMembers.foreach(f = e => {
      val rb = new RadioButton(e._1)
      rb.setToggleGroup(ret)
      rb.onMouseClicked = { _ =>
        right = new VBox {
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

  left = boxGroup
  right = toggleMember(allMembers.head._1)
}
