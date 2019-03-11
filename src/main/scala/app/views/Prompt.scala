package scala.app.views

import scala.io.StdIn

object Prompt {

  def ask(message : String) : String = StdIn.readLine(message)

}

