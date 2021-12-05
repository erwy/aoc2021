package aoc2021.day5

import aoc2021.utils.Part

class Part1(private val input: List<String>) : Part {

    override fun run() = input.map { Line(it) }
        .filter { it.horizontal() || it.vertical() }
        .flatMap { it.points() }
        .groupingBy { it }
        .eachCount()
        .count { it.value > 1 }
        .toLong()
}
