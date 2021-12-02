fun main() {
    fun part1(input: List<String>): Int {
        var horizontalPosition = 0
        var depth = 0
        for (i in input) {
            val ops = i.split(" ")[0]
            val value = i.split(" ")[1].toInt()

            when (ops) {
                "forward" -> horizontalPosition += value
                "down" -> depth += value
                "up" -> depth -= value
            }

        }

        //println("horizontal position $horizontalPosition")
        //println("depth: $depth")
        return horizontalPosition * depth
    }

    fun part2(input: List<String>): Int {
        var horizontalPosition = 0
        var depth = 0
        var aim = 0
        for (i in input) {
            val ops = i.split(" ")[0]
            val value = i.split(" ")[1].toInt()

            when (ops) {
                "forward" -> {
                    horizontalPosition += value
                    depth += aim * value
                }
                "down" -> aim += value
                "up" -> aim -= value
            }

        }

        //println("horizontal position $horizontalPosition")
        //println("depth: $depth")
        //println("aim: $aim")
        return horizontalPosition * depth
    }

    getCodeOfDay(2)
    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 150)
    check(part2(testInput) == 900)

    val input = readInput("Day02")
    println(part1(input))
    check(part1(input) == 1648020)
    println(part2(input))
    check(part2(input) == 1759818555)
}
