fun main() {
    fun part1(input: List<String>): Int {
        val length = input[0].length
        val zeroesArray = IntArray(length)
        val onesArray = IntArray(length)

        for (singleReport in input) {
            for (i in 0 until length) {
                if (singleReport[i] == '0') {
                    zeroesArray[i]++
                } else {
                    onesArray[i]++
                }
            }
        }
        var gammaString = ""
        var epsilonString = ""

        for (i in 0 until length) {
            if (onesArray[i] > zeroesArray[i]) {
                gammaString += "1"
                epsilonString += "0"
            } else {
                gammaString += "0"
                epsilonString += "1"
            }
        }
        val gamma = gammaString.toInt(2)
        val epsilon = epsilonString.toInt(2)

        return gamma * epsilon
    }

    fun mostCommonInPosition(currentValue: List<String>, position: Int): Char {
        var zeroes = 0
        var ones = 0

        for (singleReport in currentValue) {
            if (singleReport[position] == '0') {
                zeroes++
            } else {
                ones++
            }
        }

        return if (zeroes > ones) {
            '0'
        } else if (ones > zeroes) {
            '1'
        } else {
            '='
        }
    }

    fun part2(input: List<String>): Int {
        var currentOxygenValues = input
        var currentCo2Values = input
        var oxygen = 0
        var co2 = 0

        for (position in 0 until input[0].length) {
            var mostCommonOxygen = mostCommonInPosition(currentOxygenValues, position)
            mostCommonOxygen = if (mostCommonOxygen == '=') {
                '1'
            } else (mostCommonOxygen)
            currentOxygenValues = currentOxygenValues.filter {
                it[position] == mostCommonOxygen
            }
            var mostCommonCo2 = mostCommonInPosition(currentCo2Values, position)
            mostCommonCo2 = if (mostCommonCo2 == '=') {
                '1'
            } else (mostCommonCo2)
            currentCo2Values = currentCo2Values.filter {
                it[position] != mostCommonCo2
            }
            if (currentOxygenValues.size == 1) {
                oxygen = currentOxygenValues[0].toInt(2)
            }
            if (currentCo2Values.size == 1) {
                co2 = currentCo2Values[0].toInt(2)
            }
        }

        return oxygen * co2
    }

    getCodeOfDay(3)
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 198)
    check(part2(testInput) == 230)

    val input = readInput("Day03")
    println(part1(input))
    check(part1(input) == 852500)
    println(part2(input))
    check(part2(input) == 1007985)
}
