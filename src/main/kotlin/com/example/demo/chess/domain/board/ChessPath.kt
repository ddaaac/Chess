package com.example.demo.chess.domain.board

class ChessPath(val path: List<ChessPosition>) {
    fun isEmpty() = path.isEmpty()
}

val EMPTY_PATH = ChessPath(emptyList())

fun List<ChessPath>.getPath(): ChessPath {
    return first { isNotEmpty() }
}
