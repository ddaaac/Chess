package com.example.demo.chess.domain.piece.type

import com.example.demo.chess.domain.board.ChessBoard
import com.example.demo.chess.domain.board.ChessCol
import com.example.demo.chess.domain.board.ChessPosition
import com.example.demo.chess.domain.board.ChessRow
import com.example.demo.chess.domain.piece.MOCK_PIECE_PLAYER_1
import com.example.demo.chess.domain.piece.MOCK_PIECE_PLAYER_2
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class KingTest {

    @ParameterizedTest
    @CsvSource(value = ["D,THREE", "D,FIVE", "C,FOUR", "E,FOUR", "C,FIVE", "C,THREE", "E,FIVE", "E,THREE"])
    fun `King은 destination이 비어있는 상하좌우 대각선 한 칸을 이동할 수 있다`(col: ChessCol, row: ChessRow) {
        val board = ChessBoard(mapOf(
                ChessPosition.get(ChessRow.FOUR, ChessCol.D) to KING_WHITE
        ))

        val result = KING.move(ChessPosition.get(ChessRow.FOUR, ChessCol.D), ChessPosition.get(row, col))

        Assertions.assertThat(result.canMoveWith(board)).isTrue()
    }

    @ParameterizedTest
    @CsvSource(value = ["D,THREE", "D,FIVE", "C,FOUR", "E,FOUR", "C,FIVE", "C,THREE", "E,FIVE", "E,THREE"])
    fun `King은 destination에 적 기물이 있는 경우 상하좌우 대각선 한 칸을 이동할 수 있다`(col: ChessCol, row: ChessRow) {
        val board = ChessBoard(mapOf(
                ChessPosition.get(ChessRow.FOUR, ChessCol.D) to KING_WHITE,
                ChessPosition.get(row, col) to MOCK_PIECE_PLAYER_2,
        ))

        val result = KING.move(ChessPosition.get(ChessRow.FOUR, ChessCol.D), ChessPosition.get(row, col))

        Assertions.assertThat(result.canMoveWith(board)).isTrue()
    }

    @Test
    fun `King은 destination이 아군 기물일 경우 이동할 수 없다`() {
        val board = ChessBoard(mapOf(
                ChessPosition.get(ChessRow.FOUR, ChessCol.D) to KING_WHITE,
                ChessPosition.get(ChessRow.THREE, ChessCol.D) to MOCK_PIECE_PLAYER_1,
        ))

        val result = KING.move(ChessPosition.get(ChessRow.FOUR, ChessCol.D), ChessPosition.get(ChessRow.THREE, ChessCol.D))

        Assertions.assertThat(result.canMoveWith(board)).isFalse()
    }
}
