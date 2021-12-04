fun main() {
    fun readGameArray(subList: List<String>): Array<IntArray> {
        val gameArray = Array(5) { IntArray(5) }

        for (lineNumber in 0 until 5) {
            for (charNumber in 0 until 5) {
                val line = subList[lineNumber].trim()
                val splittedNumbers = line.split(" +".toRegex())
                gameArray[lineNumber][charNumber] = splittedNumbers[charNumber].toInt()
            }
        }

        return gameArray
    }

    fun isWinning(markedBoard: Array<IntArray>): Boolean {
        var columnSums = IntArray(5)
        for (line in markedBoard) {
            if (line.sum() == 5) {
                return true
            }

            for (i in columnSums.indices) {
                columnSums[i] += line[i]
            }
        }

        if (columnSums.any { it == 5 }) {
            return true
        }
        return false
    }

    fun calculateWinningEmptyScore(winningBoard: Array<IntArray>, markedBoard: Array<IntArray>): Int {
        var emptyScore = 0
        for (lineNumber in winningBoard.indices) {
            val zip = winningBoard[lineNumber].zip(markedBoard[lineNumber]) { win, mark ->
                win * (1 - mark)
            }
            emptyScore += zip.sum()
        }
        return emptyScore
    }

    fun part1(input: List<String>): Int {
        val randomNumbers = input[0].split(",").map { it.toInt() }
        var winningBoard = Array(5) { IntArray(5) }
        var winningNumber = 0
        var minWinningMoves = Int.MAX_VALUE
        var emptyScore = 1


        for (i in 2 until input.size step 6) {
            val gameArray = readGameArray(input.subList(i, i + 5))
            var markedBoard = Array(5) { IntArray(5) }

            for (winningMove in randomNumbers.indices) {
                val drawnNumber = randomNumbers[winningMove]
                for (lineNumber in 0 until 5) {
                    for (charNumber in 0 until 5) {
                        if (gameArray[lineNumber][charNumber] == drawnNumber) {
                            markedBoard[lineNumber][charNumber] = 1
                        }
                        if (isWinning(markedBoard) && winningMove < minWinningMoves) {
                            minWinningMoves = winningMove
                            winningBoard = gameArray
                            emptyScore = calculateWinningEmptyScore(winningBoard, markedBoard)
                            winningNumber = drawnNumber
                        }
                    }
                }
            }

        }

        return winningNumber * emptyScore
    }

    fun part2(input: List<String>): Int {
        val randomNumbers = input[0].split(",").map { it.toInt() }
        var winningBoard = Array(5) { IntArray(5) }
        var winningNumber = 0
        var maxWinningMoves = 0
        var emptyScore = 1


        for (i in 2 until input.size step 6) {
            val gameArray = readGameArray(input.subList(i, i + 5))
            var markedBoard = Array(5) { IntArray(5) }

            playGame@ for (winningMove in randomNumbers.indices) {
                val drawnNumber = randomNumbers[winningMove]
                for (lineNumber in 0 until 5) {
                    for (charNumber in 0 until 5) {
                        if (gameArray[lineNumber][charNumber] == drawnNumber) {
                            markedBoard[lineNumber][charNumber] = 1
                        }
                        if (isWinning(markedBoard)) {
                            if (winningMove > maxWinningMoves) {
                                maxWinningMoves = winningMove
                                winningBoard = gameArray
                                emptyScore = calculateWinningEmptyScore(winningBoard, markedBoard)
                                winningNumber = drawnNumber
                            }
                            break@playGame
                        }
                    }
                }
            }

        }

        return winningNumber * emptyScore
    }



    getCodeOfDay(4)
    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_test")
    check(part1(testInput) == 4512)
    check(part2(testInput) == 1924)

    val input = readInput("Day04")
    println(part1(input))
    check(part1(input) == 39984)
    println(part2(input))
    check(part2(input) == 8468)
}
