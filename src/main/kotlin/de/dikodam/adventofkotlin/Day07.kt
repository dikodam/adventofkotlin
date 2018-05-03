package de.dikodam.adventofkotlin

fun main(args: Array<String>) {
    Day07().doTheMagic()
}

/**
 * instruction:
 * a) <number> -> <address>
 * b) NOT <address> -> <address>
 * c) <address> <instruction> <address> -> <address>
 *     <instruction> := {AND|OR|LSHIFT|RSHIFT}
 *     <address> := letter(s)
 */

typealias Operation = Any

class Day07 : AbstractDay() {
    private val input: List<String> = loadInput()

    override fun task1() {
        input.map { translateLine(it) }
    }


    private fun translateLine(it: String): InstructionDay07 {
        val (commandString, target) = it.split(" -> ")
        val command = commandString.split(" ")
        val operation = when (command.size) {
            // direct assignment of a number
            1 -> { i: Int -> i }
            // NOT operation
            2 -> { i:Int -> i.inv()}
            3 -> {i:Int -> i}
            else -> throw IllegalArgumentException()
        }
    }

    fun parseInstruction(instructionString: String): (Int, Int) -> Int {
        return when (instructionString) {
            "AND" -> { left, right -> left.and(right) }
            "OR" -> { left, right -> left.or(right) }
            "LSHIFT" -> { left, right -> left.shl(right) }
            "RSHIFT" -> { left, right -> left.ushr(right) }
            else -> throw IllegalArgumentException(instructionString)
        }
    }

    override fun task2() {
    }


}