package net.liftmodules.ng

import net.liftweb.http.{ResourceServer, LiftRules, DispatchSnippet}
import scala.xml.NodeSeq

object AngularJS extends DispatchSnippet {
  private var modules:Seq[String] = Seq()

  def init(modules:Seq[String]) = {
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

  def render: NodeSeq = NodeSeq.Empty
}