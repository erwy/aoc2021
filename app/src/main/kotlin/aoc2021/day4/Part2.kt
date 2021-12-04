package aoc2021.day4

import aoc2021.utils.Part

class Part2(private val input: List<String>) : Part {
    override fun run(): Long {
        val noEmptyRows = input.filter { it.isNotEmpty() }.toMutableList()
        val tombola = noEmptyRows.removeFirst().split(",").map { Integer.parseInt(it) }
        val boards = noEmptyRows.windowed(5, 5).map { rows -> Board(rows) }.toMutableList()
        tombola.forEach { number ->
            boards.forEach { board -> board.check(number) }
            boards.removeIf { it.winning() && boards.size > 1 }
            val result = boards.firstOrNull { it.winning() }?.sumOfUnused()?.times(number)
            if (result != null) {
                return result.toLong()
            }
        }
        return 0
    }
}