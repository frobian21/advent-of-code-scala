package `2022`

import org.junit.Test
import org.junit.Assert._

class Day6Spec {
  val testInputs = List(
    "mjqjpqmgbljsphdztnvjfqwrcgsmlb",
    "bvwbjplbgvbhsrlpgdmjqwftvncz",
    "nppdvjthqldpwncqszvftbrmjlhg",
    "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg",
    "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw"
  )
  @Test
  def t1(): Unit = {
    assertEquals(List(7,5,6,10,11), testInputs.map(Day6.findStartOfPacket))
  }
  @Test
  def t2(): Unit = {
    assertEquals(List(19, 23, 23, 29, 26), testInputs.map(Day6.findStartOfMessage))
  }
}
