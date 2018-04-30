package de.dikodam.adventofkotlin

import java.security.MessageDigest
import java.util.stream.IntStream

fun main(args: Array<String>) {
    Day04().doTheMagic()
}

class Day04 : AbstractDay() {

    override fun task1() {
        // take input
        // append decimal number
        // compute MD5 hash
        // find first number where the (hexadecimal) MD5 hash starts with 5 zeros
        val input = loadInput()[0]
        IntStream.iterate(1, { i -> i + 1 })
            .mapToObj { Pair(it, "$input$it") }
            .map { pair -> Pair(pair.first, computeMD5Hash(pair.second)) }
            .parallel()
            .filter { pair -> "00000" == pair.second.substring(0..5) }
            .findFirst()
            .ifPresentOrElse({ println("T1: The number to append is: ${it.first}") },
                { println("no value found") })
    }

    override fun task2() {
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