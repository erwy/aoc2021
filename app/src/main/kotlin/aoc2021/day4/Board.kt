package aoc2021.day4

class Board(boardInput: List<String>) {
    private val boardMatrix = boardInput.map { s ->
        s.trim().split("""\s+""".toRegex()).map { Integer.parseInt(it.trim()) }
    }

    private val columnToUsedNumbers = mutableMapOf<Int, MutableList<Int>>()
    private val rowToUsedNumbers = mutableMapOf<Int, MutableList<Int>>()

    fun check(number: Int) {
        boardMatrix.forEachIndexed { row, numbers ->
            val column = numbers.indexOfFirst { it == number }
            if (column != -1) {
                columnToUsedNumbers.getOrPut(column) { mutableListOf() }.add(number)
                rowToUsedNumbers.getOrPut(row) { mutableListOf() }.add(number)
            }
        }
    }

    fun winning(): Boolean {
        val winningColumn = columnToUsedNumbers.values.firstOrNull { it.size == 5 }
        val winningRow = rowToUsedNumbers.values.firstOrNull { it.size == 5 }
        return winningColumn != null || winningRow != null
    }

    fun sumOfUnused(): Int {
        val usedNumbers = columnToUsedNumbers.values.flatten() + rowToUsedNumbers.values.flatten()
        return boardMatrix.flatten().filter { !usedNumbers.contains(it) }.sum()
    }
}
