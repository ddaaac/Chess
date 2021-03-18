package com.example.demo.chess.domain.board

import com.example.demo.chess.domain.piece.type.BISHOP_BLACK
import com.example.demo.chess.domain.piece.type.BISHOP_WHITE
import com.example.demo.chess.domain.piece.type.PAWN_WHITE
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

internal class ChessBoardTest {

    @Test
    fun `체스판에서 기물이 움직일 수 있는 경우 움직이고 턴이 하나 증가한다`() {
        val board = ChessBoard(
                pieces = mapOf(ChessPosition.get(ChessCol.D, ChessRow.FOUR) to BISHOP_WHITE),
                turn = 2
        )

        val result = board.move(ChessPosition.get(ChessCol.D, ChessRow.FOUR), ChessPosition.get(ChessCol.E, ChessRow.FIVE))

        assertAll(
                { assertThat(result.pieces[ChessPosition.get(ChessCol.D, ChessRow.FOUR)]).isNull() },
                { assertThat(result.pieces[ChessPosition.get(ChessCol.E, ChessRow.FIVE)]).isEqualTo(BISHOP_WHITE) },
                { assertThat(result.turn).isEqualTo(3) },
        )
    }

    @Test
    fun `체스판에서 기물이 적 기물을 잡을 경우 움직이고 턴이 하나 증가한다`() {
        val board = ChessBoard(
                pieces = mapOf(
                        ChessPosition.get(ChessCol.D, ChessRow.FOUR) to BISHOP_WHITE,
                        ChessPosition.get(ChessCol.E, ChessRow.FIVE) to BISHOP_BLACK
                ),
                turn = 2
        )

        val result = board.move(ChessPosition.get(ChessCol.D, ChessRow.FOUR), ChessPosition.get(ChessCol.E, ChessRow.FIVE))

        assertAll(
                { assertThat(result.pieces[ChessPosition.get(ChessCol.D, ChessRow.FOUR)]).isNull() },
                { assertThat(result.pieces[ChessPosition.get(ChessCol.E, ChessRow.FIVE)]).isEqualTo(BISHOP_WHITE) },
                { assertThat(result.turn).isEqualTo(3) },
        )
    }

    @Test
    fun `체스판에서 기물이 움직일 수 없는 경우 이전 체스판이 그대로 반환된다`() {
        val board = ChessBoard(
                pieces = mapOf(
                        ChessPosition.get(ChessCol.D, ChessRow.FOUR) to BISHOP_WHITE,
                        ChessPosition.get(ChessCol.E, ChessRow.FIVE) to PAWN_WHITE
                ),
                turn = 2
        )

        val result = board.move(ChessPosition.get(ChessCol.D, ChessRow.FOUR), ChessPosition.get(ChessCol.E, ChessRow.FIVE))

        assertAll(
                { assertThat(result.pieces[ChessPosition.get(ChessCol.D, ChessRow.FOUR)]).isEqualTo(BISHOP_WHITE) },
                { assertThat(result.pieces[ChessPosition.get(ChessCol.E, ChessRow.FIVE)]).isEqualTo(PAWN_WHITE) },
                { assertThat(result.turn).isEqualTo(2) },
        )
    }

    @Test
    fun `체스판에서 출발 포지션에 기물이 없을 경우 예외가 발생한다`() {
        val board = ChessBoard(
                pieces = mapOf(
                        ChessPosition.get(ChessCol.E, ChessRow.FIVE) to PAWN_WHITE
                ),
                turn = 2
        )

        assertThatIllegalArgumentException()
                .isThrownBy { board.move(ChessPosition.get(ChessCol.D, ChessRow.FOUR), ChessPosition.get(ChessCol.E, ChessRow.FIVE)) }
                .withMessage("No piece exist in source position")
    }
}
