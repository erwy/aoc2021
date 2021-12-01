package aoc2021.day1

import aoc2021.utils.Part

class Part1(private val input: List<String>) : Part {

    override fun run(): Long {
        return input.map { it.toLong() }
            .windowed(2)
            .count { it[0] < it[1] }.toLong()
    }
}
