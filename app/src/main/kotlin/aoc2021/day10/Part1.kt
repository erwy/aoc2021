package aoc2021.day10

import aoc2021.utils.Part

class Part1(private val input: List<String>) : Part {

    private val opening = listOf('(', '{', '<', '[')
    private val closing = listOf(')', '}', '>', ']')
    private var syntaxErrorScore = 0L
    override fun run(): Long {
        input.map { it.toList() }.map {
            val stack = ArrayDeque<Char>()
            it.forEach { char ->
                if (opening.contains(char)) {
                    stack.addLast(char)
                } else {
                    val currentOpening = stack.removeLast()
                    if (opening.indexOf(currentOpening) != closing.indexOf(char)) {
                        when (char) {
                            ')' -> syntaxErrorScore+=3L
                            ']' -> syntaxErrorScore+=57L
                            '}' -> syntaxErrorScore+=1197L
                            '>' -> syntaxErrorScore+=25137L
                        }
                    }
                }
            }
        }
        return syntaxErrorScore
    }
}
