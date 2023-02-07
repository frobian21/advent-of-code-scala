package `2022`

import org.junit.Test
import org.junit.Assert._

class Day7Spec {
  val testInput: String ="""
$ cd /
$ ls
dir a
14848514 b.txt
8504156 c.dat
dir d
$ cd a
$ ls
dir e
29116 f
2557 g
62596 h.lst
$ cd e
$ ls
584 i
$ cd ..
$ cd ..
$ cd d
$ ls
4060174 j
8033020 d.log
5626152 d.ext
7214296 k
"""
  @Test
  def t1(): Unit = {
    val movements = Root("/").addSubDir("a").addFile("14848514", "b.txt").addFile("8504156", "c.dat").addSubDir("d")
      .getSubDir("a").addSubDir("e").addFile("29116", "f").addFile("2557", "g").addFile("62596", "h.lst")
      .getSubDir("e").addFile("584", "i").parent.parent.getSubDir("d")
      .addFile("4060174", "j").addFile("8033020", "d.log").addFile("5626152", "d.ext").addFile("7214296", "k").parent
    assertEquals(movements, Day7.parseFileSystem(testInput))
    assertEquals(0, movements.size)
    assertEquals(48381165, movements.calculateSize())
    assertEquals(95437, Day7.getTotalSizeDirs(_ <= 100_000)(movements))
  }
  @Test
  def t2(): Unit = {
    val movements = Root("/").addSubDir("a").addFile("14848514", "b.txt").addFile("8504156", "c.dat").addSubDir("d")
      .getSubDir("a").addSubDir("e").addFile("29116", "f").addFile("2557", "g").addFile("62596", "h.lst")
      .getSubDir("e").addFile("584", "i").parent.parent.getSubDir("d")
      .addFile("4060174", "j").addFile("8033020", "d.log").addFile("5626152", "d.ext").addFile("7214296", "k").parent

    movements.calculateSize()
    assertEquals(24933642, Day7.getSmallestDir(movements))
  }
}
