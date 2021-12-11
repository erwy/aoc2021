package aoc2021.day11

import aoc2021.utils.Part

class Part1(input: List<String>) : Part {

    private val octopus = input.joinToString(separator = "") { it }.map(Character::getNumericValue).toMutableList()
    private val columns = input.first().length
    private val rows = input.size
    var totalFlashed = 0L
    override fun run(): Long {

        for (step in 0 until 100) {
            val allFlashed = mutableSetOf<Int>()
            octopus.replaceAll { it + 1 }
            var flashed = octopus.mapIndexed { index, value ->
                if (value == 10) index else null
            }.filterNotNull().toSet()
            allFlashed.addAll(flashed)
            totalFlashed += flashed.size
            while (flashed.isNotEmpty()) {
                val neighboursToFlashed = flashed
                    .flatMap { neighbours(it) }
                neighboursToFlashed.forEach {
                    octopus[it]++
                }
                val flashedNeighbours = neighboursToFlashed.filter { octopus[it] > 9 && !allFlashed.contains(it) }.toSet()
                totalFlashed += flashedNeighbours.size
                allFlashed.addAll(flashedNeighbours)
                flashed = flashedNeighbours
            }
            octopus.replaceAll { if (it > 9) 0 else it }
        }
        return totalFlashed
    }

    private fun neighbours(flashedIndex: Int): List<Int> {
        val row = flashedIndex / columns
        val column = flashedIndex.mod(columns)
        val above = Pair(row - 1, column)
        val below = Pair(row + 1, column)
        val right = Pair(row, column + 1)
        val left = Pair(row, column - 1)
        val topLeft = Pair(row - 1, column - 1)
        val topRight = Pair(row + 1, column + 1)
        val bottomLeft = Pair(row + 1, column - 1)
        val bottomRight = Pair(row - 1, column + 1)
        return setOf(
            above,
            below,
            right,
            left,
            topLeft,
            topRight,
            bottomLeft,
            bottomRight
        ).filter {
            it.first in 0 until rows && it.second in 0 until columns
        }.map {
            it.first * columns + it.second
        }
    }
}
