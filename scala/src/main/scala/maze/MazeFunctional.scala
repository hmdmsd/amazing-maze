package maze

/** Functional implementation of the maze exploration
 * This version maintains immutability and uses recursion
 */
enum Exploration:
  case Explored, UnExplored

enum Maze:
  case Branch(label: String, left: Maze, right: Maze)
  case Leaf(label: String)

  /** Explores the maze and returns a list of visited node labels
   * This is the immutable functional version that returns a new list
   */
  def explore(): List[String] = this match
    case Branch(label, left, right) =>
      label :: left.explore() ::: right.explore()
    case Leaf(label) =>
      List(label)

object MazeFunctional extends App:
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

  // Run exploration and print result
  val result = branch0.explore()
  println(s"Functional exploration trace: ${result}")