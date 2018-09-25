package de.dikodam.adventofkotlin

fun main(args: Array<String>) {
    Day01().runTasks()
}

class Day01 : AbstractDay() {
    private val input = loadInput()
    override fun task1() {
        val sum = loadInput()[0].split("")
            .asSequence()
            .map(::charToInt)
            .sum()
        println("T1: Summe ist $sum")
    }

    private fun charToInt(it: String): Int {
        return when (it) {
            ")" -> -1
            "(" -> 1
            else -> 0
        }
    }

    override fun task2() {
        val ints = loadInput()[0].split("")
            .map(::charToInt)

        var sum = 0
        var index = 0
        while (sum >= 0) {
            sum += ints[index]
            index++
        }
        index--
        println("T2: index ist $index")
    }

}
