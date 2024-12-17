
package maze

import scala.collection.mutable.ListBuffer

/** Mutable implementation of the maze exploration using ListBuffer
 * This version maintains exploration state and uses a mutable buffer for the trace
 */
enum Exploration:
  case Explored, UnExplored

enum Maze:
  case Branch(label: String, left: Maze, right: Maze, var status: Exploration = Exploration.UnExplored)
  case Leaf(label: String)

  /** Explores the maze and records visited nodes in a mutable buffer
   * @param trace The mutable buffer to record the exploration trace
   */
  def explore(trace: ListBuffer[String]): Unit = this match
    case branch@Branch(label, left, right, status) =>
      status match
        case Exploration.UnExplored =>
          trace += label
          branch.status = Exploration.Explored
          left.explore(trace)
          right.explore(trace)
        case Exploration.Explored =>
          trace += label
    case Leaf(label) =>
      trace += label

object MazeMutable extends App:
  import Maze.*

  // Create test maze
  val leaf2 = Leaf("2")
  val leaf4 = Leaf("4")
  val leaf5 = Leaf("5")
  val leaf8 = Leaf("8")
  val branch3 = Branch("3", leaf4, leaf5)
  val branch1 = Branch("1", leaf2, branch3)
  val branch7 = Branch("7", leaf5, leaf8)
  val branch6 = Branch("6", branch3, branch7)
  val branch0 = Branch("0", branch1, branch6)

  // Test exploration with mutable buffer
  val trace = ListBuffer[String]()
  branch0.explore(trace)
  println(s"Mutable exploration trace: ${trace.toList}")