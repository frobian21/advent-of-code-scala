object Day3 {

  def countTrees(unsplit_input: String)(right: Int, down: Int): Int = {
    val input = unsplit_input.split("""\s+""").filter(_.nonEmpty).toList
    (0 to (input.length - 1) / down)
      .map(i => input((i * down))((i * right) % input(0).length))
      .count(_ == '#')
  }

  def getAllSlopesForTrees(input: String): List[Int] = List(
    countTrees(input)(1, 1),
    countTrees(input)(3, 1),
    countTrees(input)(5, 1),
    countTrees(input)(7, 1),
    countTrees(input)(1, 2)
  )

  def calculateAllSlopes(input: String): Long =
    getAllSlopesForTrees(input).map(_.toLong).product

}

object Day3Runner {

  val example_input: String =
    """....#...#####..##.#..##..#....#
..##.#.#.........#.#......##...
#.#.#.##.##...#.......#...#..#.
..##.............#.#.##.....#..
##......#.............#....#...
.....##..#.....##.#.......##..#
.##.....#........##...##.#....#
.##......#.#......#.....#..##.#
##....#..#...#...#...##.#...##.
##........##.#...##......#.#.#.
..#.#........#...##.....#.....#
..#.......####.#....#..#####...
.##..#..#..##.#.....###.#..#...
......###..##.....#.#.#..###.#.
..#.#...#..##.....#....#.#.....
.....#.#...#.###.#..#..........
##.....#...#.#....#..#.#.......
..#...#...#.........##......#..
......#.#...#...#..#...##.#...#
....#.................##.##....
...#......#.............#....##
##..#..#..........#...##.#.#...
....#...##....#..#.#...........
##.#.#.#...#....#........#..#.#
...###..........#...#...#..##.#
..##.......###.#......##.##....
...........#.#....#.....#.#...#
..#......##.#...##.#.#......#.#
..........#.#....#.#..#....#...
##..##...##.......#.#....#.#.##
.##..#.#..#...........#.#...#.#
#......##......#....####.#....#
..###......##...#...#.#.......#
.#.##.##....##..#..##...#......
.#....#..#........#..#.##.#.#..
..#.........#.#.###....###.#...
..#..#.#.#..#..#.##.##...####..
#..#..#......#..#.#....#.#.#.##
..#.........#...#..#.#.#..#...#
#..#......###.....##....##.....
#..#.....#.#.#.##.....##...#.#.
##..#.#...#.........#.#........
#....#.......#.....#..#..#.#...
...###.##.###.###.#####..#...#.
.....#..#.#..##...............#
..#.....###.###.#.....#.#....##
###.#.........#..#.#.#..#.#..#.
.##.........#..#..##....#.#...#
.#...#........#...#.....#....#.
####..........###....#.#.#....#
....##..###....#....#.#...#....
..............##......##..#.###
.#...........###.#.#....#......
###.#..#..#...#.........##.....
..#.....##...#.#.....##...#.##.
.###.#........#..#.#...#.#..##.
.......##....##.........##.#..#
#....#...#...##...#.#..#..#..##
...#...##..#...#...#.#....#.#.#
#.#......#.#...##......#.#...##
.#.###..#.###.#.....#.##.##.#.#
#...#............#...#.##..##..
....#..###.......#.....##....#.
.#####..#.....#.....#...#.....#
..##..#..###.......##.#........
.#...##.##.....#.##...##...#..#
......###...#....#....#........
....#...#.#....#...#.#.#......#
....#..##...##.#..#....###.....
...#...#..#.#...#....#.#..#####
####....#.....#.........#.#....
...###.#.#..#.#..##............
.##..#####..#...#..#..#.......#
.###......#.#.#..#....#.....#..
#....##.##..#.#...............#
...#.#..#........#......#....#.
#.....#....###....#..#.#.#.....
.#..#....#...#.#.....##....#...
..#.##.#.##.#..#.##.#.....#.#..
.......#.......###..###..#...#.
.#.......#..#........#.#.......
.#.#...#.....#.##..##.....#....
#.......##....#......#.....##..
.#.....#...##...#..##.....#....
....#..#.#.......#.#.#.........
..#....#....##.##..#..##.##.#..
.#...#....##...#........#....#.
#.#......#...#....#...........#
.#....#..#..###.#.....#..#.....
..#..................#.....#...
..#...###..#..####.#..#.#.#.#..
...#........##...##..##..#....#
...#.....#........##..#.....#.#
#....#.....##.##......#...##...
...#####....#..##..##...#.#....
###.........#.#..#..#..##.#...#
##...#..##...#.##.#........#...
.#....#.#...#..#...#..#.#......
.#......##.#...#...#..#....#...
#..#.#.#......##.##.####..#....
.#...#.#.##...##.#...#...#.....
####.#.........#...##..##....##
.....###........###.##...#.#...
.##.....#.....#....##.....##...
.#.#...#####....##...##.....#..
....###..........#......##..#.#
..#.....#....#..#...#.....##..#
...##.##.#.######....#.#....##.
...#.#.#...#..#....##.........#
.#.#...##...#....#.#....##.....
...#..#.....#.....#.##.....#...
.#.#.#.....#.##.#....#.#....##.
#...#......###...#..###...#....
...##.#.#..#........##.......#.
.####.####......#........#.....
....#..#####....#......####....
#...##.#..#..#####.#...#......#
#.#....#..#.........###........
.##.........#....#......#.#....
...###.........####.#........##
..#..#........#.#..##......#..#
.##..#....#...##.####.#...#....
......#.......#..#..#.#.##.#...
.###....#.#...#.#.......##..#.#
#...#....#............#####....
...#.##......#####..#........#.
..#...##.....#...#..#.#........
...#.#...#...##...#..#....#....
..#..##.....#....#.#.###.......
.......##..#...#.............##
.....#.....#..##.##.....#......
.....##...#......#..##....#.###
.#...#.#.#.#.##.....##..###..#.
....##..........#.....###......
....#...#.#.#..#.......#....#..
..###...#...........##..###....
...#.##.......#....#....#.#....
##...#..##..#.##..........##..#
.##.....#..#......##..####.#.##
....##..#.#.###......#..#...#..
####..#.#....#...#....###.#.#..
###......#...##.##..#.##..#..#.
..#.#..#.#.#.....#...#..#.####.
.###.#...##...##....##......#.#
..#............#.##..#....#..#.
###.......#......###..#........
....##......###.....#.#..###...
..#...##...#......#..#.........
#..####.#....#.....###....#.#..
.#.#.#.......##....###.........
.......#.##.#####....#.#...####
.#...#....#....#.###..#.....#..
.###.#.#.###.###.#..####.##.#..
....#.........#.#.......##.....
#..#..#.#...........#.#.##..#.#
.#.....#..#...#.....#.##......#
..###.#............#.....####..
#.....##..##...#....####....#..
...#.....#..................#..
....#.###.#..#..#..##..#..##...
...##.#........#......#...##...
#................##....#...#...
..##......##.#.##..#....#.....#
.#..#.....#..........##.#.#....
.....#...####....#..#......#...
..#......###.#.#.#.#.......#..#
.##......#.......#....###.#....
#..#.#.#..#...#.#.##..##..#....
....#...##..#.#......#.##...#..
...###...#.##..#...#....#......
##......#.#...#.#.#.........#..
..#..........#...###.#.##....#.
...##.....#.....#...###..#.....
..####.#.....#.#.....#..#.#....
.#.....##...##.##.#.....#.####.
.......#.....#...##..........#.
.#...#.#....#####....###.#..#.#
.##.##....##...##.#.....#......
#......#.##..#..##.#.#.......#.
.#..#....###..#........##...#..
..#......##.......###..##...#..
.#..........#.#.......##.....#.
....##.....##.#.#.##........##.
..#.#..###..#..##...#.##...#...
.......#.....#..#...#...#.....#
##.#...#.#.#.##........#......#
..###.....##..#....#.......##..
#####..####...#.#..##.#...#..#.
#...####....#........#....#....
.#.#.#..#...##....#.......#.#..
...#....##..##..#..#..#####.###
...#......#.#..#......#...####.
.##.....##.##.#.####.#..##...#.
....#..#..##..##....#....#...##
##.###........#...##....#.....#
..#.#.#.......#....#..#....#...
......##.....##....#...#.....#.
#.#..#.#.......#....#.#.#......
.....###..#...#.....#..##..#...
......###.....#.#.#...#...##..#
.#..#.#....##...#...#........#.
#..###.#...####.#...#..........
.#.##.#..#..##..#..###..##...#.
.......#.#..........#.........#
#......###..##..#....###.......
..#............#.#........#...#
..##.#.............#......#..##
.#....#..#.#..#....###..#...#..
....##....#..##...###....#....#
.#....###.............#........
#..#...#..#....#.##.#.....##...
...........#.....#....#....#...
.##.##.#...#....##......##..##.
......#.#.##.#..##........#...#
....##...##...#...#...#.#......
.#...#....#...#......#.#...#..#
........##.....#.#..#...##..##.
##...#.....#.....####...#..#...
.#.#..##.##......#...#.#...#...
##...##.#......#....#.######.#.
##.....####.###......#.##.#....
.#.##....##........#...#..####.
.......#..#....##...#.#...#..#.
...##..........#..#........#..#
.##.....#...#...#.##.###.......
.##....#...#.#..#.....#...#....
..#...#.....#.####.#.........#.
#...#.##...#.#..#.#..#.###.#..#
.##..#.#.##.........####....###
.#..##........#..#.......#.....
......#.#####.#.........#.#...#
......#....#.#####...........#.
..##....##..#.#..#....#......##
#.#......#.##.#.##....#....#.#.
..#..##.#...#.......##.........
.....##.#...#..........#.......
...#........#..#...#.....##.###
....##.........#...#.#.....#...
.......#.#....#...#.......#...#
.#..#...##....#..#...........#.
.#....##.##.#..#..####.#.#.....
.##........#.....#..#......##..
.#..##......#......#..##..#....
###.....##.......#..##.#.......
.....##......#.#...##...##.....
.##....##..#..#####...#...#.##.
##...##.#....##.#.#.#....##....
.#.....#...#......#......##....
##.#............#...#....#.....
#..#.....#.....##.##.##..#..##.
......#..............#..#...#.#
....#.#....##......#..#...#....
.#...#..#...#......#..##....#.#
.....#......#..##...#.#....#...
#...............#.##..#......#.
.....#..##.#..#.#...###.....#..
...#..#..#...#....#..#..##.#...
.#...#...#..#......##...#......
....###............#.#.#....#..
#.#...#..#..#.#....#........#..
....#.#.#..#..#....#..#...##.#.
.#....#.#...#....#......#.#...#
##..#.#.#..#.....#...###....#.#
.##.....#.#...#..........#..#.#
#....#......#....#.#.#...#.....
#.#.....#.###.......#..#..#.#..
#.....##..#.###...#...##...#.##
.#.##....#.#.....##......###...
.#.......##................#...
.........#........####......#..
...##.###..#.....#.#.....##.#..
..#....#.#.#.##..........#.....
#..##.....#.............##.....
.##...#..#.......#.......#..#..
...#.................#......##.
....#....#.....#..###.#....###.
..#.#..#...#..#.....###....#.#.
.....###...#....#....#.#.##..##
...#...#.........####........#.
.......#..##.........#.........
.#......##.....#.#####...##....
....#.###...#.#....##..#......#
.##..#....#.#....#..#.###.....#
..#...#.#...#.##.....#.#....##.
#.#.#.#.....##...#.#..##..#....
.#............#.#.#..#...#...#.
...##.#..#..####.#.###..##.....
.##.....#.......#..##.#...##.#.
#.#...#..#.##...##...####..#..#
...##.......#.#.#.#.#.#...#..##
...#.#.##..##..............###.
.....#...#........#...#......#.
..#..#..##....#..##.#.....#....
#.....##........#.........##.#.
###..#....#.##..##.............
.#..#...#.#......#..#..##.....#
...#.#.#............##........#
..#.#....#..#....##....#...##.#
...##...#...#..........##.#####
....#..#.#.......##....#.#.###.
##..#..#..#...###.#.....#......
....###.#.#.#.##..##.#...#.....
.....####..#.#..#.#......#.#.#.
#.....#...#..#.#.........#..#..
.##....#.#.####......##..#..##.
......#.##.#.#..#..#....#.#....
.#..#...#...#...#..#.....#.....
..##.#..............#......#...
.....###.##.......#.....#..#...
..#.#..#..#.......#...##.##..#.
##.###......#......#.#..#..##..
..##.....#..#..#......#..#.....
...##.......#.#..#.........#.#.
......##.##.#.......#..#.#.....
#......#........##..#.......#.#
###....#...#...#.#...#..#..#...
#..###....#....####..#...#.....
....##..#.##.#....#..##...#.#..
#.##..#....##..#...#..#.#.#..#.
#.........#.....#...#.......#..
...#.....#.#.....#........##...
..#.##..#......#...#.....##.#..
...###....#.....#...#..#.##..#."""

  println(Day3.calculateAllSlopes(example_input))

}
