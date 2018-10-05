package de.dikodam.adventofkotlin

fun main(args: Array<String>) {
    Day16().runTasks()
}

class Day16 : AbstractDay() {
    private val input = loadInput()[0].split(",")
    private val programCount = 'p' - 'a' + 1
    override fun task1() {
        val programs = Array(programCount) { i -> 'a' + i }

        input.map { parseDanceMove(it) }
    }

    private fun parseDanceMove(instruction: String): DanceMove {
        val command = instruction[0]
        val parameters = instruction.substring(1)
        return when (command) {
            's' -> { array ->
                // spin by spinAmount
                // array.spinBy(parameters.toInt())
            }
            'x' -> { array ->
            }
            'p' -> { array ->
            }
            else -> error("illegal argument")
        }
    }

    override fun task2() {

    }

}

typealias DanceMove = (Array<Char>) -> Unit
