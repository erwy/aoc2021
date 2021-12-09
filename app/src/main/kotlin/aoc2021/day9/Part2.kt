package aoc2021.day9

import aoc2021.day5.Point
import aoc2021.utils.Part
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

class Part2(private val input: List<String>) : Part {
    private val heights = input.joinToString(separator = "") { it }.map(Character::getNumericValue)
    private val columns = input.first().length
    private val rows = input.size
    private val visited = mutableSetOf<Pair<Int, Int>>()
    override fun run(): Long {


        val basins = heights.mapIndexed { index,
                                             value ->
            val row = ((index) / columns) + 1
            val right = max((index + 1).mod(columns), index.mod(columns)) + (index / columns) * columns
            val left = min((index - 1).mod(columns), index.mod(columns)) + (index / columns) * columns
            val below = if (row == rows) index else (index + columns).mod(columns * rows)
            val above = if (row == 1) index else (index - columns).mod(columns * rows)
            if ((right == index || heights[right] > value)
                && (left == index || heights[left] > value)
                && (above == index || heights[above] > value)
                && (below == index || heights[below] > value)
            ) {
                Pair(row - 1, index.mod(columns))
            } else null
        }.filterNotNull().map { neighbours(it) }.sortedByDescending { it.size }

        return basins[0].size.times(basins[1].size).times(basins[2].size).toLong()
    }

    private fun neighbours(point: Pair<Int, Int>): Set<Pair<Int, Int>> {
        visited.add(point)
        val row = point.first
        val column = point.second
        val above = Pair(row - 1, column)
        val below = Pair(row + 1, column)
        val right = Pair(row, column + 1)
        val left = Pair(row, column - 1)
        val validNeighbours = setOf(above, below, right, left)
            .filter {
                it.first in 0 until rows
                        && it.second in 0 until columns
                        && heights[it.first * columns + it.second] < 9
                        && !visited.contains(it)
            }
        val resultingSet = mutableSetOf(point)
        resultingSet.addAll(validNeighbours)
        validNeighbours.forEach {
            val neighbours = neighbours(it)
            resultingSet.addAll(neighbours)
        }
        return resultingSet
    }


}