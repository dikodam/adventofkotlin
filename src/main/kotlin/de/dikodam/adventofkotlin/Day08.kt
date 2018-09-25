package de.dikodam.adventofkotlin

import java.util.regex.Pattern.compile

fun main(args: Array<String>) {
    Day08().runTasks()
}

class Day08 : AbstractDay() {

    val hexNumberRegex = compile("\\.\\d\\d")
    val backslashRegex = compile("\\\\")
    val quoteRegex = compile("\\\"")

    val joiningStrings: (String, String) -> String = { left, right -> "$left$right" }
    val addingInts: (Int, Int) -> Int = { a, b -> a + b }

    override fun task1() {
        //val input = loadInput()
        loadInput()
        val input = listOf("")

        val countOfStringLiterals = input
            .map { it.length }
            .reduce(addingInts)

        val countOfInterpretedChars = input
            .map { countInterpretedChars(it) }
            .reduce(addingInts)

        val difference = countOfStringLiterals - countOfInterpretedChars

        println("task 1: the difference is $difference")

    }

    private fun countInterpretedChars(line: String?): Int {
        val lineWithoutQuotes = line?.substring(1..(line.length - 1)) ?: ""
        return lineWithoutQuotes.truncateHexNumber()
            .truncateEscapedBackslashes()
            .truncateEscapedQuotes()
            .length
    }

    fun String.truncateHexNumber(): String {
        return this.split(hexNumberRegex)
            .reduce(joiningStrings)
    }

    fun String.truncateEscapedBackslashes(): String {
        return this.split(backslashRegex)
            .reduce(joiningStrings)
    }

    fun String.truncateEscapedQuotes(): String {
        return this.split(quoteRegex)
            .reduce(joiningStrings)
    }

    override fun task2() {

    }
}