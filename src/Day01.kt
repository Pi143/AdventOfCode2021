fun main() {
    fun part1(input: List<Int>): Int {
        var increased = 0
        for (i in 1 until input.size) {
            if (input[i] > input[i - 1]) {
                increased++
                //println("""${input[i]} is bigger than ${input[i-1]} total increase to ${increased}""")
            } else {
                //println("""${input[i]} is not bigger than ${input[i - 1]} total still at ${increased}""")
            }
        }
        return increased
    }

    fun part2(input: List<Int>): Int {
        var increased = 0
        for (i in 3 until input.size) {
            if (input[i] > input[i - 3]) {
                increased++
            }
        }
        return increased
    }

    getCodeOfDay(1)
    // test if implementation meets criteria from the description, like:
    val testInput = readInputAsInt("Day01_test")
    check(part1(testInput) == 7)
    check(part2(testInput) == 5)

    val input = readInputAsInt("Day01")
    println(part1(input))
    check(part1(input) == 1616)
    println(part2(input))
    check(part2(input) == 1645)
}
