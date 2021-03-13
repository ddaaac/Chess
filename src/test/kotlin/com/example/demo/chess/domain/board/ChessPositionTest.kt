package com.example.demo.chess.domain.board

import com.example.demo.chess.domain.board.ChessCol.*
import com.example.demo.chess.domain.board.ChessRow.*
import com.example.demo.chess.domain.board.path.ChessPath
import com.example.demo.chess.domain.board.path.emptyPath
import com.example.demo.chess.domain.piece.move.PieceDirection
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

internal class ChessPositionTest {

    @ParameterizedTest
    @MethodSource("generatePath")
    fun `시작 좌표에서 목표 좌표까지의 경로를 구한다`(start: ChessPosition, end: ChessPosition, direction: PieceDirection, limit: Int, expectedPath: ChessPath?) {
        val result = start.getPathTo(end, direction, limit)
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
                Arguments.of(ChessPosition.get(A, ONE), ChessPosition.get(A, FOUR), PieceDirection.N, 8, ChessPath(listOf(
                        ChessPosition.get(A, ONE),
                        ChessPosition.get(A, TWO),
                        ChessPosition.get(A, THREE),
                        ChessPosition.get(A, FOUR),
                ))),
                Arguments.of(ChessPosition.get(A, ONE), ChessPosition.get(B, THREE), PieceDirection.NNE, 8, ChessPath(listOf(
                        ChessPosition.get(A, ONE),
                        ChessPosition.get(B, THREE),
                ))),
                Arguments.of(ChessPosition.get(D, ONE), ChessPosition.get(H, FIVE), PieceDirection.NE, 8, ChessPath(listOf(
                        ChessPosition.get(D, ONE),
                        ChessPosition.get(E, TWO),
                        ChessPosition.get(F, THREE),
                        ChessPosition.get(G, FOUR),
                        ChessPosition.get(H, FIVE),
                ))),
                Arguments.of(ChessPosition.get(D, EIGHT), ChessPosition.get(D, ONE), PieceDirection.S, 7, ChessPath(listOf(
                        ChessPosition.get(D, EIGHT),
                        ChessPosition.get(D, SEVEN),
                        ChessPosition.get(D, SIX),
                        ChessPosition.get(D, FIVE),
                        ChessPosition.get(D, FOUR),
                        ChessPosition.get(D, THREE),
                        ChessPosition.get(D, TWO),
                        ChessPosition.get(D, ONE),
                ))),
                Arguments.of(ChessPosition.get(A, ONE), ChessPosition.get(A, THREE), PieceDirection.N, 2, ChessPath(listOf(
                        ChessPosition.get(A, ONE),
                        ChessPosition.get(A, TWO),
                        ChessPosition.get(A, THREE),
                ))),
                Arguments.of(ChessPosition.get(A, ONE), ChessPosition.get(A, THREE), PieceDirection.N, 1, emptyPath()),
                Arguments.of(ChessPosition.get(D, EIGHT), ChessPosition.get(D, ONE), PieceDirection.S, 6, emptyPath()),
                Arguments.of(ChessPosition.get(D, EIGHT), ChessPosition.get(B, ONE), PieceDirection.S, 8, emptyPath()),
                Arguments.of(ChessPosition.get(A, ONE), ChessPosition.get(B, THREE), PieceDirection.NE, 8, emptyPath()),
                Arguments.of(ChessPosition.get(D, ONE), ChessPosition.get(H, FIVE), PieceDirection.NNE, 8, emptyPath()),
        )
    }
}
