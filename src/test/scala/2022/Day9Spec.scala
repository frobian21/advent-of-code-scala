package `2022`

import scala.language.implicitConversions

import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should

import Day9.*

given Conversion[(Int, Int), Coordinate] = Coordinate.apply.tupled(_)

class Day9Spec extends AnyWordSpec with should.Matchers {   
  val testInput: String = """
R 4
U 4
L 3
D 1
R 4
D 1
L 5
R 2
"""
  val parsedInput: Array[RopeMove] = Array(
    RopeMove.Right(4),
    RopeMove.Up(4),
    RopeMove.Left(3),
    RopeMove.Down(1),
    RopeMove.Right(4),
    RopeMove.Down(1),
    RopeMove.Left(5),
    RopeMove.Right(2)
  )

  "parseRopeMoves" should {
    "parse input string correctly" in {
      parseRopeMoves(testInput) shouldBe parsedInput
    }
  }
  
  "moveRopeHead" should {
    "add new steps to the input coordinates using the last value as the start, and step with move.unitVector" when {
      val inputCoordinates: Array[Coordinate] = Array((1,2), (3,1), (0, 0))
      "right" in {
        moveRopeHead(inputCoordinates, RopeMove.Right(1)) shouldBe inputCoordinates ++ Array[Coordinate]((1, 0))
      }
      "left" in {
        moveRopeHead(inputCoordinates, RopeMove.Left(1)) shouldBe inputCoordinates ++ Array[Coordinate]((-1, 0))
      }
      "up" in {
        moveRopeHead(inputCoordinates, RopeMove.Up(1)) shouldBe inputCoordinates ++ Array[Coordinate]((0, 1))
      }
      "down" in {
        moveRopeHead(inputCoordinates, RopeMove.Down(1)) shouldBe inputCoordinates ++ Array[Coordinate]((0, -1))
      }
    }
    "add multiple steps to the input correctly" when {
      val inputCoordinates: Array[Coordinate] = Array((1,2), (3,1), (4, 4))
      "right" in {
        moveRopeHead(inputCoordinates, RopeMove.Right(2)) shouldBe inputCoordinates ++ Array[Coordinate]((5, 4), (6, 4))
      }
      "left" in {
        moveRopeHead(inputCoordinates, RopeMove.Left(2)) shouldBe inputCoordinates ++ Array[Coordinate]((3, 4), (2, 4))
      }
      "up" in {
        moveRopeHead(inputCoordinates, RopeMove.Up(2)) shouldBe inputCoordinates ++ Array[Coordinate]((4, 5), (4, 6))
      }
      "down" in {
        moveRopeHead(inputCoordinates, RopeMove.Down(2)) shouldBe inputCoordinates ++ Array[Coordinate]((4, 3), (4, 2))
      }
    }
  }
  "applyAllHeadMoves" should {
    "apply all the moves and return the coordinates visited" in {
      applyAllHeadMoves(parsedInput) should contain inOrder (
        Coordinate(0,0),
        Coordinate(4,0),
        Coordinate(4,4),
        Coordinate(1,4),
        Coordinate(1,3),
        Coordinate(5,3),
        Coordinate(5,2),
        Coordinate(0,2),
        Coordinate(2,2))
    }
  }
  

}
