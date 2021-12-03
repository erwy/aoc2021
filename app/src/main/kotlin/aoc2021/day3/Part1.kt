package aoc2021.day3

import aoc2021.utils.Part

class Part1(private val input: List<String>) : Part {

    override fun run(): Long {
        val mapOfIndexToPair = mutableMapOf<Int, Pair<Int, Int>>()
        input.forEach {
            it.forEachIndexed { index, c ->
                val orDefault = mapOfIndexToPair.getOrDefault(index, Pair(0, 0))
                if (c == '0') {
                    mapOfIndexToPair[index] = orDefault.copy(orDefault.first + 1, orDefault.second)
                } else {
                    mapOfIndexToPair[index] = orDefault.copy(orDefault.first, orDefault.second + 1)
                }
            }
        }
        var gamma = ""
        var epsilon = ""
        mapOfIndexToPair.values
            .forEach {
                if (it.first > it.second) {
                    gamma += "0"
                    epsilon += "1"
                } else {
                    gamma += "1"
                    epsilon += "0"
                }
            }
    return Integer.parseInt(gamma,2).toLong() * Integer.parseInt(epsilon,2).toLong()
}
}
