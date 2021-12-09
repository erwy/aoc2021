package aoc2021.day8

import aoc2021.utils.Part

class Part2(private val input: List<String>) : Part {
    override fun run(): Long {
        return input.map { it.split("|") }.map {
            val signals = it[0]
                .split(" ")
                .filter { it.isNotEmpty() }
                .groupBy { it.length }.toMutableMap()
            val one = signals[2]?.first()?.toSet() ?: emptySet()
            val four = signals[4]?.first()?.toSet() ?: emptySet()
            val seven = signals[3]?.first()?.toSet() ?: emptySet()
            val eight = signals[7]?.first()?.toSet() ?: emptySet()
            val three =
                signals[5]?.first { it.toSet().containsAll(seven) }?.toSet() ?: emptySet()
            val nine = four.union(three)
            val zero =
                signals[6]?.first { it.toSet().containsAll(one) && it.toSet().minus(nine).isNotEmpty() }?.toSet()
                    ?: emptySet()
            val five =
                signals[5]?.first { nine.containsAll(it.toSet()) && !it.toSet().containsAll(three)}?.toSet() ?: emptySet()
            val two =
                signals[5]?.first {
                    it.toSet().minus(five).isNotEmpty()
                            && it.toSet().minus(three).isNotEmpty()
                }?.toSet() ?: emptySet()

            val six =
                signals[6]?.first {
                    it.toSet().minus(nine).isNotEmpty()
                            && it.toSet().minus(zero).isNotEmpty()
                }?.toSet() ?: emptySet()

            val mapOf = mapOf(
                zero to '0',
                one to '1',
                two to '2',
                three to '3',
                four to '4',
                five to '5',
                six to '6',
                seven to '7',
                eight to '8',
                nine to '9',
            )
            it[1].split(" ")
                .filter { it.isNotEmpty() }
                .map { it.toSet() }
                .map { mapOf[it] ?: "" }
                .joinToString(separator = "")
                .toInt()
        }.sum().toLong()
    }

}