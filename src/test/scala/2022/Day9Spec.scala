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
    RopeMove.Right(4), RopeMove.Up(4), RopeMove.Left(3), RopeMove.Down(1),
    RopeMove.Right(4), RopeMove.Down(1), RopeMove.Left(5), RopeMove.Right(2)
  )
  val testInput2: String = """
R 5
U 8
L 8
D 3
R 17
D 10
L 25
U 20
"""
  val parsedInput2: Array[RopeMove] = Array(
    RopeMove.Right(5), RopeMove.Up(8), RopeMove.Left(8), RopeMove.Down(3),
    RopeMove.Right(17), RopeMove.Down(10), RopeMove.Left(25), RopeMove.Up(20)
  )

  "parseRopeMoves" should {
    "parse input string correctly" in {
      parseRopeMoves(testInput) shouldBe parsedInput
      parseRopeMoves(testInput2) shouldBe parsedInput2
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
        Coordinate(0,0), Coordinate(4,0), Coordinate(4,4),
        Coordinate(1,4), Coordinate(1,3), Coordinate(5,3),
        Coordinate(5,2), Coordinate(0,2), Coordinate(2,2)
      )
    }
  }
  "Coordinate adjacentSet" should {
    "contain all the elements of the unitVector set + diagonals" in {
      val expectedElements = List[Coordinate]((1 ,1), (1, 0), (1, -1), (0, 1), (0, 0), (0, -1), (-1, 1), (-1, 0), (-1, -1))
      expectedElements should contain allElementsOf Coordinate.adjacentSet
    }
  }
  "isAdjacent" should {
    "return true" when {
      "Coordinate is within one unit in any direction (including diagonals)" in {
        val coordinates = Coordinate.adjacentSet
        for (coordinate <- coordinates) (Coordinate(0,0) - coordinate).isAdjacent shouldBe true
        for (coordinate <- coordinates) (Coordinate(3,3) - (coordinate + Coordinate(3,3))).isAdjacent shouldBe true

      }
    }
    "return false" when {
      "Coordinates are not within one unit of each other" in {
        val coordinates = Coordinate.adjacentSet
        for (coordinate <- coordinates if coordinate != Coordinate(0,0)) (Coordinate(0,0) - coordinate*2).isAdjacent shouldBe false
      }
    }
  }
  "applyTailMove" should {
    "append the first cooordinate to tail if the second coordinate is out of range" in {
      val tail: Array[Coordinate] = Array((0,0))
      moveRopeTail(tail, Array((1,0), (2,0))) shouldBe tail :+ Coordinate(1,0) 
    }
    "return tail if the second coordinate is in range" in {
      val tail: Array[Coordinate] = Array((0,0))
      moveRopeTail(tail, Array((1,0), (1,1))) shouldBe tail
    }
  }
  "applyAllTailMoves" should {
    val headMoves = applyAllHeadMoves(parsedInput)
    "track all the head moves" in {
      // headMoves should contain theSameElementsAs List.empty[Coordinate]
      val res = applyAllTailMoves(headMoves)
      res should contain theSameElementsInOrderAs List[Coordinate](
        (0,0), (1,0), (2,0), (3,0), (4,1), (4,2), (4,3),
        (3,4), (2,4), (3,3), (4,3),
        (3,2), (2,2), (1,2)
      )
      res.toSet should have size 13
    }
    "allow multiple knots and track the last knot" in {
      List(13, 7, 4, 3, 2, 1, 1, 1, 1).zip(1 to 10).foreach{
        case (moves, count) => applyAllTailMoves(headMoves, count).toSet should have size moves
      }
      List(5, 4, 3, 2, 1, 1, 1, 1, 1).zip(1 to 10).foreach{
        case (moves, count) => applyAllTailMoves(applyAllHeadMoves(parsedInput2.take(1)), count).toSet should have size moves
      }
      List(12, 10, 8, 6, 5, 4, 3, 2, 1).zip(1 to 10).foreach{
        case (moves, count) => applyAllTailMoves(applyAllHeadMoves(parsedInput2.take(2)), count).toSet should have size moves
      }
      List(19, 16, 13, 10, 8, 7, 6, 5, 4).zip(1 to 10).foreach{
        case (moves, count) => applyAllTailMoves(applyAllHeadMoves(parsedInput2.take(3)), count).toSet should have size moves
      }
      applyAllTailMoves(applyAllHeadMoves(parsedInput2.take(2)), 1) shouldBe Array[Coordinate]((0,0), (1,0), (2,0), (3,0), (4,0), (5,1), (5,2), (5,3), (5,4), (5,5), (5,6), (5,7))
      applyAllTailMoves(applyAllHeadMoves(parsedInput2.take(2)), 5) shouldBe Array[Coordinate]((0,0), (1,1), (2,2), (3,3), (4,4))
      applyAllTailMoves(applyAllHeadMoves(parsedInput2.take(2)), 8) shouldBe Array[Coordinate]((0,0), (1,1))
      applyAllTailMoves(applyAllHeadMoves(parsedInput2.take(2)), 9) shouldBe Array[Coordinate]((0,0))
      applyAllTailMoves(applyAllHeadMoves(parsedInput2.take(3)), 9) shouldBe Array[Coordinate]((0,0), (1,1), (2,2), (1,3))
      val res = applyAllTailMoves(applyAllHeadMoves(parsedInput2), 9)
      res.toSet.size shouldBe 36
    }
  }

}
