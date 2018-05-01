package de.dikodam.adventofkotlin

fun main(args: Array<String>) {
    Day05().doTheMagic()
}

class Day05 : AbstractDay() {

    val vowels = listOf('a', 'e', 'i', 'o', 'u')
    val forbiddenStrings = listOf("ab", "cd", "pq", "xy")

    override fun task1() {
        val count = loadInput()
            .filter(this::contains3OrMoreVowels)
            .filter(this::containsDuplicate)
            .filter(this::containsNoForbiddenString)
            .count()

        println("task 1: There are $count nice strings")
    }

    fun contains3OrMoreVowels(text: String): Boolean {
        return (0 until text.length)
            .map { text[it] }
            .distinct()
            .filter { vowels.contains(it.toChar()) }
            .count() >= 3
    }

    fun containsNoForbiddenString(text: String): Boolean =
        forbiddenStrings.none { text.contains(it) }

    fun containsDuplicate(text: String): Boolean =
        (1 until text.length).any { index -> text[index - 1] == text[index] }


    override fun task2() {
    }

    /*
    listOf("ugknbfddgicrmopn", "jchzalrnumimnmhp", "haegwjzuvuyypxyu", "dvszwmarrgswjxmb")
    .filter(this::contains3OrMoreVowels)
    .filter(this::containsDuplicate)
    .filter(this::containsNoForbiddenString)
*/
}