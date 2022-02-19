package com.example.demo.chess.domain.piece.type

import com.example.demo.chess.domain.board.*
import com.example.demo.chess.domain.piece.ChessPieceInGame
import com.example.demo.chess.domain.piece.MOCK_PIECE_PLAYER_1
import com.example.demo.chess.domain.piece.MOCK_PIECE_PLAYER_2
import com.example.demo.chess.domain.piece.MockPiece
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource

internal class QueenTest {

    /**
     *      8           x           x
     *      7  x
     *      6
     *      5           x
     *      4  x     x  q           x
     *      3
     *      2
     *      1  x        x        x
     *         A  B  C  D  E  F  G  H
     *
     */
    @ParameterizedTest
    @CsvSource(value = ["A,ONE", "D,ONE", "G,ONE", "A,FOUR", "H,FOUR", "A,SEVEN", "D,EIGHT", "H,EIGHT", "C,FOUR", "D,FIVE"])
    fun `Queen은 경로 중간에 기물이 존재하지 않고 destination이 비어있는 상하좌우 대각선 어느 칸으로 이동할 수 있다`(col: ChessCol, row: ChessRow) {
        val board = ChessBoard(mapOf(
                ChessPosition.get(ChessRow.FOUR, ChessCol.D) to QUEEN_WHITE
        ))

        val result = QUEEN.move(ChessPosition.get(ChessRow.FOUR, ChessCol.D), ChessPosition.get(row, col))

        Assertions.assertThat(result.canMoveWith(board)).isTrue()
    }

    @ParameterizedTest
    @CsvSource(value = ["A,ONE", "D,ONE", "G,ONE", "A,FOUR", "H,FOUR", "A,SEVEN", "D,EIGHT", "H,EIGHT", "C,FOUR", "D,FIVE"])
    fun `Queen은 경로 중간에 기물이 존재하지 않고 destination에 적 기물이 있는 경우 상하좌우 대각선 어느 칸으로 이동할 수 있다`(col: ChessCol, row: ChessRow) {
        val board = ChessBoard(mapOf(
                ChessPosition.get(ChessRow.FOUR, ChessCol.D) to QUEEN_WHITE,
                ChessPosition.get(row, col) to MOCK_PIECE_PLAYER_2,
        ))

        val result = QUEEN.move(ChessPosition.get(ChessRow.FOUR, ChessCol.D), ChessPosition.get(row, col))

        Assertions.assertThat(result.canMoveWith(board)).isTrue()
    }

    @Test
    fun `Queen은 destination이 아군 기물일 경우 이동할 수 없다`() {
        val board = ChessBoard(mapOf(
                ChessPosition.get(ChessRow.FOUR, ChessCol.D) to QUEEN_WHITE,
                ChessPosition.get(ChessRow.ONE, ChessCol.D) to MOCK_PIECE_PLAYER_1,
        ))

        val result = QUEEN.move(ChessPosition.get(ChessRow.FOUR, ChessCol.D), ChessPosition.get(ChessRow.ONE, ChessCol.D))

        Assertions.assertThat(result.canMoveWith(board)).isFalse()
    }

    @ParameterizedTest
    @ValueSource(strings = ["PLAYER_1", "PLAYER_2"])
    fun `Queen은 경로 중간에 기물이 있는 경우 이동할 수 없다`(player: ChessPlayer) {
        val board = ChessBoard(mapOf(
                ChessPosition.get(ChessRow.FOUR, ChessCol.D) to QUEEN_WHITE,
                ChessPosition.get(ChessRow.TWO, ChessCol.D) to ChessPieceInGame(MockPiece(), player),
        ))

        val result = QUEEN.move(ChessPosition.get(ChessRow.FOUR, ChessCol.D), ChessPosition.get(ChessRow.ONE, ChessCol.D))

        Assertions.assertThat(result.canMoveWith(board)).isFalse()
    }
}
