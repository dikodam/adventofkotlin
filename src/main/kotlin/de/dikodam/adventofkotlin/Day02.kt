package de.dikodam.adventofkotlin

import kotlin.math.min

fun main(args: Array<String>) {
    AbstractDay.doTheMagic(Day02())
}

class Day02 : AbstractDay() {
    override fun task1() {
        val sum = loadInput()
            .map(::parseDimensions)
            .map(::computeArea)
            .sum()
        println("T1: area sum is $sum")
    }

    private fun parseDimensions(str: String): Triple<Int, Int, Int> {
        val dimensions = str.split("x")
            .map(String::toInt)
        return Triple(dimensions[0], dimensions[1], dimensions[2])
    }

    private fun computeArea(dimensions: Triple<Int, Int, Int>): Int {
        val area1 = dimensions.first * dimensions.second
        val area2 = dimensions.first * dimensions.third
        val area3 = dimensions.second * dimensions.third
        val minArea = min(area1, min(area2, area3))
        return 2 * (area1 + area2 + area3) + minArea
    }

    override fun task2() {
        val ribbonLength = loadInput()
            .map(::parseDimensions)
            .map(::computeRibbonLength)
            .sum()

        println("T3: ribbon length is $ribbonLength")
    }

    private fun computeRibbonLength(dimensions: Triple<Int, Int, Int>): Int {
        val perimeter1 = 2 * (dimensions.first + dimensions.second)
        val perimeter2 = 2 * (dimensions.first + dimensions.third)
        val perimeter3 = 2 * (dimensions.second + dimensions.third)

        val minPerimeter = min(perimeter1, min(perimeter2, perimeter3))

        val volume = dimensions.first * dimensions.second * dimensions.third

        return minPerimeter + volume
    }
}


