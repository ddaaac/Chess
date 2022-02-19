package com.example.demo.chess.domain.board

import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.MethodSource

internal class ChessColTest {

    @ParameterizedTest
    @MethodSource("createCol")
    fun next(base: ChessCol, step: Int, expected: ChessCol) {
        assertThat(base.next(step)).isEqualTo(expected)

    }

    @ParameterizedTest
    @MethodSource("createExceptionCol")
    fun nextNotExist(base: ChessCol, step: Int) {
        assertThat(base.next(step)).isNull()
    }

    companion object {
        @JvmStatic
        fun createCol() = listOf(
                Arguments.of(ChessCol.A, 1, ChessCol.B),
                Arguments.of(ChessCol.B, 1, ChessCol.C),
                Arguments.of(ChessCol.G, 1, ChessCol.H),
                Arguments.of(ChessCol.B, -1, ChessCol.A),
                Arguments.of(ChessCol.H, -1, ChessCol.G),
                Arguments.of(ChessCol.A, 7, ChessCol.H),
                Arguments.of(ChessCol.H, -7, ChessCol.A),
        )

        @JvmStatic
        fun createExceptionCol() = listOf(
                Arguments.of(ChessCol.A, -1),
                Arguments.of(ChessCol.B, -2),
                Arguments.of(ChessCol.G, -7),
                Arguments.of(ChessCol.B, 7),
                Arguments.of(ChessCol.H, 1),
                Arguments.of(ChessCol.A, 8),
                Arguments.of(ChessCol.H, 1),
        )
    }

    @ParameterizedTest
    @CsvSource(value = ["A,H", "B,G", "C,F", "D,E", "E,D", "F,C", "G,B", "H,A"])
    fun `대칭되는 col를 반환한다`(col: ChessCol, expected: ChessCol) {
        Assertions.assertThat(col.reversed()).isEqualTo(expected)
    }
}
