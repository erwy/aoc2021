package aoc2021.day9

import aoc2021.utils.Input

class Day {
    private val input = Input("day9.txt").lines()
    private val example = Input("example9.txt").lines()

    private val parts = listOf(Part1(input), Part2(input))
    private val exampleParts = listOf(Part1(example), Part2(example))

    fun run() {
        println("Example")
        exampleParts.map { println(it.run()) }
        println("Input")
        parts.map { println(it.run()) }
    }
}

fun main() {
    println(Day().run())
}
