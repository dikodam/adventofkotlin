package de.dikodam.adventofkotlin

fun main(args: Array<String>) {
    Day14().runTasks()
}

class Day14 : AbstractDay() {
    private val input = loadInput()
    override fun task1() {
        val inputString = input[0]
        val rowKeys = (0..127).map { suffix -> "$inputString-$suffix" }
        rowKeys.map {  }

    }

    override fun task2() {

    }
}