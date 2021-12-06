package aoc2021.day6

import aoc2021.utils.Part

class Part2(private val input: List<String>) : Part {
     override fun run(): Long {
        var counterToNumberOfFish =
            input[0].split(",").map { it.toInt() }.groupingBy { it }.eachCount().mapValues { it.value.toLong() }
        for (i in 1..256) {
            val newMap = mutableMapOf<Int, Long>()
            val newFishThisRound = counterToNumberOfFish[0] ?: 0L
            newMap[8] = newFishThisRound
            newMap[0] = counterToNumberOfFish[1] ?: 0
            newMap[1] = counterToNumberOfFish[2] ?: 0
            newMap[2] = counterToNumberOfFish[3] ?: 0
            newMap[3] = counterToNumberOfFish[4] ?: 0
            newMap[4] = counterToNumberOfFish[5] ?: 0
            newMap[5] = counterToNumberOfFish[6] ?: 0
            newMap[6] = (counterToNumberOfFish[7] ?: 0) + newFishThisRound
            newMap[7] = counterToNumberOfFish[8] ?: 0
            counterToNumberOfFish = newMap
        }
        return counterToNumberOfFish.values.sum()
    }
}