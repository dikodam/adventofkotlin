package de.dikodam.adventofkotlin

fun main(args: Array<String>) {
    AbstractDay.doTheMagicWithKotlin(Day02())
}
class Day02 : AbstractDay(){
    override fun task1() {
        input.forEach { println(it) }
    }

    override fun task2() {
    }

}