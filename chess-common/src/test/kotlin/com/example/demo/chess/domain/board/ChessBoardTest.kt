package com.example.demo.chess.domain.board

import com.example.demo.chess.domain.piece.type.BISHOP_BLACK
import com.example.demo.chess.domain.piece.type.BISHOP_WHITE
import com.example.demo.chess.domain.piece.type.PAWN_BLACK
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
                        ChessPosition.get(ChessCol.D, ChessRow.FOUR) to BISHOP_BLACK,
                        ChessPosition.get(ChessCol.E, ChessRow.FIVE) to PAWN_BLACK
                ),
                turn = 3
        )

        val result = board.move(ChessPosition.get(ChessCol.D, ChessRow.FOUR), ChessPosition.get(ChessCol.E, ChessRow.FIVE))

        assertAll(
                { assertThat(result.pieces[ChessPosition.get(ChessCol.D, ChessRow.FOUR)]).isEqualTo(BISHOP_BLACK) },
                { assertThat(result.pieces[ChessPosition.get(ChessCol.E, ChessRow.FIVE)]).isEqualTo(PAWN_BLACK) },
                { assertThat(result.turn).isEqualTo(3) },
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

    @Test
    fun `백색 폰은 본래 방향인 N과 다르게 아래로 이동할 수 있다`() {
        val board = ChessBoard(
                pieces = mapOf(
                        ChessPosition.get(ChessCol.B, ChessRow.SEVEN) to PAWN_WHITE
                ),
                turn = 2
        )

        val result = board.move(ChessPosition.get(ChessCol.B, ChessRow.SEVEN), ChessPosition.get(ChessCol.B, ChessRow.FIVE))

        assertThat(result.pieces[ChessPosition.get(ChessCol.B, ChessRow.FIVE)]).isEqualTo(PAWN_WHITE)
    }

    @Test
    fun `흑색의 차례에 백색 말을 움직일 수 없다`() {
        val board = ChessBoard(
                pieces = mapOf(
                        ChessPosition.get(ChessCol.B, ChessRow.SEVEN) to PAWN_WHITE
                ),
                turn = 1
        )

        assertThatIllegalArgumentException()
                .isThrownBy { board.move(ChessPosition.get(ChessCol.B, ChessRow.SEVEN), ChessPosition.get(ChessCol.B, ChessRow.SIX)) }
                .withMessage("It is not turn of source piece.")
    }

    @Test
    fun `백색의 차례에 흑색 말을 움직일 수 없다`() {
        val board = ChessBoard(
                pieces = mapOf(
                        ChessPosition.get(ChessCol.B, ChessRow.TWO) to PAWN_BLACK
                ),
                turn = 2
        )

        assertThatIllegalArgumentException()
                .isThrownBy { board.move(ChessPosition.get(ChessCol.B, ChessRow.TWO), ChessPosition.get(ChessCol.B, ChessRow.THREE)) }
                .withMessage("It is not turn of source piece.")
    }
}
