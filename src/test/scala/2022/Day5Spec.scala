package `2022`

import org.junit.Test
import org.junit.Assert._

class Day5Spec {
    
  val testData = """
    [D]    
[N] [C]    
[Z] [M] [P]
 1   2   3 

move 1 from 2 to 1
move 3 from 1 to 3
move 2 from 2 to 1
move 1 from 1 to 2
"""
  val parsedStacks = List("NZ", "DCM", "P")
  val parsedProcedures = List(
    Procedure(1, 2, 1),
    Procedure(3, 1, 3),
    Procedure(2, 2, 1),
    Procedure(1, 1, 2),
  )
  @Test
  def t1(): Unit = {
    assertEquals(parsedStacks, Day5.parseStacks(testData))
    assertEquals(parsedProcedures, Day5.parseProcedures(testData.split("\n\n").tail.head))
    assertEquals((parsedStacks, parsedProcedures), Day5.parseStacksAndProcedures(testData))
    assertEquals(List("", "DCM", "ZNP"), Day5.applyProcedure(Day5.singleBoxMove)(parsedStacks, parsedProcedures.tail.head))
    assertEquals("CMZ", Day5.applySingleBoxProceduresAndGetTopCrates(parsedStacks, parsedProcedures))
  }
  @Test
  def t2(): Unit = {
    assertEquals(List("", "DCM", "NZP"), Day5.applyProcedure(Day5.multiBoxMove)(parsedStacks, parsedProcedures.tail.head))
    assertEquals("MCD", Day5.applyMultiBoxProceduresAndGetTopCrates(parsedStacks, parsedProcedures))
  }
}
