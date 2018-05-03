package de.dikodam.adventofkotlin

fun main(args: Array<String>) {
    Day06().doTheMagic()
}

// <instruction> <coords> through <coords>
// instruction: turn on, turn off, toggle
// coords: Int,Int


typealias Command = (Lightbulb) -> Unit
typealias Instruction = Triple<Command, Coordinates, Coordinates>

class Day06 : AbstractDay() {

    override fun task1() {
        val instructions = loadInput()
            .map { parseLineToInstruction(it) }

        val lightbulbs = (0..999)
            .map {
                (0..999).map { LightbulbOnOff() }
            }

        instructions.applyTo(lightbulbs)

        val count = lightbulbs.flatMap { it }
            .filter { bulb -> bulb.turnedOn }
            .count()

        println("task 1: $count bulbs are turned on after all instructions completed")
    }

    fun List<Instruction>.applyTo(lightbulbs: List<List<Lightbulb>>) {
        this
            .forEach { (command, coordsFrom, coordsTo) ->
                (coordsFrom..coordsTo)
                    .map { (x, y) -> lightbulbs[x][y] }
                    .forEach { command(it) }
            }
    }

    private fun parseLineToInstruction(instructionLine: String): Triple<Command, Coordinates, Coordinates> {
        val command = parseCommand(instructionLine)
        val coordinates = instructionLine.dropWhile { it !in '0'..'9' }
            .split(" through ")
            .map { parseCoordinates(it) }
            .toList()
        return Instruction(command, coordinates[0], coordinates[1])
    }

    private fun parseCommand(instructionLine: String): Command {
        return when {
            instructionLine.startsWith("toggle") -> { bulb: Lightbulb -> bulb.toggle() }
            instructionLine.startsWith("turn on") -> { bulb: Lightbulb -> bulb.turnOn() }
            instructionLine.startsWith("turn off") -> { bulb: Lightbulb -> bulb.turnOff() }
            else -> {
                throw IllegalArgumentException()
            }
        }
    }

    private fun parseCoordinates(coords: String): Coordinates {
        return coords.split(",")
            .map(String::toInt)
            .zipWithNext()
            .first()
    }

    override fun task2() {
        val instructions = loadInput()
            .map { parseLineToInstruction(it) }

        val lightbulbs = (0..999)
            .map { (0..999).map { LightbulbWithBrightness() } }

        instructions.applyTo(lightbulbs)

        val totalBrightness = lightbulbs
            .flatMap { it }
            .map { it.brightness }
            .sum()

        println("task 2: total brightness is $totalBrightness")
    }
}

interface Lightbulb {

    fun turnOn()
    fun turnOff()
    fun toggle()
}

class LightbulbOnOff : Lightbulb {
    var turnedOn: Boolean = false
        private set

    override fun turnOn() {
        turnedOn = true
    }

    override fun turnOff() {
        turnedOn = false
    }

    override fun toggle() {
        turnedOn = !turnedOn
    }

}

class LightbulbWithBrightness : Lightbulb {
    var brightness: Int = 0
        private set

    override fun turnOn() {
        brightness++
    }

    override fun turnOff() {
        if (brightness > 0) {
            brightness--
        }
    }

    override fun toggle() {
        brightness += 2
    }

}