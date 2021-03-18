package com.example.demo.chess.domain.board

import java.lang.IllegalArgumentException

enum class ChessRow {
    ONE,
    TWO,
    THREE,
    FOUR,
    FIVE,
    SIX,
    SEVEN,
    EIGHT;

    fun next(step: Int): ChessRow =
            values().getOrNull(ordinal + step)
                    ?: throw IllegalArgumentException("Range of row is between 1 to 8")

    fun reversed(): ChessRow = values().let {
        it[it.size - ordinal - 1]
    }
}
