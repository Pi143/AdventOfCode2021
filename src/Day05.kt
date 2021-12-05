import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

fun main() {
    fun part1(input: List<String>): Int {
        val coverageMap = mutableMapOf<Pair<Int, Int>, Int>()

        for (ventLine in input) {
            val split = ventLine.split(" -> ")
            val start = split[0].split(",")
            val end = split[1].split(",")

            if (start[0] == end[0]) {
                val posX = start[0]
                val minY = min(start[1].toInt(), end[1].toInt())
                val maxY = max(start[1].toInt(), end[1].toInt())

                for (y in minY..maxY) {
                    val currentPosition = Pair(posX.toInt(), y)
                    if (coverageMap.containsKey(currentPosition)) {
                        coverageMap[currentPosition] = coverageMap[currentPosition]!!.plus(1)
                    } else {
                        coverageMap[currentPosition] = 1
                    }
                }

            }

            if (start[1] == end[1]) {
                val posY = start[1]
                val minX = min(start[0].toInt(), end[0].toInt())
                val maxX = max(start[0].toInt(), end[0].toInt())

                for (x in minX..maxX) {
                    val currentPosition = Pair(x, posY.toInt())
                    if (coverageMap.containsKey(currentPosition)) {
                        coverageMap[currentPosition] = coverageMap[currentPosition]!!.plus(1)
                    } else {
                        coverageMap[currentPosition] = 1
                    }

                }
            }

        }

        return coverageMap.filter { it.value > 1 }.size
    }

    fun part2(input: List<String>): Int {
        val coverageMap = mutableMapOf<Pair<Int, Int>, Int>()

        for (ventLine in input) {
            val split = ventLine.split(" -> ")
            val start = split[0].split(",")
            val end = split[1].split(",")

            if (start[0] == end[0]) {
                val posX = start[0]
                val minY = min(start[1].toInt(), end[1].toInt())
                val maxY = max(start[1].toInt(), end[1].toInt())

                for (y in minY..maxY) {
                    val currentPosition = Pair(posX.toInt(), y)
                    if (coverageMap.containsKey(currentPosition)) {
                        coverageMap[currentPosition] = coverageMap[currentPosition]!!.plus(1)
                    } else {
                        coverageMap[currentPosition] = 1
                    }
                }

            }

            if (start[1] == end[1]) {
                val posY = start[1]
                val minX = min(start[0].toInt(), end[0].toInt())
                val maxX = max(start[0].toInt(), end[0].toInt())

                for (x in minX..maxX) {
                    val currentPosition = Pair(x, posY.toInt())
                    if (coverageMap.containsKey(currentPosition)) {
                        coverageMap[currentPosition] = coverageMap[currentPosition]!!.plus(1)
                    } else {
                        coverageMap[currentPosition] = 1
                    }
                }
            }

            if (start[0] != end[0] && start[1] != end[1]) {
                val startX = start[0].toInt()
                val startY = start[1].toInt()
                val endX = end[0].toInt()
                val endY = end[1].toInt()

                val directionX = if (endX > startX) 1 else -1
                val directionY = if (endY > startY) 1 else -1
                val distance = abs(endX - startX)

                for (movement in 0..distance) {
                    val currentPosition = Pair(startX + movement * directionX, startY + movement * directionY)
                    if (coverageMap.containsKey(currentPosition)) {
                        coverageMap[currentPosition] = coverageMap[currentPosition]!!.plus(1)
                    } else {
                        coverageMap[currentPosition] = 1
                    }
                }

            }

        }

        return coverageMap.filter { it.value > 1 }.size
    }

    getCodeOfDay(5)
    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day05_test")
    check(part1(testInput) == 5)
    check(part2(testInput) == 12)

    val input = readInput("Day05")
    println(part1(input))
    check(part1(input) == 5835)
    println(part2(input))
    check(part2(input) == 17013)
}
