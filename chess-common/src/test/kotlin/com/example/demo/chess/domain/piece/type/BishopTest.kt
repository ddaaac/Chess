package com.example.demo.chess.domain.piece.type

import com.example.demo.chess.domain.board.*
import com.example.demo.chess.domain.piece.ChessPieceInGame
import com.example.demo.chess.domain.piece.MockPiece
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource

internal class BishopTest {

    /**
     *      8                       x
     *      7  x
     *      6
     *      5        x
     *      4           q
     *      3        x
     *      2
     *      1  x                 x
     *         A  B  C  D  E  F  G  H
     *
     */
    @ParameterizedTest
    @CsvSource(value = ["A,ONE", "G,ONE", "A,SEVEN", "H,EIGHT", "C,FIVE", "C,THREE"])
    fun `Bishop은 경로 중간에 기물이 존재하지 않고 destination이 비어있는 대각선 어느 칸으로 이동할 수 있다`(col: ChessCol, row: ChessRow) {
        val board = ChessBoard(mapOf(
                ChessPosition.get(ChessRow.FOUR, ChessCol.D) to BISHOP_WHITE
        ))

        val result = BISHOP.move(ChessPosition.get(ChessRow.FOUR, ChessCol.D), ChessPosition.get(row, col))

        Assertions.assertThat(result.canMoveWith(board)).isTrue()
    }

    @ParameterizedTest
    @CsvSource(value = ["A,ONE", "G,ONE", "A,SEVEN", "H,EIGHT", "C,FIVE", "C,THREE"])
    fun `Bishop은 경로 중간에 기물이 존재하지 않고 destination에 적 기물이 있는 경우 대각선 어느 칸으로 이동할 수 있다`(col: ChessCol, row: ChessRow) {
        val board = ChessBoard(mapOf(
                ChessPosition.get(ChessRow.FOUR, ChessCol.D) to BISHOP_WHITE,
                ChessPosition.get(row, col) to BISHOP_BLACK,
        ))

        val result = BISHOP.move(ChessPosition.get(ChessRow.FOUR, ChessCol.D), ChessPosition.get(row, col))

        Assertions.assertThat(result.canMoveWith(board)).isTrue()
    }

    @Test
    fun `Bishop은 destination이 아군 기물일 경우 이동할 수 없다`() {
        val board = ChessBoard(mapOf(
                ChessPosition.get(ChessRow.FOUR, ChessCol.D) to BISHOP_WHITE,
                ChessPosition.get(ChessRow.ONE, ChessCol.A) to BISHOP_WHITE,
        ))

        val result = BISHOP.move(ChessPosition.get(ChessRow.FOUR, ChessCol.D), ChessPosition.get(ChessRow.ONE, ChessCol.A))

        Assertions.assertThat(result.canMoveWith(board)).isFalse()
    }

    @ParameterizedTest
    @ValueSource(strings = ["PLAYER_1", "PLAYER_2"])
    fun `Bishop 경로 중간에 기물이 있는 경우 이동할 수 없다`(player: ChessPlayer) {
        val board = ChessBoard(mapOf(
                ChessPosition.get(ChessRow.FOUR, ChessCol.D) to BISHOP_WHITE,
                ChessPosition.get(ChessRow.TWO, ChessCol.B) to ChessPieceInGame(MockPiece(), player),
        ))

        val result = BISHOP.move(ChessPosition.get(ChessRow.FOUR, ChessCol.D), ChessPosition.get(ChessRow.ONE, ChessCol.A))

        Assertions.assertThat(result.canMoveWith(board)).isFalse()
    }
}
