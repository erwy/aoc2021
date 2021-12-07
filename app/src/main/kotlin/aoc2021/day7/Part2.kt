package aoc2021.day7

import aoc2021.utils.Part
import kotlin.math.abs

class Part2(private val input: List<String>) : Part {
    override fun run(): Long {
        val map = input.flatMap { it.split(",") }.map { it.toInt() }
        var fuel = 0
        for (horizontalPosition in map.minOf { it }..map.maxOf { it }) {
            val fuelForPosition = map.fold(0) { totalFuel,
                                                position ->
                calculateFuel(position, horizontalPosition) + totalFuel
            }
            if (fuel == 0 || fuel > fuelForPosition) {
                fuel = fuelForPosition
            }
        }
        return fuel.toLong()
    }

    private fun calculateFuel(position: Int, horizontalPosition: Int) =
        generateSequence(1) { it + 1 }.take(abs(position - horizontalPosition)).sum()
}