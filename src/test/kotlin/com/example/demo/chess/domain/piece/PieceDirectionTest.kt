package com.example.demo.chess.domain.piece

import com.example.demo.chess.domain.board.ChessCol
import com.example.demo.chess.domain.board.ChessCol.*
import com.example.demo.chess.domain.board.ChessRow
import com.example.demo.chess.domain.board.ChessRow.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

internal class PieceDirectionTest {

    @ParameterizedTest
    @MethodSource("generateDirections")
    fun `D4를 기준으로 각 방향으로 한 번 움직인 좌표를 구한다`(direction: PieceDirection, expectedCol: ChessCol, expectedRow: ChessRow) {
        val result = direction.nextOf(D, FOUR)
        assertThat(result).isEqualTo(expectedCol to expectedRow)
    }

    companion object {
        @JvmStatic
        // D4
        fun generateDirections() = listOf(
                Arguments.of(PieceDirection.N, D, FIVE),
                Arguments.of(PieceDirection.S, D, THREE),
                Arguments.of(PieceDirection.W, C, FOUR),
                Arguments.of(PieceDirection.E, E, FOUR),
                Arguments.of(PieceDirection.NW, C, FIVE),
                Arguments.of(PieceDirection.NE, E, FIVE),
                Arguments.of(PieceDirection.SW, C, THREE),
                Arguments.of(PieceDirection.SE, E, THREE),
                Arguments.of(PieceDirection.NNW, C, SIX),
                Arguments.of(PieceDirection.NWW, B, FIVE),
                Arguments.of(PieceDirection.NNE, E, SIX),
                Arguments.of(PieceDirection.NEE, F, FIVE),
                Arguments.of(PieceDirection.SSW, C, TWO),
                Arguments.of(PieceDirection.SWW, B, THREE),
                Arguments.of(PieceDirection.SSE, E, TWO),
                Arguments.of(PieceDirection.SEE, F, THREE),
        )
    }
}
