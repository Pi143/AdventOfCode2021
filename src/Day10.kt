fun main() {
    fun part1(input: List<String>): Int {

        var syntaxCheckerScore = 0
        for (line in input) {
            var currentOpenBrackets = ""

            for (char in line) {
                when (char) {
                    '(' -> currentOpenBrackets += "("
                    '[' -> currentOpenBrackets += "["
                    '{' -> currentOpenBrackets += "{"
                    '<' -> currentOpenBrackets += "<"
                    ')' -> {
                        if (currentOpenBrackets.last() == '(') {
                            currentOpenBrackets = currentOpenBrackets.removeSuffix("(")
                        } else {
                            syntaxCheckerScore += 3
                            break
                        }
                    }
                    ']' -> {
                        if (currentOpenBrackets.last() == '[') {
                            currentOpenBrackets = currentOpenBrackets.removeSuffix("[")
                        } else {
                            syntaxCheckerScore += 57
                            break
                        }
                    }
                    '}' -> {
                        if (currentOpenBrackets.last() == '{') {
                            currentOpenBrackets = currentOpenBrackets.removeSuffix("{")
                        } else {
                            syntaxCheckerScore += 1197
                            break
                        }
                    }
                    '>' -> {
                        if (currentOpenBrackets.last() == '<') {
                            currentOpenBrackets = currentOpenBrackets.removeSuffix("<")
                        } else {
                            syntaxCheckerScore += 25137
                            break
                        }
                    }

                }
            }
        }
        return syntaxCheckerScore
    }

    fun part2(input: List<String>): Long {
        val autocompleteScores = mutableListOf<Long>()

        var incompleteLines = mutableListOf<String>()
        for (line in input) {
            var currentOpenBrackets = ""
            var incorrect = false

            for (char in line) {
                when (char) {
                    '(' -> currentOpenBrackets += "("
                    '[' -> currentOpenBrackets += "["
                    '{' -> currentOpenBrackets += "{"
                    '<' -> currentOpenBrackets += "<"
                    ')' -> {
                        if (currentOpenBrackets.last() == '(') {
                            currentOpenBrackets = currentOpenBrackets.removeSuffix("(")
                        } else {
                            incorrect = true
                            break
                        }
                    }
                    ']' -> {
                        if (currentOpenBrackets.last() == '[') {
                            currentOpenBrackets = currentOpenBrackets.removeSuffix("[")
                        } else {
                            incorrect = true
                            break
                        }
                    }
                    '}' -> {
                        if (currentOpenBrackets.last() == '{') {
                            currentOpenBrackets = currentOpenBrackets.removeSuffix("{")
                        } else {
                            incorrect = true
                            break
                        }
                    }
                    '>' -> {
                        if (currentOpenBrackets.last() == '<') {
                            currentOpenBrackets = currentOpenBrackets.removeSuffix("<")
                        } else {
                            incorrect = true
                            break
                        }
                    }
                }

            }
            if (currentOpenBrackets.isNotEmpty() && !incorrect) {
                incompleteLines.add(currentOpenBrackets)
            }
        }
        for (line in incompleteLines) {
            var currentAutoCompleteScore = 0L
            for (char in line.reversed()) {
                when (char) {

                    '(' -> {
                        currentAutoCompleteScore *= 5
                        currentAutoCompleteScore += 1

                    }
                    '[' -> {
                        currentAutoCompleteScore *= 5
                        currentAutoCompleteScore += 2

                    }
                    '{' -> {
                        currentAutoCompleteScore *= 5
                        currentAutoCompleteScore += 3

                    }
                    '<' -> {
                        currentAutoCompleteScore *= 5
                        currentAutoCompleteScore += 4

                    }

                }
            }
            autocompleteScores.add(currentAutoCompleteScore)
        }

        autocompleteScores.sort()
        return autocompleteScores[(autocompleteScores.size - 1) / 2]
    }

    getCodeOfDay(10)
    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day10_test")
    check(part1(testInput) == 26397)
    check(part2(testInput) == 288957L)

    val input = readInput("Day10")
    println(part1(input))
    check(part1(input) == 168417)
    println(part2(input))
    check(part2(input) == 2802519786)
}
