package pisa.agent

import de.unruh.isabelle.control.Isabelle
import pisa.server.PisaOS
import de.unruh.isabelle.mlvalue.Implicits._
import de.unruh.isabelle.pure.Implicits._

import scala.concurrent.ExecutionContext
import scala.util.control.Breaks

object RefactorTest {
  val path_to_isa_bin: String = "/home/qj213/Isabelle2021"
  val path_to_file: String = "/home/qj213/afp-2021-10-22/thys/FunWithFunctions/FunWithFunctions.thy"
  val working_directory: String = "/home/qj213/afp-2021-10-22/thys/FunWithFunctions"
  def main(args: Array[String]): Unit = {
    val pisaos = new PisaOS(
      path_to_isa_bin=path_to_isa_bin,
      path_to_file=path_to_file,
      working_directory=working_directory
    )
    implicit val isabelle: Isabelle = pisaos.isabelle
    implicit val ec: ExecutionContext = pisaos.ec

    var transition_count = 0
    val starting_time = System.currentTimeMillis()
    val continue = new Breaks
    for ((transition, text) <- pisaos.parse_text(pisaos.thy1, pisaos.fileContentCopy).force.retrieveNow) {
      continue.breakable {
        if (text.trim.isEmpty) continue.break
        else {
          transition_count += 1
          pisaos.singleTransition(transition)
        }
      }
    }
    val total_time = System.currentTimeMillis() - starting_time
    println(
      s"""Total time ${total_time}ms.
         |Total transitions ${transition_count}.
         |Time per transition: ${total_time/transition_count}ms.""".stripMargin)
  }
}
