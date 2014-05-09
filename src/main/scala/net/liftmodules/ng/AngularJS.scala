package net.liftmodules.ng

import net.liftweb.http.{S, ResourceServer, LiftRules, DispatchSnippet}
import scala.xml.NodeSeq

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
    val ms = S.attr("modules").map(_.split(',').map(_.trim).toSeq).openOr(modules)
    ("" +: ms).map { m =>
      val name = if(m == "") "angular" else "angular-"+m
      <script id={name+"_js"} src={"/classpath/net/liftmodules/ng/js/"+name+"-"+ngVersion+".js"} type="text/javascript"></script>
    }.foldLeft(NodeSeq.Empty)(_ ++ _)
  }
}