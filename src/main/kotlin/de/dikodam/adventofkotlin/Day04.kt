package de.dikodam.adventofkotlin

import java.security.MessageDigest
import java.util.stream.IntStream

fun main(args: Array<String>) {
    Day04().runTasks()
}

class Day04 : AbstractDay() {

    override fun task1() {
        // take input
        // append decimal number
        // compute MD5 hash
        // find first number where the (hexadecimal) MD5 hash starts with 5 zeros
        val input = loadInput()[0]
        findFirstHashWithDifficulty(5, input)
        { pair: Pair<Int, String> -> println("T1: The number to append is: ${pair.first}") }
    }

    private fun findFirstHashWithDifficulty(difficulty: Int,
                                            seedString: String,
                                            consumeOnSuccess: (Pair<Int, String>) -> Unit) {
        IntStream.iterate(1) { i -> i + 1 }
            .parallel() // TODO no idea if this really adds efficiency...
            .mapToObj { Pair(it, "$seedString$it") }
            .map { pair -> Pair(pair.first, computeMD5Hash(pair.second)) }
            .filter { (_, hash) -> hasLeadingZeroes(difficulty)(hash) }
            .findFirst()
            .ifPresent { consumeOnSuccess(it) }
    }

    private fun hasLeadingZeroes(zeroCount: Int): (String) -> Boolean {
        val zeroes = "0".repeat(zeroCount)
        return { input -> zeroes == input.substring(0..(zeroCount - 1)) }
    }

    override fun task2() {
        val input = loadInput()[0]
        findFirstHashWithDifficulty(6, input)
        { pair: Pair<Int, String> -> println("T2: The number to append is: ${pair.first}") }
    }

    private fun computeMD5Hash(input: String): String {
        return MessageDigest.getInstance("MD5")
            .digest(input.toByteArray())
            .toHexString()
    }

    private fun ByteArray.toHexString(): String = buildString {
        for (byte in this@toHexString) {
            append(String.format("%02x", byte))
        }
    }
}