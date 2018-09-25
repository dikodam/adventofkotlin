package de.dikodam.adventofkotlin

fun main(args: Array<String>) {
    Day07().runTasks()
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