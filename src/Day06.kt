fun main() {
    fun lantern(days: Int): Long {
        if (days < 7) return 1
        val generations = (days - 1) / 7
        val generationOffset = (days - 1) % 7 - 8
        var sizeOfOffspring = 0L
        for (g in 1..generations) {
            sizeOfOffspring += lantern(g * 7 + generationOffset)
        }
        return sizeOfOffspring + 1
    }

    fun lanternWithMap(days: Int, lanternMap: MutableMap<Int, Long>): Long {
        if (days < 7) return 1
        if (lanternMap.containsKey(days)) return lanternMap[days]!!
        val generations = (days - 1) / 7
        val generationOffset = (days - 1) % 7 - 8
        var sizeOfOffspring = 0L
        for (g in 1..generations) {
            sizeOfOffspring += lanternWithMap(g * 7 + generationOffset, lanternMap)
        }

        lanternMap[days] = sizeOfOffspring + 1
        return sizeOfOffspring + 1
    }

    fun part1(input: List<String>): Long {

        val days = 80
        val split = input[0].split(",")
        var totalPopulation: Long = 0

        for (pop in split) {
            totalPopulation += lantern(days + 7 - pop.toInt())
        }

        println(totalPopulation)
        return totalPopulation
    }

    fun part2(input: List<String>): Long {
        val days = 256
        val split = input[0].split(",")
        var totalPopulation = 0L
        val lanternMap = mutableMapOf<Int, Long>()

        for (pop in split) {
            totalPopulation += lanternWithMap(days + 7 - pop.toInt(), lanternMap)
        }

        println(totalPopulation)
        return totalPopulation
    }

    getCodeOfDay(6)
    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day06_test")
    check(part1(testInput) == 5934L)
    check(part2(testInput) == 26984457539)

    val input = readInput("Day06")
    println(part1(input))
    check(part1(input) == 379114L)
    println(part2(input))
    check(part2(input) == 1702631502303)
}
