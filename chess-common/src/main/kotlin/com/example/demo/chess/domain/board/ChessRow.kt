package com.example.demo.chess.domain.board

enum class ChessRow {
    ONE,
    TWO,
    THREE,
    FOUR,
    FIVE,
    SIX,
    SEVEN,
    EIGHT;

    fun next(step: Int): ChessRow? = values().getOrNull(ordinal + step)

    fun reversed(): ChessRow = values().let {
        it[it.size - ordinal - 1]
    }
}
