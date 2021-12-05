package aoc2021.day5

import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

class Line(definition: String) {
    private val start: Point
    private val end: Point

    init {
        val startAndEnd = definition.split("->")
        start = Point(startAndEnd[0].trim())
        end = Point(startAndEnd[1].trim())
    }

    fun horizontal(): Boolean {
        return start.y == end.y
    }

    fun vertical(): Boolean {
        return start.x == end.x
    }

    fun points(): Set<Point> {
        val points = mutableSetOf(start)
        if (vertical()) {
            for (y in min(start.y, end.y)..max(start.y, end.y)) {
                points.add(Point(start.x, y))
            }
        } else if (horizontal()) {
            for (x in min(start.x, end.x)..max(start.x, end.x)) {
                points.add(Point(x, start.y))
            }
        } else {
            val xFactor = (end.x - start.x) / abs(end.x - start.x)
            val yFactor = (end.y - start.y) / abs(end.y - start.y)
            for (i in 1..abs(end.x - start.x)) {
                points.add(Point(start.x + (i * xFactor), start.y + (i * yFactor)))
            }
        }
        return points
    }

}