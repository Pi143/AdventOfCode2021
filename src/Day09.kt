fun main() {
    fun part1(input: List<String>): Int {
        val minValues = mutableListOf<Int>()
        for (i in input.indices) {
            for (j in 0 until input[0].length) {
                val currentValue = Character.getNumericValue(input[i].toCharArray()[j])
                val top = Character.getNumericValue(input.getOrElse(i - 1) { "9" }.toCharArray().getOrElse(j) { '9' })
                val bot = Character.getNumericValue(input.getOrElse(i + 1) { "9" }.toCharArray().getOrElse(j) { '9' })
                val left = Character.getNumericValue(input.getOrElse(i) { "9" }.toCharArray().getOrElse(j - 1) { '9' })
                val right = Character.getNumericValue(input.getOrElse(i) { "9" }.toCharArray().getOrElse(j + 1) { '9' })

                if (top > currentValue && bot > currentValue && left > currentValue && right > currentValue) {
                    minValues.add(currentValue)
                }
            }
        }

        return minValues.sumOf { it + 1 }
    }

    fun part2(input: List<String>): Int {
        val basinCoords = mutableListOf<Pair<Int, Int>>()
        for (i in input.indices) {
            for (j in 0 until input[0].length) {
                val currentValue = Character.getNumericValue(input[i].toCharArray()[j])
                val top = Character.getNumericValue(input.getOrElse(i - 1) { "9" }.toCharArray().getOrElse(j) { '9' })
                val bot = Character.getNumericValue(input.getOrElse(i + 1) { "9" }.toCharArray().getOrElse(j) { '9' })
                val left = Character.getNumericValue(input.getOrElse(i) { "9" }.toCharArray().getOrElse(j - 1) { '9' })
                val right = Character.getNumericValue(input.getOrElse(i) { "9" }.toCharArray().getOrElse(j + 1) { '9' })

                if (top > currentValue && bot > currentValue && left > currentValue && right > currentValue) {
                    basinCoords.add(Pair(i, j))
                }
            }
        }

        val basinSizes = mutableListOf<Int>()
        for (basin in basinCoords) {
            var currentFlooding = mutableSetOf<Pair<Int, Int>>()
            currentFlooding.add(basin)
            var basinIncrease = 1
            while (basinIncrease > 0) {
                val newFlooding = mutableSetOf<Pair<Int, Int>>()
                newFlooding.addAll(currentFlooding)
                for (floodedField in currentFlooding) {
                    val i = floodedField.first
                    val j = floodedField.second
                    val top =
                        Character.getNumericValue(input.getOrElse(i - 1) { "9" }.toCharArray().getOrElse(j) { '9' })
                    val bot =
                        Character.getNumericValue(input.getOrElse(i + 1) { "9" }.toCharArray().getOrElse(j) { '9' })
                    val left =
                        Character.getNumericValue(input.getOrElse(i) { "9" }.toCharArray().getOrElse(j - 1) { '9' })
                    val right =
                        Character.getNumericValue(input.getOrElse(i) { "9" }.toCharArray().getOrElse(j + 1) { '9' })
                    if (top < 9) newFlooding.add(Pair(i - 1, j))
                    if (bot < 9) newFlooding.add(Pair(i + 1, j))
                    if (left < 9) newFlooding.add(Pair(i, j - 1))
                    if (right < 9) newFlooding.add(Pair(i, j + 1))
                }
                basinIncrease = newFlooding.size - currentFlooding.size
                currentFlooding = newFlooding
            }
            basinSizes.add(currentFlooding.size)
        }


        val max1 = basinSizes.maxOrNull()!!
        basinSizes.remove(max1)
        val max2 = basinSizes.maxOrNull()!!
        basinSizes.remove(max2)
        val max3 = basinSizes.maxOrNull()!!
        return max1 * max2 * max3
    }

    getCodeOfDay(9)
    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day09_test")
    check(part1(testInput) == 15)
    check(part2(testInput) == 1134)

    val input = readInput("Day09")
    println(part1(input))
    check(part1(input) == 570)
    println(part2(input))
    check(part2(input) == 899392)
}
