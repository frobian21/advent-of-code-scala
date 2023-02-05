package `2022`

import org.junit.Test
import org.junit.Assert._

class Day3Spec {
    
    val testData = """
vJrwpWtwJgWrhcsFMMfFFhFp
jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL
PmmdzqPrVvPwwTWBwg
wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn
ttgJtRGJQctTZtZT
CrZsJsPPZsGzwwsLwLmpwMDw
    """
    val strToSet: String => Set[Char] = string => string.toCharArray().toSet
    val parsedTestData: List[(Set[Char], Set[Char])] = List(
      (strToSet("vJrwpWtwJgWr"), strToSet("hcsFMMfFFhFp")),
      (strToSet("jqHRNqRjqzjGDLGL"), strToSet("rsFMfFZSrLrFZsSL")),
      (strToSet("PmmdzqPrV"), strToSet("vPwwTWBwg")),
      (strToSet("wMqvLMZHhHMvwLH"), strToSet("jbvcjnnSBnvTQFn")),
      (strToSet("ttgJtRGJ"), strToSet("QctTZtZT")),
      (strToSet("CrZsJsPPZsGz"), strToSet("wwsLwLmpwMDw")),
    )
    val commonChars: List[Char] = List('p', 'L', 'P', 'v', 't', 's')
    val charValues: List[Int] = List(16, 38, 42, 22, 20, 19)
    @Test
    def t1(): Unit = {
      assertEquals(parsedTestData, Day3.parseRucksacks(testData))
      assertEquals(commonChars, parsedTestData.map(Day3.getCommonItem))
      assertEquals(charValues, commonChars.map(Day3.convertCharToInt))
      assertEquals(157, Day3.getTotalScore(parsedTestData))
    }
    @Test
    def t2(): Unit = {
      assertEquals(List('r', 'Z'), Day3.getCommonBadges(parsedTestData))
      assertEquals(70, Day3.getTotalBadgeScore(parsedTestData))
    }
}