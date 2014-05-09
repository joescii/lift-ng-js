package net.liftmodules.ng

import net.liftweb.http.{LiftRules, DispatchSnippet}
import scala.xml.NodeSeq

object AngularJS extends DispatchSnippet {
  private var modules:Seq[String] = Seq()

  def init(modules:Seq[String]) = {
    this.modules = modules

    LiftRules.snippetDispatch.append {
      case "AngularJS" => this
    }
  }

  override def dispatch = {
    case _ => { _ => render }
  }

  def render: NodeSeq = NodeSeq.Empty
}