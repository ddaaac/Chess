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
                ChessPosition.get(ChessRow.TWO, ChessCol.A) to PAWN_WHITE
        ))

        val result = PAWN.move(ChessPosition.get(ChessRow.TWO, ChessCol.A), ChessPosition.get(ChessRow.THREE, ChessCol.A))

        assertThat(result.canMoveWith(board)).isTrue()
    }

    @ParameterizedTest
    @ValueSource(strings = ["PLAYER_1", "PLAYER_2"])
    fun `Pawn은 위 칸에 기물이 존재하면 움직일 수 없다`(player: ChessPlayer) {
        val board = ChessBoard(mapOf(
                ChessPosition.get(ChessRow.TWO, ChessCol.A) to PAWN_WHITE,
                ChessPosition.get(ChessRow.THREE, ChessCol.A) to ChessPieceInGame(MockPiece(), player),
        ))

        val result = PAWN.move(ChessPosition.get(ChessRow.TWO, ChessCol.A), ChessPosition.get(ChessRow.THREE, ChessCol.A))

        assertThat(result.canMoveWith(board)).isFalse()
    }

    @ParameterizedTest
    @ValueSource(strings = ["A", "C"])
    fun `Pawn은 대각선 위에 상대방 기물이 존재하면 움직일 수 있다`(col: ChessCol) {

        val board = ChessBoard(mapOf(
                ChessPosition.get(ChessRow.TWO, ChessCol.B) to PAWN_WHITE,
                ChessPosition.get(ChessRow.THREE, col) to MOCK_PIECE_PLAYER_2,
        ))

        val result = PAWN.move(ChessPosition.get(ChessRow.TWO, ChessCol.B), ChessPosition.get(ChessRow.THREE, col))

        assertThat(result.canMoveWith(board)).isTrue()
    }

    @ParameterizedTest
    @ValueSource(strings = ["A", "C"])
    fun `Pawn은 대각선 위에 아군 기물이 존재하면 움직일 수 없다`(col: ChessCol) {

        val board = ChessBoard(mapOf(
                ChessPosition.get(ChessRow.TWO, ChessCol.B) to PAWN_WHITE,
                ChessPosition.get(ChessRow.THREE, col) to MOCK_PIECE_PLAYER_1,
        ))

        val result = PAWN.move(ChessPosition.get(ChessRow.TWO, ChessCol.B), ChessPosition.get(ChessRow.THREE, col))

        assertThat(result.canMoveWith(board)).isFalse()
    }

    @Test
    fun `Pawn은 초기 위치에 있을 경우 위로 두 칸을 움직일 수 있다`() {

        val board = ChessBoard(mapOf(
                ChessPosition.get(ChessRow.TWO, ChessCol.A) to PAWN_WHITE,
        ))

        val result = PAWN.move(ChessPosition.get(ChessRow.TWO, ChessCol.A), ChessPosition.get(ChessRow.FOUR, ChessCol.A))

        assertThat(result.canMoveWith(board)).isTrue()
    }

    @Test
    fun `Pawn은 초기 위치가 아닐 경우 위로 두 칸을 움직일 수 없다`() {
        val board = ChessBoard(mapOf(
                ChessPosition.get(ChessRow.THREE, ChessCol.B) to PAWN_WHITE,
        ))

        val result = PAWN.move(ChessPosition.get(ChessRow.THREE, ChessCol.B), ChessPosition.get(ChessRow.FIVE, ChessCol.B))

        assertThat(result.canMoveWith(board)).isFalse()
    }

    @ParameterizedTest
    @CsvSource(value = ["THREE,PLAYER_1", "THREE,PLAYER_2", "FOUR,PLAYER_1", "FOUR,PLAYER_2"])
    fun `Pawn은 초기 위치에 있지만 중간, 목적지에 기물이 있는 경우 위로 두 칸을 움직일 수 없다`(row: ChessRow, player: ChessPlayer) {
        val board = ChessBoard(
                mapOf(
                        ChessPosition.get(ChessRow.TWO, ChessCol.B) to PAWN_WHITE,
                        ChessPosition.get(row, ChessCol.B) to ChessPieceInGame(MockPiece(), player),
                ))

        val result = PAWN.move(ChessPosition.get(ChessRow.TWO, ChessCol.B), ChessPosition.get(ChessRow.FOUR, ChessCol.B))

        assertThat(result.canMoveWith(board)).isFalse()
    }
}
