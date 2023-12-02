package day2

import java.io.File
import java.nio.charset.Charset

fun main() {
    val inputList = File("./src/main/kotlin/day2/input.txt").readLines(Charset.defaultCharset())

    // Part 1
    val gameIdPossible = mutableMapOf<Int, Boolean>()
    val colorsMax = mapOf("red" to 12, "green" to 13, "blue" to 14)


    inputList.forEach { line ->
        val gameId = line.split(":").get(0).split(" ").get(1).toInt()

        val grabs = line.split(":").get(1).split(";")
        val isPossible = grabs.stream()
            .flatMap { grab ->
                grab.split(",").map { cube ->
                    val (colorCount, colorName) = cube.trim().split(" ")
                    val colorAllowedMax = colorsMax.get(colorName)
                    colorCount.toInt() <= colorAllowedMax!!
                }.stream()
            }.allMatch { it }

        gameIdPossible.put(gameId, isPossible)
    }

    val answer1 = gameIdPossible.filter { it.value }.keys.sum()
    println("Answer 1 is $answer1")


    // Part 2
    val colorsFewestRequired = mutableMapOf("red" to 0, "green" to 0, "blue" to 0)
    val gameIdPower = mutableMapOf<Int, Int>()

    inputList.forEach { line ->
        val gameId = line.split(":").get(0).split(" ").get(1).toInt()

        val grabs = line.split(":").get(1).split(";")
        grabs.stream().forEach { grab ->
            grab.split(",").forEach { cube ->
                val (colorCount, colorName) = cube.trim().split(" ")
                val fewestCount = colorsFewestRequired[colorName] ?: 0
                if (colorCount.toInt() > fewestCount) {
                    colorsFewestRequired.put(colorName, colorCount.toInt())
                }
            }
        }
        gameIdPower.put(gameId, colorsFewestRequired.values.reduce { i, j -> i * j })
        colorsFewestRequired.replaceAll { _, _ -> 0 }
    }
    val answer2 = gameIdPower.values.sum()
    println("Answer 2 is $answer2")
}