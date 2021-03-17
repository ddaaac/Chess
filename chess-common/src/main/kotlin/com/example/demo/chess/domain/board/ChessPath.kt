package com.example.demo.chess.domain.board

const val MIN_PATH_SIZE = 2

class ChessPath(val path: List<ChessPosition>) {

    init {
        require(path.size >= MIN_PATH_SIZE) {
            "Path should have at least source and destination"
        }
    }

    val source: ChessPosition = path.first()
    val destination: ChessPosition = path.last()

    fun isSizeOf(size: Int) = (path.size == size)
}

fun List<ChessPath?>.findNotEmptyPath(): ChessPath? {
    return firstOrNull { it != emptyPath() }
}

fun emptyPath(): ChessPath? = null
