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

internal class MovingTest {

    @ParameterizedTest
    @MethodSource("generateJust")
    fun `JustMoving은 destination이 비어있으면 움직일 수 있다`(path: ChessPath, board: Map<ChessPosition, ChessPieceInGame>, expected: Boolean) {
        val justMoving = Moving()

        val result: Boolean = justMoving.canPlay(path, ChessBoard(board))

        Assertions.assertThat(result).isEqualTo(expected)
    }

    companion object {
        private val mockPiece = MOCK_PIECE_PLAYER_1
        private val mockPieceOpponent = MOCK_PIECE_PLAYER_2

        @JvmStatic
        fun generateJust() = listOf(
                Arguments.of(
                        ChessPath(listOf(
                                ChessPosition.get(ChessCol.A, ChessRow.ONE),
                                ChessPosition.get(ChessCol.B, ChessRow.TWO),
                                ChessPosition.get(ChessCol.C, ChessRow.THREE))
                        ),
                        mapOf(
                                ChessPosition.get(ChessCol.A, ChessRow.ONE) to EMPTY_PIECE,
                                ChessPosition.get(ChessCol.B, ChessRow.TWO) to EMPTY_PIECE,
                                ChessPosition.get(ChessCol.C, ChessRow.THREE) to EMPTY_PIECE,
                        ),
                        false
                ),
                Arguments.of(
                        ChessPath(listOf(
                                ChessPosition.get(ChessCol.A, ChessRow.ONE),
                                ChessPosition.get(ChessCol.B, ChessRow.TWO),
                                ChessPosition.get(ChessCol.C, ChessRow.THREE))
                        ),
                        mapOf(
                                ChessPosition.get(ChessCol.A, ChessRow.ONE) to mockPiece,
                                ChessPosition.get(ChessCol.B, ChessRow.TWO) to EMPTY_PIECE,
                                ChessPosition.get(ChessCol.C, ChessRow.THREE) to EMPTY_PIECE,
                        ),
                        true
                ),
                Arguments.of(
                        ChessPath(listOf(
                                ChessPosition.get(ChessCol.A, ChessRow.ONE),
                                ChessPosition.get(ChessCol.B, ChessRow.TWO),
                                ChessPosition.get(ChessCol.C, ChessRow.THREE))
                        ),
                        mapOf(
                                ChessPosition.get(ChessCol.A, ChessRow.ONE) to mockPiece,
                                ChessPosition.get(ChessCol.B, ChessRow.TWO) to EMPTY_PIECE,
                                ChessPosition.get(ChessCol.C, ChessRow.THREE) to mockPieceOpponent,
                        ),
                        false
                ),
                Arguments.of(
                        ChessPath(listOf(
                                ChessPosition.get(ChessCol.A, ChessRow.ONE),
                                ChessPosition.get(ChessCol.B, ChessRow.TWO),
                                ChessPosition.get(ChessCol.C, ChessRow.THREE))
                        ),
                        mapOf(
                                ChessPosition.get(ChessCol.A, ChessRow.ONE) to mockPiece,
                                ChessPosition.get(ChessCol.B, ChessRow.TWO) to mockPieceOpponent,
                                ChessPosition.get(ChessCol.C, ChessRow.THREE) to EMPTY_PIECE,
                        ),
                        true
                ),
                Arguments.of(
                        ChessPath(listOf(
                                ChessPosition.get(ChessCol.A, ChessRow.ONE),
                                ChessPosition.get(ChessCol.B, ChessRow.TWO))
                        ),
                        mapOf(
                                ChessPosition.get(ChessCol.A, ChessRow.ONE) to mockPiece,
                                ChessPosition.get(ChessCol.B, ChessRow.TWO) to EMPTY_PIECE,
                        ),
                        true
                ),
                Arguments.of(
                        ChessPath(listOf(
                                ChessPosition.get(ChessCol.A, ChessRow.ONE),
                                ChessPosition.get(ChessCol.B, ChessRow.TWO))
                        ),
                        mapOf(
                                ChessPosition.get(ChessCol.A, ChessRow.ONE) to mockPiece,
                                ChessPosition.get(ChessCol.B, ChessRow.TWO) to mockPiece,
                        ),
                        false
                )
        )
    }
}
