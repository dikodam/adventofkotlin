package de.dikodam.adventofkotlin

import java.util.stream.Stream

fun main(args: Array<String>) {
    AbstractDay.doTheMagic(Day03())
}

class Day03 : AbstractDay() {

    override fun task1() {
        val movements = input[0].split("")
            .map(::parseDirectionSignToMovement)
        val movementsIterator = movements.iterator()
        val positionCount = Stream.iterate(Pair(0, 0), { it + movementsIterator.next() })
            .limit(movements.size.toLong())
            .distinct()
            .count()

        println("T1: visited positions: $positionCount")
    }

    private fun parseDirectionSignToMovement(direction: String): Pair<Int, Int> {
        return when (direction) {
            "^" -> Pair(0, 1)
            "v" -> Pair(0, -1)
            "<" -> Pair(-1, 0)
            ">" -> Pair(1, 0)
            else -> Pair(0, 0)
        }
    }

    override fun task2() {
        val movements = input[0].split("")
            .map(::parseDirectionSignToMovement)
        val santaPositions = buildVisitedPositionsStream(movements, indexFilter = { it % 2 == 0 })
        val roboPositions = buildVisitedPositionsStream(movements, indexFilter = { it % 2 != 0 })
        val positionCount = Stream.concat(santaPositions, roboPositions)
            .distinct()
            .count()

        println("T2: count visited positions: $positionCount")
    }

    private fun buildVisitedPositionsStream(movements: List<Pair<Int, Int>>,
                                            indexFilter: (Int) -> Boolean): Stream<Pair<Int, Int>> {

        val filteredMoves = movements.filterIndexed { index, _ -> indexFilter(index) }
        val iterator = filteredMoves.iterator()
        return Stream.iterate(Pair(0, 0), { it + iterator.next() })
            .limit(filteredMoves.size.toLong())
    }

}