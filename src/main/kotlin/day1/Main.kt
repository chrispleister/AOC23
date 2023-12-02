package day1

import java.io.File
import java.nio.charset.Charset

val numbers: Map<String, Int> = mapOf(
    "one" to 1,
    "two" to 2,
    "three" to 3,
    "four" to 4,
    "five" to 5,
    "six" to 6,
    "seven" to 7,
    "eight" to 8,
    "nine" to 9
)

fun main() {
    val inputList = File("./src/main/kotlin/day1/input.txt").readLines(Charset.defaultCharset())
    val answer1 = inputList.sumOf { getRowCalibrationValue(it) }

    println("Answer 1: " + answer1)

    val answer2 = inputList.map { row ->
        val out = StringBuilder()
        row.forEachIndexed { index, char ->
            if (char.isDigit()) {
                out.append(char)
            } else {
                for (i in 3..5) {
                    val startIndex = index.coerceAtMost(row.length)
                    val endIndex = (index + i).coerceAtMost(row.length)
                    val maybeNumber = row.substring(startIndex, endIndex)
                    if (numbers.containsKey(maybeNumber)) {
                        out.append(numbers.get(maybeNumber))
                    }
                }
            }
        }
        out.toString()
    }.sumOf { getRowCalibrationValue(it) }

    println("Answer 2: " + answer2)
}

fun getRowCalibrationValue(s: String): Int {
    val first = s.first { it.isDigit() }
    val last = s.last { it.isDigit() }
    return "${first}${last}".toInt()
}


