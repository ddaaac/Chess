package com.example.demo.chess.domain.board

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

internal class ChessRowTest {

    @ParameterizedTest
    @MethodSource("createRow")
    fun next(base: ChessRow, step: Int, expected: ChessRow) {
        assertThat(base.next(step)).isEqualTo(expected)

    }

    @ParameterizedTest
    @MethodSource("createExceptionRow")
    fun nextError(base: ChessRow, step: Int) {
        assertThatIllegalArgumentException()
                .isThrownBy { base.next(step) }
                .withMessage("Range of row is between 1 to 8")

    }

    companion object {
        @JvmStatic
        fun createRow() = listOf(
                Arguments.of(ChessRow.ONE, 1, ChessRow.TWO),
                Arguments.of(ChessRow.TWO, 1, ChessRow.THREE),
                Arguments.of(ChessRow.SEVEN, 1, ChessRow.EIGHT),
                Arguments.of(ChessRow.TWO, -1, ChessRow.ONE),
                Arguments.of(ChessRow.EIGHT, -1, ChessRow.SEVEN),
                Arguments.of(ChessRow.ONE, 7, ChessRow.EIGHT),
                Arguments.of(ChessRow.EIGHT, -7, ChessRow.ONE),
        )

        @JvmStatic
        fun createExceptionRow() = listOf(
                Arguments.of(ChessRow.ONE, -1),
                Arguments.of(ChessRow.TWO, -2),
                Arguments.of(ChessRow.SEVEN, -7),
                Arguments.of(ChessRow.TWO, 7),
                Arguments.of(ChessRow.EIGHT, 1),
                Arguments.of(ChessRow.ONE, 8),
                Arguments.of(ChessRow.EIGHT, 1),
        )
    }
}
