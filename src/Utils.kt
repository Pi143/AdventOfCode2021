import java.io.BufferedReader
import java.io.File
import java.math.BigInteger
import java.net.HttpURLConnection
import java.net.URL
import java.security.MessageDigest

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = File("src", "$name.txt").readLines()

/**
 * Converts string to md5 hash.
 */
fun String.md5(): String = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray())).toString(16)

/**
 * Reads lines from the given input txt file as int
 */
fun readInputAsInt(name: String) = File("src", "$name.txt").readLines().map { i: String -> i.toInt() }


fun getCodeOfDay(day: Int) {
    val url = URL("https://adventofcode.com/2021/day/$day/input")
    //contains session=<your-session-in-cookie>
    val config = File("src", "config.txt").readLines()
    val file = File("src", "Day${day.toString().padStart(2, '0')}.txt")

    if (!file.exists()) {
        println("Downloading puzzle input for the first time")
        with(url.openConnection() as HttpURLConnection) {
            this.setRequestProperty("Cookie", config[0])
            requestMethod = "GET"
            file.writeText(inputStream.bufferedReader().use(BufferedReader::readText))
        }
    }
}

fun createDay(day: Int) {
    createTemplate(day)
    createTestfile(day)
    //getCodeOfDay(day)
}

fun createTemplate(day: Int) {
    val template = File("src", "DayXX.kt")
    val file = File("src", "Day${day.toString().padStart(2, '0')}.kt")
    if (!file.exists()) {
        file.writeText(
            template.readText()
                .replace("0", day.toString())
                .replace("XX", day.toString().padStart(2, '0'))
        )
    }

}

fun createTestfile(day: Int) {
    val file = File("src", "Day${day.toString().padStart(2, '0')}_test.txt")
    if (!file.exists()) {
        file.createNewFile()
    }
}

fun findMaxDay(): Int {
    var maxDay = 0
    File("src").walk().forEach {
        val regex = "\\d{2}".toRegex()
        val match = regex.find(it.name)
        val currentDay = match?.value?.toInt()
        if(currentDay != null && currentDay > maxDay) {
            maxDay = currentDay
        }
    }
    return maxDay
}

fun createNextDay(){
    val day = findMaxDay()
    val file = File("src", "Day${day.toString().padStart(2, '0')}.txt")
    if (!file.exists()) {
        createDay(day+1)
    }

}
