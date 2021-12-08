fun main() {
    fun part1(input: List<String>): Int {
        var easyDigitsSize = 0
        for (line in input) {
            val outputDigits = line.split(" | ")[1].split(" ")
            for (d in outputDigits) {
                if (d.length == 2 || d.length == 4 || d.length == 3 || d.length == 7) {
                    easyDigitsSize++
                }
            }
        }
        return easyDigitsSize
    }

    fun part2(input: List<String>): Int {
        var totalSum = 0
        for (line in input) {
            val patternDigits = line.split(" | ")[0].split(" ").map { it.toSortedSet() }
            val wireToDigitMap = mutableMapOf<Collection<Char>, Int>()
            val digitToWireMap = mutableMapOf<Int, Collection<Char>>()
            for (d in patternDigits) {
                when (d.size) {
                    2 -> {
                        wireToDigitMap[d] = 1
                        digitToWireMap[1] = d
                    }
                    4 -> {
                        wireToDigitMap[d] = 4
                        digitToWireMap[4] = d
                    }
                    3 -> {
                        wireToDigitMap[d] = 7
                        digitToWireMap[7] = d
                    }
                    7 -> {
                        wireToDigitMap[d] = 8
                        digitToWireMap[8] = d
                    }
                }
            }

            for (d in patternDigits) {
                if (d.size == 5 && digitToWireMap[1]?.let { d.containsAll(it) } == true) {
                    wireToDigitMap[d] = 3
                    digitToWireMap[3] = d
                }
                if (d.size == 6 && digitToWireMap[1]?.let { d.containsAll(it) } == false) {
                    wireToDigitMap[d] = 6
                    digitToWireMap[6] = d
                }
                val bdSet = digitToWireMap[4]?.minus(digitToWireMap[1]!!.toSet())
                if (d.size == 6 && bdSet?.let { d.containsAll(it) } == false && digitToWireMap[1]?.let { d.containsAll(it) } == true) {
                    wireToDigitMap[d] = 0
                    digitToWireMap[0] = d
                }
                if (d.size == 6 && bdSet?.let { d.containsAll(it) } == true && digitToWireMap[1]?.let { d.containsAll(it) } == true) {
                    wireToDigitMap[d] = 9
                    digitToWireMap[9] = d
                }
                if (d.size == 5 && bdSet?.let { d.containsAll(it) } == false && digitToWireMap[1]?.let { d.containsAll(it) } == false) {
                    wireToDigitMap[d] = 2
                    digitToWireMap[2] = d
                }
                if (d.size == 5 && bdSet?.let { d.containsAll(it) } == true) {
                    wireToDigitMap[d] = 5
                    digitToWireMap[5] = d
                }
            }
            val outputDigits = line.split(" | ")[1].split(" ").map { it.toSortedSet() }

            val outputNumber = 1000*wireToDigitMap[outputDigits[0]]!! + 100*wireToDigitMap[outputDigits[1]]!! + 10*wireToDigitMap[outputDigits[2]]!! + wireToDigitMap[outputDigits[3]]!!
            totalSum += outputNumber
        }
        return totalSum
    }

    getCodeOfDay(8)
    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day08_test")
    check(part1(testInput) == 26)
    check(part2(testInput) == 61229)

    val input = readInput("Day08")
    println(part1(input))
    check(part1(input) == 525)
    println(part2(input))
    check(part2(input) == 1083859)
}
