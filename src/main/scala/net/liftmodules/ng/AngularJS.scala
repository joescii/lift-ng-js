package net.liftmodules.ng

import net.liftweb.http.DispatchSnippet
import scala.xml.NodeSeq

object AngularJS extends DispatchSnippet {
  override def dispatch = {
    case _ => { _ => render }
  }
  
  def render: NodeSeq = NodeSeq.Empty
}