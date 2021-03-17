package com.example.demo.chess.domain.piece.type

import com.example.demo.chess.domain.board.*
import com.example.demo.chess.domain.piece.ChessPieceInGame
import com.example.demo.chess.domain.piece.MOCK_PIECE_PLAYER_1
import com.example.demo.chess.domain.piece.MOCK_PIECE_PLAYER_2
import com.example.demo.chess.domain.piece.MockPiece
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource

internal class PawnTest {

    @Test
    fun `Pawn은 비어있는 바로 위 칸으로 이동할 수 있다`() {
        val board = ChessBoard(mapOf(
                ChessPosition.get(ChessCol.A, ChessRow.TWO) to PAWN_WHITE
        ))

        val result = PAWN.move(ChessPosition.get(ChessCol.A, ChessRow.TWO), ChessPosition.get(ChessCol.A, ChessRow.THREE))

        assertThat(result.canMoveWith(board)).isTrue()
    }

    @ParameterizedTest
    @ValueSource(strings = ["PLAYER_1", "PLAYER_2"])
    fun `Pawn은 위 칸에 기물이 존재하면 움직일 수 없다`(player: ChessPlayer) {
        val board = ChessBoard(mapOf(
                ChessPosition.get(ChessCol.A, ChessRow.TWO) to PAWN_WHITE,
                ChessPosition.get(ChessCol.A, ChessRow.THREE) to ChessPieceInGame(MockPiece(), player),
        ))

        val result = PAWN.move(ChessPosition.get(ChessCol.A, ChessRow.TWO), ChessPosition.get(ChessCol.A, ChessRow.THREE))

        assertThat(result.canMoveWith(board)).isFalse()
    }

    @ParameterizedTest
    @ValueSource(strings = ["A", "C"])
    fun `Pawn은 대각선 위에 상대방 기물이 존재하면 움직일 수 있다`(col: ChessCol) {

        val board = ChessBoard(mapOf(
                ChessPosition.get(ChessCol.B, ChessRow.TWO) to PAWN_WHITE,
                ChessPosition.get(col, ChessRow.THREE) to MOCK_PIECE_PLAYER_2,
        ))

        val result = PAWN.move(ChessPosition.get(ChessCol.B, ChessRow.TWO), ChessPosition.get(col, ChessRow.THREE))

        assertThat(result.canMoveWith(board)).isTrue()
    }

    @ParameterizedTest
    @ValueSource(strings = ["A", "C"])
    fun `Pawn은 대각선 위에 아군 기물이 존재하면 움직일 수 없다`(col: ChessCol) {

        val board = ChessBoard(mapOf(
                ChessPosition.get(ChessCol.B, ChessRow.TWO) to PAWN_WHITE,
                ChessPosition.get(col, ChessRow.THREE) to MOCK_PIECE_PLAYER_1,
        ))

        val result = PAWN.move(ChessPosition.get(ChessCol.B, ChessRow.TWO), ChessPosition.get(col, ChessRow.THREE))

        assertThat(result.canMoveWith(board)).isFalse()
    }

    @Test
    fun `Pawn은 초기 위치에 있을 경우 위로 두 칸을 움직일 수 있다`() {

        val board = ChessBoard(mapOf(
                ChessPosition.get(ChessCol.B, ChessRow.TWO) to PAWN_WHITE,
        ))

        val result = PAWN.move(ChessPosition.get(ChessCol.B, ChessRow.TWO), ChessPosition.get(ChessCol.B, ChessRow.FOUR))

        assertThat(result.canMoveWith(board)).isTrue()
    }

    @Test
    fun `Pawn은 초기 위치가 아닐 경우 위로 두 칸을 움직일 수 있다`() {
        val board = ChessBoard(mapOf(
                ChessPosition.get(ChessCol.B, ChessRow.THREE) to PAWN_WHITE,
        ))

        val result = PAWN.move(ChessPosition.get(ChessCol.B, ChessRow.THREE), ChessPosition.get(ChessCol.B, ChessRow.FIVE))

        assertThat(result.canMoveWith(board)).isFalse()
    }

    @ParameterizedTest
    @CsvSource(value = ["THREE,PLAYER_1", "THREE,PLAYER_2", "FOUR,PLAYER_1", "FOUR,PLAYER_2"])
    fun `Pawn은 초기 위치에 있지만 중간, 목적지에 기물이 있는 경우 위로 두 칸을 움직일 수 없다`(row: ChessRow, player: ChessPlayer) {
        val board = ChessBoard(mapOf(
                ChessPosition.get(ChessCol.B, ChessRow.TWO) to PAWN_WHITE,
                ChessPosition.get(ChessCol.B, row) to ChessPieceInGame(MockPiece(), player),
        ))

        val result = PAWN.move(ChessPosition.get(ChessCol.B, ChessRow.TWO), ChessPosition.get(ChessCol.B, ChessRow.FOUR))

        assertThat(result.canMoveWith(board)).isFalse()
    }
}
