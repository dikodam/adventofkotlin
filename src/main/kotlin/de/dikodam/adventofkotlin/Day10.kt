package de.dikodam.adventofkotlin

import de.dikodam.adventofkotlin.KnotHash.Companion.defaultInitHash
import java.util.stream.IntStream
import kotlin.streams.toList

fun main(args: Array<String>) {
    Day10().runTasks()
}

class Day10 : AbstractDay() {
    private val input = loadInput()
    override fun task1() {
        val lengths = input[0]
            .split(",")
            .map(String::toInt)


        val finalHash = defaultInitHash().performOneHashingRound(lengths)

        val productOfFirstTwoNumbers = finalHash
            .numberSequence
            .asSequence()
            .take(2)
            .reduce(Int::times)

        println("Task 1: product of first 2 numberSequence of hash is $productOfFirstTwoNumbers")
    }

    override fun task2() {
        val lengths = input[0].parseToKnotHashParameters()

        val finalHash = defaultInitHash().fullHash(lengths)

        println("Task 2: dense hash is $finalHash")
    }
}

class KnotHash(val numberSequence: List<Int>, private val currentPosition: Int, private val skipSize: Int) {

    companion object {
        fun defaultInitHash(): KnotHash {
            return KnotHash(
                numberSequence = (0..255).toList(),
                currentPosition = 0,
                skipSize = 0)
        }
    }

    private fun performHashingStep(length: Int): KnotHash {
        val indicesToBeReversed = IntRange(start = currentPosition, endInclusive = currentPosition + length - 1)
            .map { it % numberSequence.size }

        val reversedIndices = indicesToBeReversed.reversed()

        val reversions: Map<Int, Int> = indicesToBeReversed.zip(reversedIndices).toMap()

        val newNumberSequence = numberSequence.indices
            .map { index -> reversions[index] ?: index }
            .map { index -> numberSequence[index] }

        return KnotHash(
            numberSequence = newNumberSequence,
            currentPosition = (currentPosition + skipSize + length) % numberSequence.size,
            skipSize = skipSize + 1)
    }

    fun performOneHashingRound(parameters: List<Int>): KnotHash =
        parameters.fold(this, KnotHash::performHashingStep)

    private fun perform64HashingRounds(parameters: List<Int>): KnotHash {
        var roundHash = this
        repeat(64) {
            roundHash = roundHash.performOneHashingRound(parameters)
        }
        return roundHash
    }

    fun fullHash(parameters: List<Int>): String {
        val sparseHash = perform64HashingRounds(parameters).numberSequence
        val denseHash = sparseHash.asSequence()
            .chunked(16)
            .map { listOf16Ints -> listOf16Ints.reduce(Int::xor) }
        return denseHash
            .map(Integer::toHexString)
            .map { it.leftPadZero() }
            .joinToString(separator = "")
    }
}

fun String.parseToKnotHashParameters(): List<Int> {
    val input = this.chars()
    val suffix = IntStream.of(17, 31, 73, 47, 23)
    return IntStream.concat(input, suffix).toList()
}

fun String.leftPadZero(): String {
    return if (this.length < 2) {
        "0$this"
    } else {
        this
    }
}
