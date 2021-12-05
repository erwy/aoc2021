package aoc2021.day5

data class Point(val x: Int, val y: Int) {
    constructor(point: String) : this(
        point.split(",")[0].toInt(),
        point.split(",")[1].toInt()
    )
}