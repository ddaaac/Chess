package com.example.demo.chess.domain.board

import com.example.demo.chess.domain.board.ChessCol.*
import com.example.demo.chess.domain.board.ChessRow.*
import com.example.demo.chess.domain.piece.PieceDirection
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

internal class ChessPositionTest {

    @ParameterizedTest
    @MethodSource("generatePath")
    fun `시작 좌표에서 목표 좌표까지의 경로를 구한다`(start: ChessPosition, end: ChessPosition, direction: PieceDirection, expectedPath: ChessPath) {
        val result = start.getPathTo(end, direction)
        assertThat(result).usingRecursiveComparison()
                .isEqualTo(expectedPath)
    }

    companion object {
        @JvmStatic
        fun generatePath() = listOf(
                Arguments.of(ChessPosition.get(A, ONE), ChessPosition.get(A, FOUR), PieceDirection.N, ChessPath(listOf(
                        ChessPosition.get(A, ONE),
                        ChessPosition.get(A, TWO),
                        ChessPosition.get(A, THREE),
                        ChessPosition.get(A, FOUR),
                ))),
                Arguments.of(ChessPosition.get(A, ONE), ChessPosition.get(B, THREE), PieceDirection.NNE, ChessPath(listOf(
                        ChessPosition.get(A, ONE),
                        ChessPosition.get(B, THREE),
                ))),
                Arguments.of(ChessPosition.get(D, ONE), ChessPosition.get(H, FIVE), PieceDirection.NE, ChessPath(listOf(
                        ChessPosition.get(D, ONE),
                        ChessPosition.get(E, TWO),
                        ChessPosition.get(F, THREE),
                        ChessPosition.get(G, FOUR),
                        ChessPosition.get(H, FIVE),
                ))),
                Arguments.of(ChessPosition.get(D, EIGHT), ChessPosition.get(D, ONE), PieceDirection.S, ChessPath(listOf(
                        ChessPosition.get(D, EIGHT),
                        ChessPosition.get(D, SEVEN),
                        ChessPosition.get(D, SIX),
                        ChessPosition.get(D, FIVE),
                        ChessPosition.get(D, FOUR),
                        ChessPosition.get(D, THREE),
                        ChessPosition.get(D, TWO),
                        ChessPosition.get(D, ONE),
                ))),
                Arguments.of(ChessPosition.get(D, EIGHT), ChessPosition.get(B, ONE), PieceDirection.S, EMPTY_PATH),
                Arguments.of(ChessPosition.get(A, ONE), ChessPosition.get(B, THREE), PieceDirection.NE, EMPTY_PATH),
                Arguments.of(ChessPosition.get(D, ONE), ChessPosition.get(H, FIVE), PieceDirection.NNE, EMPTY_PATH),
        )
    }
}
