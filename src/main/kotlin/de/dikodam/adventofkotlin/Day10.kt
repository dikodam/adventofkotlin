package de.dikodam.adventofkotlin

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

        val initHash = KnotHash(
            numberSequence = (0..255).toList(),
            currentPosition = 0,
            skipSize = 0)

        val finalHash = lengths.fold(initHash, KnotHash::hash)

        val productOfFirstTwoNumbers = finalHash
            .numberSequence
            .asSequence()
            .take(2)
            .reduce(Int::times)

        println("Task 1: product of first 2 numberSequence of hash is $productOfFirstTwoNumbers")
    }

    override fun task2() {
        val inputLengthsStream = input[0].chars()
        val suffixLengthsStream = IntStream.of(17, 31, 73, 47, 23)

        val lengths = IntStream.concat(inputLengthsStream, suffixLengthsStream).toList()

        var roundInitHash = KnotHash(
            numberSequence = (0..255).toList(),
            currentPosition = 0,
            skipSize = 0)

        repeat(64) {
            roundInitHash = performHashingRound(roundInitHash, lengths)
        }

        val sparseHash = roundInitHash.numberSequence
        val denseHash = sparseHash.asSequence()
            .chunked(16)
            .map { listOf16Ints -> listOf16Ints.reduce(Int::xor) }
            .map(Integer::toHexString)
            .map { it.leftPadZero() }
            .joinToString(separator = "")

        println("Task 2: dense hash is $denseHash")

    }

    private fun String.leftPadZero(): String {
        return if (this.length < 2) {
            "0$this"
        } else {
            this
        }
    }
}

class KnotHash(val numberSequence: List<Int>, private val currentPosition: Int, private val skipSize: Int) {

    fun hash(length: Int): KnotHash {
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
}

fun performHashingRound(initHash: KnotHash, lengths: List<Int>) =
    lengths.fold(initHash, KnotHash::hash)
