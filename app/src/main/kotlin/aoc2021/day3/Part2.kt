package aoc2021.day3

import aoc2021.utils.Part

class Part2(private val input: List<String>) : Part {
    override fun run(): Long {
        var oxygenStrings = input.toMutableList()
        val mapOfIndexToPair = indexToPairMap(oxygenStrings)
        mapOfIndexToPair
        for () {

            // Oxygen
            mapOfIndexToPair.entries.forEach {
                if (oxygenStrings.size != 1) {
                    val index = it.key
                    val toKeep = if (it.value.first > it.value.second) {
                        '0'
                    } else if (it.value.first < it.value.second) {
                        '1'
                    } else {
                        '1'
                    }
                    oxygenStrings.removeIf { it[index] != toKeep }
                }
            }
        }

        var co2Strings = input.toMutableList()
        // C02
        while(oxygenStrings.size > 1) {
            val mapOfIndexToPair = indexToPairMap(co2Strings)
            mapOfIndexToPair.entries.forEach {
                if (co2Strings.size != 1) {
                    val index = it.key
                    val toKeep = if (it.value.first > it.value.second) {
                        '1'
                    } else if (it.value.first < it.value.second) {
                        '0'
                    } else {
                        '0'
                    }
                    co2Strings.removeIf { it[index] != toKeep }
                }
            }
        }


        return Integer.parseInt(oxygenStrings.first(),2).toLong() * Integer.parseInt(co2Strings.first(),2).toLong()
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