fun main() {
    fun part1(input: List<String>): Int {
        var increased = 0
        for (i in 1 until input.size) {
            if (input[i].toInt() > input[i - 1].toInt()) {
                increased++
                //println("""${input[i]} is bigger than ${input[i-1]} total increase to ${increased}""")
            } else {
                //println("""${input[i]} is not bigger than ${input[i - 1]} total still at ${increased}""")
            }
        }
        return increased
    }

    fun part2(input: List<String>): Int {
        var increased = 0
        for (i in 3 until input.size) {
            if (input[i].toInt() + input[i - 1].toInt() + input[i - 2].toInt() > input[i - 1].toInt() + input[i - 2].toInt() + input[i - 3].toInt()) {
                increased++
            }
        }
        return increased
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 7)

    val input = readInput("Day01")
    println(part1(input))
    check(part1(input) == 1616)
    println(part2(input))
    check(part2(input) == 1645)
}
