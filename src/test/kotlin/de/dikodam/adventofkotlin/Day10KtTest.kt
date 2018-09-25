package de.dikodam.adventofkotlin

import org.hamcrest.core.Is.`is`
import org.hamcrest.core.IsEqual.equalTo
import org.junit.Assert.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

internal class Day10Test {

    @Test
    fun parseToKnotHashLengths() {
        val result = "1,2,3".parseToKnotHashParameters()
        assertThat(result, `is`(listOf(49, 44, 50, 44, 51, 17, 31, 73, 47, 23)))
    }

    @Test
    fun leftPadZero() {
        assertAll(
            {assertThat("a".leftPadZero(), equalTo("0a"))},
            {assertThat("ab".leftPadZero(), equalTo("ab"))}
        )
    }
}