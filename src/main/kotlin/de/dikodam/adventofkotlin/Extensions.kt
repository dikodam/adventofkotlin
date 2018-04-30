package de.dikodam.adventofkotlin

operator fun Pair<Int, Int>.plus(other: Pair<Int, Int>): Pair<Int, Int> {
    return Pair(this.first + other.first, this.second + other.second)
}

/**
 * calls task1() and task2() on the Day object
 */
fun <T : AbstractDay> T.doTheMagic() {
    this.task1()
    this.task2()
}