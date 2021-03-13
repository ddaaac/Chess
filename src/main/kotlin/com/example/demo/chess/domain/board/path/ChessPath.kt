package com.example.demo.chess.domain.board.path

import com.example.demo.chess.domain.board.ChessPosition

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

fun List<ChessPath?>.getExistPath(): ChessPath? {
    return firstOrNull { it != emptyPath() }
}

fun emptyPath(): ChessPath? = null
