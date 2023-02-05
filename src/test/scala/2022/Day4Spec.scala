package `2022`

import org.junit.Test
import org.junit.Assert._

class Day4Spec {
    
  val testData = """
2-4,6-8
2-3,4-5
5-7,7-9
2-8,3-7
6-6,4-6
2-6,4-8
"""
  val parsedTestData = List(
    ((2, 4), (6, 8)),
    ((2, 3), (4, 5)),
    ((5, 7), (7, 9)),
    ((2, 8), (3, 7)),
    ((6, 6), (4, 6)),
    ((2, 6), (4, 8))
  )
  @Test
  def t1(): Unit = {
    val expectedSubsets = List(false, false, false, true, true, false)
    assertEquals(parsedTestData, Day4.parseSets(testData))
    assertEquals(expectedSubsets, parsedTestData.map(Day4.anySubset))
    assertEquals(2, Day4.totalSubsets(parsedTestData))
  }
  @Test
  def t2(): Unit = {
    val expectedOverlaps = List(false, false, true, true, true, true)
    assertEquals(expectedOverlaps, parsedTestData.map(Day4.anyOverlap))
    assertEquals(4, Day4.totalOverlaps(parsedTestData))
  }
}
