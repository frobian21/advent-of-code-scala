package `2022`

import org.junit.Test
import org.junit.Assert._

class Day1Spec {
    @Test
    def t1(): Unit = {

        val testData = """
1000
2000
3000

4000

5000
6000

7000
8000
9000

10000"""
        val parsedTestData: List[Elf] = List(
                Elf(List(1000, 2000, 3000)),
                Elf(List(4000)),
                Elf(List(5000, 6000)),
                Elf(List(7000, 8000, 9000)),
                Elf(List(10_000))
        )
        assertEquals(
            parsedTestData,
            Day1.parseElfData(testData)
        )
        assertEquals(6000, Elf(List(1000, 2000, 3000)).totalCalories)
        assertEquals((4, 24000), Day1.getElfWithMostCalories(parsedTestData))
        assertEquals((List(4, 3, 5), 24000 + 11_000 + 10_000), Day1.getTopElvesWithMostCalories(parsedTestData, 3))
    }
}