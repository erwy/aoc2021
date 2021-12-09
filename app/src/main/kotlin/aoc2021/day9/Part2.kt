package aoc2021.day9

import aoc2021.utils.Part
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

class Part2(private val input: List<String>) : Part {
    override fun run(): Long {
        val columns = input.first().length
        val rows = input.size
        val heights = input.joinToString(separator = "") { it }.map(Character::getNumericValue)
        return heights.mapIndexed { index,
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
                value
            } else null
        }.filterNotNull().sum().toLong()

    }

}