package aoc2021.day10

import aoc2021.utils.Part

class Part2(private val input: List<String>) : Part {

    private val opening = listOf('(', '{', '<', '[')
    private val closing = listOf(')', '}', '>', ']')
    private var incompleteScores = mutableListOf<Long>()
    override fun run(): Long {
        input.map { it.toList() }.map {
            val stack = ArrayDeque<Char>()
            for (c in it) {
                if (opening.contains(c)) {
                    stack.addLast(c)
                } else {
                    val currentOpening = stack.removeLast()
                    if (opening.indexOf(currentOpening) != closing.indexOf(c)) {
                        stack.clear()
                        break
                    }
                }
            }
            var incompleteScore = 0L
            while (stack.isNotEmpty()) {
                val char = stack.removeLast()
                when (char) {
                    '(' -> incompleteScore = (incompleteScore * 5) + 1
                    '[' -> incompleteScore = (incompleteScore * 5) + 2
                    '{' -> incompleteScore = (incompleteScore * 5) + 3
                    '<' -> incompleteScore = (incompleteScore * 5) + 4
                }
            }
            if (incompleteScore > 0) {
                incompleteScores.add(incompleteScore)
            }
        }
        return incompleteScores.sorted()[incompleteScores.size / 2]
    }
}