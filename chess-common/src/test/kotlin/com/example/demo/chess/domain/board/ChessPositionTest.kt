package com.example.demo.chess.domain.board

import com.example.demo.chess.domain.board.ChessCol.*
import com.example.demo.chess.domain.board.ChessRow.*
import com.example.demo.chess.domain.piece.play.PieceDirection
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

internal class ChessPositionTest {

    @ParameterizedTest
    @MethodSource("generatePath")
    fun `시작 좌표에서 목표 좌표까지의 경로를 구한다`(start: ChessPosition, end: ChessPosition, direction: PieceDirection, limit: Int, expectedPath: ChessPath?) {
        val result = start.findPathTo(end, direction, limit)
        assertThat(result).usingRecursiveComparison()
                .isEqualTo(expectedPath)
    }

    /**
     *      8
     *      7
     *      6
     *      5
     *      4
     *      3
     *      2
     *      1
     *         A  B  C  D  E  F  G  H
     *
     */
    companion object {
        @JvmStatic
        fun generatePath() = listOf(
                Arguments.of(ChessPosition.get(ONE, A), ChessPosition.get(FOUR, A), PieceDirection.N, 8, ChessPath(listOf(
                        ChessPosition.get(ONE, A),
                        ChessPosition.get(TWO, A),
                        ChessPosition.get(THREE, A),
                        ChessPosition.get(FOUR, A),
                ))),
                Arguments.of(ChessPosition.get(ONE, A), ChessPosition.get(THREE, B), PieceDirection.NNE, 8, ChessPath(listOf(
                        ChessPosition.get(ONE, A),
                        ChessPosition.get(THREE, B),
                ))),
                Arguments.of(ChessPosition.get(ONE, D), ChessPosition.get(FIVE, H), PieceDirection.NE, 8, ChessPath(listOf(
                        ChessPosition.get(ONE, D),
                        ChessPosition.get(TWO, E),
                        ChessPosition.get(THREE, F),
                        ChessPosition.get(FOUR, G),
                        ChessPosition.get(FIVE, H),
                ))),
                Arguments.of(ChessPosition.get(EIGHT, D), ChessPosition.get(ONE, D), PieceDirection.S, 7, ChessPath(listOf(
                        ChessPosition.get(EIGHT, D),
                        ChessPosition.get(SEVEN, D),
                        ChessPosition.get(SIX, D),
                        ChessPosition.get(FIVE, D),
                        ChessPosition.get(FOUR, D),
                        ChessPosition.get(THREE, D),
                        ChessPosition.get(TWO, D),
                        ChessPosition.get(ONE, D),
                ))),
                Arguments.of(ChessPosition.get(ONE, A), ChessPosition.get(THREE, A), PieceDirection.N, 2, ChessPath(listOf(
                        ChessPosition.get(ONE, A),
                        ChessPosition.get(TWO, A),
                        ChessPosition.get(THREE, A),
                ))),
                Arguments.of(ChessPosition.get(ONE, A), ChessPosition.get(THREE, A), PieceDirection.N, 1, null),
                Arguments.of(ChessPosition.get(EIGHT, D), ChessPosition.get(ONE, D), PieceDirection.S, 6, null),
                Arguments.of(ChessPosition.get(EIGHT, D), ChessPosition.get(ONE, B), PieceDirection.S, 8, null),
                Arguments.of(ChessPosition.get(ONE, A), ChessPosition.get(THREE, B), PieceDirection.NE, 8, null),
                Arguments.of(ChessPosition.get(ONE, D), ChessPosition.get(FIVE, H), PieceDirection.NNE, 8, null),
        )
    }

    @Test
    fun `대칭에 있는 좌표를 구한다`() {
        assertThat(ChessPosition.get(ONE, D).reversed()).isEqualTo(ChessPosition.get(EIGHT, E))
    }
}
