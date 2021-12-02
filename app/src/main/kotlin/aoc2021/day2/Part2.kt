package aoc2021.day2

import aoc2021.utils.Part

class Part2(private val input: List<String>) : Part{
    override fun run(): Long {
        val pairs = input.map { it.split(" ") }
            .map { Pair(it[0], it[1].toLong()) }

        var depth = 0L
        var horizontal = 0L
        var aim = 0L
        pairs.forEach {
            when(it.first) {
                "forward" -> {
                    horizontal+=it.second
                    depth+=(aim*it.second)
                }
                "up" -> {
                    //depth-=it.second
                    aim-=it.second
                }
                "down" -> {
                    //depth+=it.second
                    aim+=it.second
                }
            }
        }
        return depth*horizontal
    }
}