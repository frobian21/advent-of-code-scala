package `2022`

import org.junit.Test
import org.junit.Assert._

class Day2Spec {
    
    val testData = """
A Y
B X
C Z
    """
    @Test
    def t1(): Unit = {
        val parsedTestData: List[(Move, Move)] = List(
            (Move.Rock, Move.Paper),
            (Move.Paper, Move.Rock),
            (Move.Scissors, Move.Scissors)
        )
        assertEquals(parsedTestData, Day2.parseMoves(testData))
        assertEquals(List(8, 1, 6), Day2.getRoundScores(parsedTestData))
        assertEquals(8 + 1 + 6, Day2.getTotalScore(parsedTestData))
    }
    @Test
    def t2(): Unit = {
        val parsedTestData: List[(Move, Result)] = List(
            (Move.Rock, Result.Draw),
            (Move.Paper, Result.Loss),
            (Move.Scissors, Result.Win)
        )
        assertEquals(parsedTestData, Day2.parseMovesAndResults(testData))
        assertEquals(List(4, 1, 7), Day2.getRoundScores(parsedTestData))
        assertEquals(4 + 1 + 7, Day2.getTotalScore(parsedTestData))
    }
}