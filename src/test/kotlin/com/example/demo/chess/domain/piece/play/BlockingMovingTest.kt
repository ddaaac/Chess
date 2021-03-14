package com.example.demo.chess.domain.piece.play

import com.example.demo.chess.domain.board.*
import com.example.demo.chess.domain.piece.ChessPieceInGame
import com.example.demo.chess.domain.piece.MockPiece
import com.example.demo.chess.domain.piece.type.EMPTY_PIECE
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

internal class BlockingMovingTest {

    @ParameterizedTest
    @MethodSource("generateBlocking")
    fun `BlockingMoving은 source와 destination을 제외한 경로에 다른 기물이 존재하지 않으면 움직일 수 있다`(path: ChessPath, board: Map<ChessPosition, ChessPieceInGame>, expected: Boolean) {
        val blockMoving = Block()

        val result: Boolean = blockMoving.canPlay(path, ChessBoard(board))

        assertThat(result).isEqualTo(expected)
    }

    companion object {
        private val mockPiece = ChessPieceInGame(MockPiece(), ChessPlayer.PLAYER_1)
        private val mockPieceOpponent = ChessPieceInGame(MockPiece(), ChessPlayer.PLAYER_2)

        @JvmStatic
        fun generateBlocking() = listOf(
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
                                ChessPosition.get(ChessCol.B, ChessRow.TWO) to mockPieceOpponent,
                                ChessPosition.get(ChessCol.C, ChessRow.THREE) to EMPTY_PIECE,
                        ),
                        false
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
                        true
                )
        )
    }
}


