package `2022`

import org.junit.Test
import org.junit.Assert._

class Day8Spec {  
  val testInput: String = """
30373
25512
65332
33549
35390
"""
  val inputParsed: Array[Array[Tree]] = Array(
      Array(Tree(3, true), Tree(0, true), Tree(3, true), Tree(7, true), Tree(3, true)),
      Array(Tree(2, true), Tree(5, true), Tree(5, true), Tree(1, false), Tree(2, true)),
      Array(Tree(6, true), Tree(5, true), Tree(3, false), Tree(3, true), Tree(2, true)),
      Array(Tree(3, true), Tree(3, false), Tree(5, true), Tree(4, false), Tree(9, true)),
      Array(Tree(3, true), Tree(5, true), Tree(3, true), Tree(9, true), Tree(0, true))
    )
  
  @Test
  def t1(): Unit = {
    val result = Day8.parseGrid(testInput)
    assertArrayEquals(
      "Parsed grid contains expected heights",
      inputParsed.flatten.map(_.height),
      result.flatten.map(_.height)
    ) 
    assertArrayEquals(
      "Parsed grid contains default visibilities",
      inputParsed.flatten.map(_ => false),
      result.flatten.map(_.visible)
    )
    assertArrayEquals(
      "Update visibility produces expected update to _.isvisible",
      inputParsed.flatten.map(_.visible),
      Day8.mutableUpdateVisibility(result).flatten.map(_.visible)
    )
    assertEquals(
      "countVisible counts the number of visible trees",
      21,
      Day8.countVisible(Day8.parseGrid(testInput))
    )
  }
  @Test
  def immutableUpdatVisibility(): Unit = {
    val result = Day8.updateVisibility(Day8.parseGrid(testInput))
    assertArrayEquals(
      inputParsed.flatten.map(_.visible),
      result.flatten.map(_.visible)
    )
  }
  @Test
  def t2(): Unit = {
    val grid = Day8.parseGrid(testInput)
    val expectedTreeCountRight: Array[Array[Int]] = Array(
      Array(2, 1, 1, 1, 1),
      Array(1, 1, 2, 1, 1),
      Array(4, 3, 1, 1, 1),
      Array(1, 1, 2, 1, 1),
      Array(1, 2, 1, 1, 1),
    )
    assertArrayEquals(expectedTreeCountRight.flatten, Day8.getVisibleTreesRight(grid).flatten)
    val expectedTreeCountLeft: Array[Array[Int]] = Array(
      Array(1, 1, 2, 3, 1),
      Array(1, 1, 1, 1, 2),
      Array(1, 1, 1, 1, 1),
      Array(1, 1, 2, 1, 4),
      Array(1, 1, 1, 3, 1),
    )
    assertArrayEquals(expectedTreeCountLeft.flatten, Day8.getVisibleTreesLeft(grid).flatten)
    val expectedTreeCountDown: Array[Array[Int]] = Array(
      Array(2, 1, 1, 4, 3),
      Array(1, 1, 2, 1, 1),
      Array(2, 2, 1, 1, 1),
      Array(1, 1, 1, 1, 1),
      Array(1, 1, 1, 1, 1),
    )
    assertArrayEquals(expectedTreeCountDown.flatten, Day8.getVisibleTreesDown(grid).flatten)
    val expectedTreeCountUp: Array[Array[Int]] = Array(
      Array(1, 1, 1, 1, 1),
      Array(1, 1, 1, 1, 1),
      Array(2, 1, 1, 2, 1),
      Array(1, 1, 2, 3, 3),
      Array(1, 2, 1, 4, 1),
    )
    assertArrayEquals(expectedTreeCountUp.flatten, Day8.getVisibleTreesUp(grid).flatten)
    val expectedScenicScore: Array[Array[Int]] = Array(
      Array(4, 1, 2, 12, 3),
      Array(1, 1, 4, 1, 2),
      Array(16, 6, 1, 2, 1),
      Array(1, 1, 8, 3, 12),
      Array(1, 4, 1, 12, 1),
    )
    assertArrayEquals(expectedScenicScore.flatten, Day8.getScenicScore(grid).flatten)
    assertEquals(16, Day8.getMaxScenicScore(grid))
    assertEquals(16, Day8.highestPotentialScenicScore(grid))
  }

}
