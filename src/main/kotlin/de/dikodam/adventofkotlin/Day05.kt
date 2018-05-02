package de.dikodam.adventofkotlin

fun main(args: Array<String>) {
    Day05().doTheMagic()
}

class Day05 : AbstractDay() {

    private val vowels = listOf('a', 'e', 'i', 'o', 'u')
    private val forbiddenStrings = listOf("ab", "cd", "pq", "xy")

    override fun task1() {
        val count = loadInput()
            .filter(this::contains3OrMoreVowels)
            .filter(this::containsDuplicate)
            .filter(this::containsNoForbiddenString)
            .count()

        println("task 1: There are $count nice strings")
    }

    private fun contains3OrMoreVowels(line: String): Boolean {
        return (0 until line.length)
            .map { line[it] }
            .filter { vowels.contains(it) }
            .count() >= 3
    }

    private fun containsNoForbiddenString(line: String): Boolean =
        forbiddenStrings.none { line.contains(it) }

    private fun containsDuplicate(line: String): Boolean =
        (1 until line.length).any { index -> line[index - 1] == line[index] }


    override fun task2() {
        val count = loadInput()
            .filter { containsDuplicatePair(it) }
            .filter { containsDuplicateWith1CharInBetween(it) }
            .count()
        println("task 2: $count nice strings found")
    }

    private fun containsDuplicatePair(line: String): Boolean {
        return true
    }

    private fun containsDuplicateWith1CharInBetween(line: String): Boolean =
        (2 until line.length).any { index -> line[index - 2] == line[index] }

}