package com.example.demo.chess.domain.piece.type

import com.example.demo.chess.domain.board.*
import com.example.demo.chess.domain.piece.ChessPieceInGame
import com.example.demo.chess.domain.piece.MockPiece
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class KingTest {

    @ParameterizedTest
    @CsvSource(value = ["D,THREE", "D,FIVE", "C,FOUR", "E,FOUR", "C,FIVE", "C,THREE", "E,FIVE", "E,THREE"])
    fun `King은 destination이 비어있는 상하좌우 대각선 한 칸을 이동할 수 있다`(col: ChessCol, row: ChessRow) {
        val board = ChessBoard(mapOf(
                ChessPosition.get(ChessCol.D, ChessRow.FOUR) to ChessPieceInGame(KING, ChessPlayer.PLAYER_1)
        ))

        val result = KING.move(ChessPosition.get(ChessCol.D, ChessRow.FOUR), ChessPosition.get(col, row))

        Assertions.assertThat(result.canMoveWith(board)).isTrue()
    }

    @ParameterizedTest
    @CsvSource(value = ["D,THREE", "D,FIVE", "C,FOUR", "E,FOUR", "C,FIVE", "C,THREE", "E,FIVE", "E,THREE"])
    fun `King은 destination에 적 기물이 있는 경우 상하좌우 대각선 한 칸을 이동할 수 있다`(col: ChessCol, row: ChessRow) {
        val board = ChessBoard(mapOf(
                ChessPosition.get(ChessCol.D, ChessRow.FOUR) to ChessPieceInGame(KING, ChessPlayer.PLAYER_1),
                ChessPosition.get(col, row) to ChessPieceInGame(MockPiece(), ChessPlayer.PLAYER_2),
        ))

        val result = KING.move(ChessPosition.get(ChessCol.D, ChessRow.FOUR), ChessPosition.get(col, row))

        Assertions.assertThat(result.canMoveWith(board)).isTrue()
    }

    @Test
    fun `King은 destination이 아군 기물일 경우 이동할 수 없다`() {
        val board = ChessBoard(mapOf(
                ChessPosition.get(ChessCol.D, ChessRow.FOUR) to ChessPieceInGame(KING, ChessPlayer.PLAYER_1),
                ChessPosition.get(ChessCol.D, ChessRow.THREE) to ChessPieceInGame(MockPiece(), ChessPlayer.PLAYER_1),
        ))

        val result = KING.move(ChessPosition.get(ChessCol.D, ChessRow.FOUR), ChessPosition.get(ChessCol.D, ChessRow.THREE))

        Assertions.assertThat(result.canMoveWith(board)).isFalse()
    }
}
