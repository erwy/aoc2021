package aoc2021.day2

import aoc2021.utils.Input

class Day {
    private val input = Input("day2.txt").lines()
    private val example = Input("example2.txt").lines()

    private val parts = listOf(Part1(input), Part2(input))
    private val exampleParts = listOf(Part1(example), Part2(example))

    fun run() {
        exampleParts.map { println(it.run()) }
        parts.map { println(it.run()) }
    }
}

fun main() {
    println(Day().run())
}
