package net.liftmodules.ng

import net.liftweb.http.{S, ResourceServer, LiftRules, DispatchSnippet}
import scala.xml.NodeSeq
import net.liftweb.util.Props
import net.liftweb.util.Props.RunModes

object AngularJS extends DispatchSnippet {
  private var modules:Seq[String] = Seq()

  def init(modules:String*) = {
    this.modules = modules

    LiftRules.snippetDispatch.append {
      case "AngularJS" => this
    }

    ResourceServer.allow {
      case "net" :: "liftmodules" :: "ng" :: "js" :: _ => true
    }
  }

  override def dispatch = {
    case _ => { _ => render }
  }

  def render: NodeSeq = {
    import js.BuildInfo.ngVersion
    val ms  = S.attr("modules").map(_.split(',').map(_.trim).toSeq).openOr(modules)
    val notDev = Props.mode != RunModes.Development
    val min = S.attr("min").map(bool(_, notDev)).openOr(notDev)
    ("" +: ms).map { m =>
      val name = if(m == "") "angular" else "angular-"+m
      val id = name+"_js"
      val src = "/classpath/net/liftmodules/ng/js/"+name+"-"+ngVersion+(if(min) ".min" else "")+".js"
      <script id={id} src={src} type="text/javascript"></script>
    }.foldLeft(NodeSeq.Empty)(_ ++ _)
  }

  private def bool(s:String, default:Boolean):Boolean = {
    val truthy = List("true", "yes", "on")
    val falsey = List("false", "no", "off")

    if(default) !falsey.find(_.equalsIgnoreCase(s)).isDefined
    else truthy.find(_.equalsIgnoreCase(s)).isDefined
  }

}