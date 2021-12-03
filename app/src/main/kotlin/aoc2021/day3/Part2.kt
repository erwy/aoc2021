package aoc2021.day3

import aoc2021.utils.Part

class Part2(private val input: List<String>) : Part {
    override fun run(): Long {
        var oxygenStrings = input.toMutableList()
        for (index in 0 until indexToPairMap(oxygenStrings).keys.size) {
            val mapOfIndexToPair = indexToPairMap(oxygenStrings)
            // Oxygen
            val pair = mapOfIndexToPair[index]
            if (oxygenStrings.size != 1 && pair != null) {
                val toKeep = if (pair.first > pair.second) {
                    '0'
                } else if (pair.first < pair.second) {
                    '1'
                } else {
                    '1'
                }
                oxygenStrings.removeIf { it[index] != toKeep }
            }
        }

        var co2Strings = input.toMutableList()
        // C02
        for (index in 0 until indexToPairMap(co2Strings).keys.size) {
            val mapOfIndexToPair = indexToPairMap(co2Strings)
            val pair = mapOfIndexToPair[index]
            if (co2Strings.size != 1 && pair != null) {
                val toKeep = if (pair.first > pair.second) {
                    '1'
                } else if (pair.first < pair.second) {
                    '0'
                } else {
                    '0'
                }
                co2Strings.removeIf { it[index] != toKeep }
            }
        }


        return Integer.parseInt(oxygenStrings.first(), 2).toLong() * Integer.parseInt(co2Strings.first(), 2).toLong()
    }

    private fun indexToPairMap(input: List<String>): MutableMap<Int, Pair<Int, Int>> {
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
        return mapOfIndexToPair
    }
}