package com.example.demo.chess.domain.board.path

import com.example.demo.chess.domain.board.ChessPosition

const val MIN_PATH_SIZE = 2

class ChessPath(val path: List<ChessPosition>) {
    init {
        require(path.size >= MIN_PATH_SIZE) {
            "Path should have at least source and destination"
        }
    }
}

fun List<ChessPath?>.getExistOrEmptyPath(): ChessPath? {
    return firstOrNull { it != emptyPath() }
}

fun emptyPath(): ChessPath? = null
