package com.example.demo.chess.domain.piece.play

import com.example.demo.chess.domain.board.*
import com.example.demo.chess.domain.piece.ChessPieceInGame
import com.example.demo.chess.domain.piece.MOCK_PIECE_PLAYER_1
import com.example.demo.chess.domain.piece.MOCK_PIECE_PLAYER_2
import com.example.demo.chess.domain.piece.type.EMPTY_PIECE
import org.assertj.core.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

internal class AttackTest {

    @ParameterizedTest
    @MethodSource("generateAttack")
    fun `AttackMoving은 source와 destination이 opponent 관계면 움직일 수 있다`(path: ChessPath, board: Map<ChessPosition, ChessPieceInGame>, expected: Boolean) {
        val attackMoving = Attack()

        val result: Boolean = attackMoving.canPlay(path, ChessBoard(board))

        Assertions.assertThat(result).isEqualTo(expected)
    }

    companion object {
        private val mockPiece = MOCK_PIECE_PLAYER_1
        private val mockPieceOpponent = MOCK_PIECE_PLAYER_2

        @JvmStatic
        fun generateAttack() = listOf(
                Arguments.of(
                        ChessPath(listOf(
                                ChessPosition.get(ChessRow.ONE, ChessCol.A),
                                ChessPosition.get(ChessRow.TWO, ChessCol.B),
                                ChessPosition.get(ChessRow.THREE, ChessCol.C))
                        ),
                        mapOf(
                                ChessPosition.get(ChessRow.ONE, ChessCol.A) to EMPTY_PIECE,
                                ChessPosition.get(ChessRow.TWO, ChessCol.B) to EMPTY_PIECE,
                                ChessPosition.get(ChessRow.THREE, ChessCol.C) to EMPTY_PIECE,
                        ),
                        false
                ),
                Arguments.of(
                        ChessPath(listOf(
                                ChessPosition.get(ChessRow.ONE, ChessCol.A),
                                ChessPosition.get(ChessRow.TWO, ChessCol.B),
                                ChessPosition.get(ChessRow.THREE, ChessCol.C))
                        ),
                        mapOf(
                                ChessPosition.get(ChessRow.ONE, ChessCol.A) to mockPiece,
                                ChessPosition.get(ChessRow.TWO, ChessCol.B) to EMPTY_PIECE,
                                ChessPosition.get(ChessRow.THREE, ChessCol.C) to EMPTY_PIECE,
                        ),
                        false
                ),
                Arguments.of(
                        ChessPath(listOf(
                                ChessPosition.get(ChessRow.ONE, ChessCol.A),
                                ChessPosition.get(ChessRow.TWO, ChessCol.B),
                                ChessPosition.get(ChessRow.THREE, ChessCol.C))
                        ),
                        mapOf(
                                ChessPosition.get(ChessRow.ONE, ChessCol.A) to mockPiece,
                                ChessPosition.get(ChessRow.TWO, ChessCol.B) to EMPTY_PIECE,
                                ChessPosition.get(ChessRow.THREE, ChessCol.C) to mockPieceOpponent,
                        ),
                        true
                ),
                Arguments.of(
                        ChessPath(listOf(
                                ChessPosition.get(ChessRow.ONE, ChessCol.A),
                                ChessPosition.get(ChessRow.TWO, ChessCol.B),
                                ChessPosition.get(ChessRow.THREE, ChessCol.C))
                        ),
                        mapOf(
                                ChessPosition.get(ChessRow.ONE, ChessCol.A) to mockPiece,
                                ChessPosition.get(ChessRow.TWO, ChessCol.B) to mockPieceOpponent,
                                ChessPosition.get(ChessRow.THREE, ChessCol.C) to EMPTY_PIECE,
                        ),
                        false
                ),
                Arguments.of(
                        ChessPath(listOf(
                                ChessPosition.get(ChessRow.ONE, ChessCol.A),
                                ChessPosition.get(ChessRow.TWO, ChessCol.B))
                        ),
                        mapOf(
                                ChessPosition.get(ChessRow.ONE, ChessCol.A) to mockPiece,
                                ChessPosition.get(ChessRow.TWO, ChessCol.B) to EMPTY_PIECE,
                        ),
                        false
                ),
                Arguments.of(
                        ChessPath(listOf(
                                ChessPosition.get(ChessRow.ONE, ChessCol.A),
                                ChessPosition.get(ChessRow.TWO, ChessCol.B))
                        ),
                        mapOf(
                                ChessPosition.get(ChessRow.ONE, ChessCol.A) to mockPiece,
                                ChessPosition.get(ChessRow.TWO, ChessCol.B) to mockPiece,
                        ),
                        false
                ),
                Arguments.of(
                        ChessPath(listOf(
                                ChessPosition.get(ChessRow.ONE, ChessCol.A),
                                ChessPosition.get(ChessRow.TWO, ChessCol.B))
                        ),
                        mapOf(
                                ChessPosition.get(ChessRow.ONE, ChessCol.A) to mockPiece,
                                ChessPosition.get(ChessRow.TWO, ChessCol.B) to mockPieceOpponent,
                        ),
                        true
                )
        )
    }
}
