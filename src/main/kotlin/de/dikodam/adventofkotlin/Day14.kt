package de.dikodam.adventofkotlin

fun main(args: Array<String>) {
    Day14().runTasks()
}

class Day14 : AbstractDay() {
    private val input = loadInput()
    private val square = buildSquare()

    override fun task1() {
        val usedSquareCount = square.asSequence()
            .flatMap { it.asSequence() }
            .count { it == '1' }

        println("Task 1: there are $usedSquareCount used squares")
    }

    private fun buildSquare(): List<List<Char>> {
        val inputString = input[0]
        val rowKeys = (0..127).map { suffix -> "$inputString-$suffix" }
        return rowKeys.toCharSquare()
    }

    private fun List<String>.toCharSquare(): List<List<Char>> {
        return this.asSequence()
            .map { rowKey -> rowKey.parseToKnotHashParameters() }
            .map { knotHashParams -> KnotHash.defaultInitHash().fullHash(knotHashParams) }
            .map { hexHash -> hexHash.toBinaryRepresentation() }
            .map { it.toList() }
            .toList()
    }

    private fun String.toBinaryRepresentation(): CharSequence {
        return this.toCharArray().joinToString(separator = "") { char -> char.toBinaryString() }
    }

    private fun Char.toBinaryString(): CharSequence {
        return when (this) {
            '0' -> "0000"
            '1' -> "0001"
            '2' -> "0010"
            '3' -> "0011"
            '4' -> "0100"
            '5' -> "0101"
            '6' -> "0110"
            '7' -> "0111"
            '8' -> "1000"
            '9' -> "1001"
            'a' -> "1010"
            'b' -> "1011"
            'c' -> "1100"
            'd' -> "1101"
            'e' -> "1110"
            'f' -> "1111"
            else -> ""
        }
    }

    override fun task2() {
        // identify regions
        // count them
    }
}