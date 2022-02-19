package com.example.demo.chess.domain.board

enum class ChessCol {
    A,
    B,
    C,
    D,
    E,
    F,
    G,
    H;

    fun next(step: Int): ChessCol? = values().getOrNull(ordinal + step)

    fun reversed(): ChessCol = values().let {
        it[it.size - ordinal - 1]
    }
}
