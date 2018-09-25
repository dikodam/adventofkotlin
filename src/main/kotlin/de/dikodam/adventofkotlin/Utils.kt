package de.dikodam.adventofkotlin

typealias Coordinates = Pair<Int, Int>

/**
 * return a list with all pairs, e.g. Pair(0,0) .. Pair(2,2) returns [(0,0), (0,1), (0,2), (1,0), (1,1), (1,2), (2,0), (2,1), (2,2)]
 */
operator fun Pair<Int, Int>.rangeTo(other: Pair<Int, Int>): List<Pair<Int, Int>> {
    val (fromX, fromY) = this
    val (toX, toY) = other
    return (fromX..toX)
        .flatMap { x ->
            (fromY..toY)
                .map { y -> Pair(x, y) }
        }
}

operator fun Pair<Int, Int>.plus(other: Pair<Int, Int>): Pair<Int, Int> {
    return Pair(this.first + other.first, this.second + other.second)
}

