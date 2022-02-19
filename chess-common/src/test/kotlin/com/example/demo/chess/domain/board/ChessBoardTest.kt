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
                pieces = mapOf(ChessPosition.get(ChessRow.FOUR, ChessCol.D) to BISHOP_WHITE),
                turn = 2
        )

        val result = board.move(ChessPosition.get(ChessRow.FOUR, ChessCol.D), ChessPosition.get(ChessRow.FIVE, ChessCol.E))

        assertAll(
                { assertThat(result.pieces[ChessPosition.get(ChessRow.FOUR, ChessCol.D)]).isNull() },
                { assertThat(result.pieces[ChessPosition.get(ChessRow.FIVE, ChessCol.E)]).isEqualTo(BISHOP_WHITE) },
                { assertThat(result.turn).isEqualTo(3) },
        )
    }

    @Test
    fun `체스판에서 기물이 적 기물을 잡을 경우 움직이고 턴이 하나 증가한다`() {
        val board = ChessBoard(
                pieces = mapOf(
                        ChessPosition.get(ChessRow.FOUR, ChessCol.D) to BISHOP_WHITE,
                        ChessPosition.get(ChessRow.FIVE, ChessCol.E) to BISHOP_BLACK
                ),
                turn = 2
        )

        val result = board.move(ChessPosition.get(ChessRow.FOUR, ChessCol.D), ChessPosition.get(ChessRow.FIVE, ChessCol.E))

        assertAll(
                { assertThat(result.pieces[ChessPosition.get(ChessRow.FOUR, ChessCol.D)]).isNull() },
                { assertThat(result.pieces[ChessPosition.get(ChessRow.FIVE, ChessCol.E)]).isEqualTo(BISHOP_WHITE) },
                { assertThat(result.turn).isEqualTo(3) },
        )
    }

    @Test
    fun `체스판에서 기물이 움직일 수 없는 경우 이전 체스판이 그대로 반환된다`() {
        val board = ChessBoard(
                pieces = mapOf(
                        ChessPosition.get(ChessRow.FOUR, ChessCol.D) to BISHOP_BLACK,
                        ChessPosition.get(ChessRow.FIVE, ChessCol.E) to PAWN_BLACK
                ),
                turn = 3
        )

        val result = board.move(ChessPosition.get(ChessRow.FOUR, ChessCol.D), ChessPosition.get(ChessRow.FIVE, ChessCol.E))

        assertAll(
                { assertThat(result.pieces[ChessPosition.get(ChessRow.FOUR, ChessCol.D)]).isEqualTo(BISHOP_BLACK) },
                { assertThat(result.pieces[ChessPosition.get(ChessRow.FIVE, ChessCol.E)]).isEqualTo(PAWN_BLACK) },
                { assertThat(result.turn).isEqualTo(3) },
        )
    }

    @Test
    fun `체스판에서 출발 포지션에 기물이 없을 경우 예외가 발생한다`() {
        val board = ChessBoard(
                pieces = mapOf(
                        ChessPosition.get(ChessRow.FIVE, ChessCol.E) to PAWN_WHITE
                ),
                turn = 2
        )

        assertThatIllegalArgumentException()
                .isThrownBy { board.move(ChessPosition.get(ChessRow.FOUR, ChessCol.D), ChessPosition.get(ChessRow.FIVE, ChessCol.E)) }
                .withMessage("No piece exist in source position")
    }

    @Test
    fun `백색 폰은 본래 방향인 N과 다르게 아래로 이동할 수 있다`() {
        val board = ChessBoard(
                pieces = mapOf(
                        ChessPosition.get(ChessRow.SEVEN, ChessCol.B) to PAWN_WHITE
                ),
                turn = 2
        )

        val result = board.move(ChessPosition.get(ChessRow.SEVEN, ChessCol.B), ChessPosition.get(ChessRow.FIVE, ChessCol.B))

        assertThat(result.pieces[ChessPosition.get(ChessRow.FIVE, ChessCol.B)]).isEqualTo(PAWN_WHITE)
    }

    @Test
    fun `흑색의 차례에 백색 말을 움직일 수 없다`() {
        val board = ChessBoard(
                pieces = mapOf(
                        ChessPosition.get(ChessRow.SEVEN, ChessCol.B) to PAWN_WHITE
                ),
                turn = 1
        )

        assertThatIllegalArgumentException()
                .isThrownBy { board.move(ChessPosition.get(ChessRow.SEVEN, ChessCol.B), ChessPosition.get(ChessRow.SIX, ChessCol.B)) }
                .withMessage("It is not turn of source piece.")
    }

    @Test
    fun `백색의 차례에 흑색 말을 움직일 수 없다`() {
        val board = ChessBoard(
                pieces = mapOf(
                        ChessPosition.get(ChessRow.TWO, ChessCol.B) to PAWN_BLACK
                ),
                turn = 2
        )

        assertThatIllegalArgumentException()
                .isThrownBy { board.move(ChessPosition.get(ChessRow.TWO, ChessCol.B), ChessPosition.get(ChessRow.THREE, ChessCol.B)) }
                .withMessage("It is not turn of source piece.")
    }
}
