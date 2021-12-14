fun main() {
    fun part1(input: List<String>): Int {
        val numberOfSteps = 100
        var flashCount = 0
        val rows = input.size
        val columns = input[0].length
        val octopusArray = Array(rows) { IntArray(columns) }

        for (i in 0 until rows) {
            for (j in 0 until columns) {
                octopusArray[i][j] = Character.getNumericValue(input[i][j])
            }
        }

        for (step in 0 until numberOfSteps) {
            for (i in 0 until rows) {
                for (j in 0 until columns) {
                    octopusArray[i][j]++
                }
            }
            var allFlashesFinished = false
            while (!allFlashesFinished) {
                allFlashesFinished = true
                for (i in 0 until rows) {
                    for (j in 0 until columns) {
                        if (octopusArray[i][j] > 9) {
                            if (i > 0) {
                                if (j > 0) {
                                    octopusArray[i - 1][j - 1]++
                                }
                                octopusArray[i - 1][j]++
                                if (j < columns - 1) {
                                    octopusArray[i - 1][j + 1]++
                                }
                            }
                            if (j > 0) {
                                octopusArray[i][j - 1]++
                            }
                            if (j < columns - 1) {
                                octopusArray[i][j + 1]++
                            }
                            if (i < rows - 1) {
                                if (j > 0) {
                                    octopusArray[i + 1][j - 1]++
                                }
                                octopusArray[i + 1][j]++
                                if (j < columns - 1) {
                                    octopusArray[i + 1][j + 1]++
                                }
                            }

                            octopusArray[i][j] = -100
                            allFlashesFinished = false
                        }
                    }
                }
            }
            for (i in 0 until rows) {
                for (j in 0 until columns) {
                    if (octopusArray[i][j] < 0) {
                        octopusArray[i][j] = 0
                        flashCount++
                    }
                }
            }
            /*
            for (i in 0 until rows) {
                    println(octopusArray[i].toList())
            }
            println(flashCount)
            */

        }

        return flashCount
    }

    fun part2(input: List<String>): Int {
        var flashCount = 0
        val rows = input.size
        val columns = input[0].length
        val octopusArray = Array(rows) { IntArray(columns) }

        for (i in 0 until rows) {
            for (j in 0 until columns) {
                octopusArray[i][j] = Character.getNumericValue(input[i][j])
            }
        }

        var allFlashStep = 0
        var currentStep = 0
        while (allFlashStep == 0) {
            currentStep++
            for (i in 0 until rows) {
                for (j in 0 until columns) {
                    octopusArray[i][j]++
                }
            }
            var allFlashesFinished = false
            while (!allFlashesFinished) {
                allFlashesFinished = true
                for (i in 0 until rows) {
                    for (j in 0 until columns) {
                        if (octopusArray[i][j] > 9) {
                            if (i > 0) {
                                if (j > 0) {
                                    octopusArray[i - 1][j - 1]++
                                }
                                octopusArray[i - 1][j]++
                                if (j < columns - 1) {
                                    octopusArray[i - 1][j + 1]++
                                }
                            }
                            if (j > 0) {
                                octopusArray[i][j - 1]++
                            }
                            if (j < columns - 1) {
                                octopusArray[i][j + 1]++
                            }
                            if (i < rows - 1) {
                                if (j > 0) {
                                    octopusArray[i + 1][j - 1]++
                                }
                                octopusArray[i + 1][j]++
                                if (j < columns - 1) {
                                    octopusArray[i + 1][j + 1]++
                                }
                            }

                            octopusArray[i][j] = -100
                            allFlashesFinished = false
                        }
                    }
                }
            }
            val notFlashed = octopusArray.filter { it.any { it2 -> it2 > 0 } }.size
            if (notFlashed == 0) {
                allFlashStep = currentStep
            }
            for (i in 0 until rows) {
                for (j in 0 until columns) {
                    if (octopusArray[i][j] < 0) {
                        octopusArray[i][j] = 0
                        flashCount++
                    }
                }
            }

        }

        return allFlashStep
    }

    getCodeOfDay(11)
    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day11_test")
    check(part1(testInput) == 1656)
    check(part2(testInput) == 195)

    val input = readInput("Day11")
    println(part1(input))
    check(part1(input) == 1725)
    println(part2(input))
    check(part2(input) == 308)
}
