// File: scala/src/test/scala/maze/MazeTest.scala
package maze

import org.junit.Test
import org.junit.Assert.*
import scala.collection.mutable.ListBuffer

class MazeTest:
  import maze.functional.Maze.{Branch as FBranch, Leaf as FLeaf}
  import maze.mutable.Maze.{Branch as MBranch, Leaf as MLeaf}

  val expectedTrace = List("0", "1", "2", "3", "4", "5", "6", "3", "7", "5", "8")

  @Test def testFunctionalMaze(): Unit =
    // Create test maze using Functional implementation
    val leaf2 = FLeaf("2")
    val leaf4 = FLeaf("4")
    val leaf5 = FLeaf("5")
    val leaf8 = FLeaf("8")
    val branch3 = FBranch("3", leaf4, leaf5)
    val branch1 = FBranch("1", leaf2, branch3)
    val branch7 = FBranch("7", leaf5, leaf8)
    val branch6 = FBranch("6", branch3, branch7)
    val branch0 = FBranch("0", branch1, branch6)

    val result = branch0.explore()
    assertEquals("Functional exploration should match expected trace",
      expectedTrace, result)

  @Test def testMutableMaze(): Unit =
    // Create test maze using Mutable implementation
    val leaf2 = MLeaf("2")
    val leaf4 = MLeaf("4")
    val leaf5 = MLeaf("5")
    val leaf8 = MLeaf("8")
    val branch3 = MBranch("3", leaf4, leaf5)
    val branch1 = MBranch("1", leaf2, branch3)
    val branch7 = MBranch("7", leaf5, leaf8)
    val branch6 = MBranch("6", branch3, branch7)
    val branch0 = MBranch("0", branch1, branch6)

    val trace = ListBuffer[String]()
    branch0.explore(trace)
    assertEquals("Mutable exploration should match expected trace",
      expectedTrace, trace.toList)