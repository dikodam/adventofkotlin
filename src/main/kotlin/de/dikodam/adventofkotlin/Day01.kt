package de.dikodam.adventofkotlin

fun main(args: Array<String>) {
    AbstractDay.doTheMagic(Day01())
}

class Day01 : AbstractDay() {
    override fun task1() {
        val sum = input[0].split("")
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
        val ints = input[0].split("")
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
