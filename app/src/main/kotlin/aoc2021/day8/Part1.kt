package aoc2021.day8

import aoc2021.utils.Part
import kotlin.math.abs

class Part1(private val input: List<String>) : Part {

    override fun run(): Long {
        val eachCount = input.map { it.split("|")[1] }
            .flatMap { it.split(" ") }
            .groupingBy { it.length }
            .eachCount()
        return ((eachCount[2] ?: 0) + (eachCount[4] ?: 0) + (eachCount[3] ?: 0) + (eachCount[7] ?: 0)).toLong()
    }
}
