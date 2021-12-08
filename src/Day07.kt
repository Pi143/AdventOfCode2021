import java.util.Collections.max
import java.util.Collections.min
import kotlin.math.abs
import kotlin.math.min

fun main() {
    fun part1(input: List<String>): Int {
        val crabsCoords = input[0].split(",").map { it.toInt() }
        val minCrab = min(crabsCoords)
        val maxCrab = max(crabsCoords)

        var minFuel = Int.MAX_VALUE
        for (coord in minCrab..maxCrab){
            var fuel = 0
            for(crab in crabsCoords){
                fuel += abs(crab-coord)
            }
            minFuel = min(minFuel,fuel)
        }
        return minFuel
    }

    fun part2(input: List<String>): Int {
        val crabsCoords = input[0].split(",").map { it.toInt() }
        val minCrab = min(crabsCoords)
        val maxCrab = max(crabsCoords)

        var minFuel = Int.MAX_VALUE
        for (coord in minCrab..maxCrab){
            var fuel = 0
            for(crab in crabsCoords){
                fuel += abs(crab-coord)*(abs(crab-coord)+1)/2
            }
            minFuel = min(minFuel,fuel)
        }
        return minFuel
    }

    getCodeOfDay(7)
    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day07_test")
    check(part1(testInput) == 37)
    check(part2(testInput) == 168)

    val input = readInput("Day07")
    println(part1(input))
    check(part1(input) == 355989)
    println(part2(input))
    check(part2(input) == 102245489)
}
