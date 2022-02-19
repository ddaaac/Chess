package com.example.demo.chess.domain.piece

import com.example.demo.chess.domain.board.ChessCol
import com.example.demo.chess.domain.board.ChessCol.*
import com.example.demo.chess.domain.board.ChessRow
import com.example.demo.chess.domain.board.ChessRow.*
import com.example.demo.chess.domain.piece.play.PieceDirection
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

internal class PieceDirectionTest {

    @ParameterizedTest
    @MethodSource("generateDirections")
    fun `D4를 기준으로 각 방향으로 한 번 움직인 좌표를 구한다`(direction: PieceDirection, expectedRow: ChessRow, expectedCol: ChessCol) {
        val result = direction.nextOf(FOUR, D)
        assertThat(result).isEqualTo(expectedRow to expectedCol)
    }

    companion object {
        @JvmStatic
        // D4
        fun generateDirections() = listOf(
                Arguments.of(PieceDirection.N, FIVE, D),
                Arguments.of(PieceDirection.S, THREE, D),
                Arguments.of(PieceDirection.W, FOUR, C),
                Arguments.of(PieceDirection.E, FOUR, E),
                Arguments.of(PieceDirection.NW, FIVE, C),
                Arguments.of(PieceDirection.NE, FIVE, E),
                Arguments.of(PieceDirection.SW, THREE, C),
                Arguments.of(PieceDirection.SE, THREE, E),
                Arguments.of(PieceDirection.NNW, SIX, C),
                Arguments.of(PieceDirection.NWW, FIVE, B),
                Arguments.of(PieceDirection.NNE, SIX, E),
                Arguments.of(PieceDirection.NEE, FIVE, F),
                Arguments.of(PieceDirection.SSW, TWO, C),
                Arguments.of(PieceDirection.SWW, THREE, B),
                Arguments.of(PieceDirection.SSE, TWO, E),
                Arguments.of(PieceDirection.SEE, THREE, F),
        )
    }
}
