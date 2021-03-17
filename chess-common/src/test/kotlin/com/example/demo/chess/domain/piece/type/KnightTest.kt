package com.example.demo.chess.domain.piece.type

import com.example.demo.chess.domain.board.*
import com.example.demo.chess.domain.piece.MOCK_PIECE_PLAYER_1
import com.example.demo.chess.domain.piece.MOCK_PIECE_PLAYER_2
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class KnightTest {

    /**
     *      8
     *      7
     *      6        x     x
     *      5     x        o  x
     *      4        o  q
     *      3     x     o     x
     *      2        x     x
     *      1
     *         A  B  C  D  E  F  G  H
     *
     */
    @ParameterizedTest
    @CsvSource(value = ["B,THREE", "B,FIVE", "C,TWO", "C,SIX", "E,TWO", "E,SIX", "F,THREE", "F,FIVE"])
    fun `Knight는 다른 기물과는 상관없이 destination이 비어있는 Knight 방향으로 이동할 수 있다`(col: ChessCol, row: ChessRow) {
        val board = ChessBoard(mapOf(
                ChessPosition.get(ChessCol.D, ChessRow.FOUR) to KNIGHT_WHITE,
                ChessPosition.get(ChessCol.C, ChessRow.FOUR) to MOCK_PIECE_PLAYER_2,
                ChessPosition.get(ChessCol.D, ChessRow.THREE) to MOCK_PIECE_PLAYER_1,
                ChessPosition.get(ChessCol.E, ChessRow.FIVE) to MOCK_PIECE_PLAYER_2,
        ))

        val result = KNIGHT.move(ChessPosition.get(ChessCol.D, ChessRow.FOUR), ChessPosition.get(col, row))

        Assertions.assertThat(result.canMoveWith(board)).isTrue()
    }

    @ParameterizedTest
    @CsvSource(value = ["B,THREE", "B,FIVE", "C,TWO", "C,SIX", "E,TWO", "E,SIX", "F,THREE", "F,FIVE"])
    fun `Knight는 다른 기물과는 상관없이 destination에 적 기물이 있는 Knight 방향으로 이동할 수 있다`(col: ChessCol, row: ChessRow) {
        val board = ChessBoard(mapOf(
                ChessPosition.get(ChessCol.D, ChessRow.FOUR) to KING_WHITE,
                ChessPosition.get(ChessCol.C, ChessRow.FOUR) to MOCK_PIECE_PLAYER_2,
                ChessPosition.get(ChessCol.D, ChessRow.THREE) to MOCK_PIECE_PLAYER_1,
                ChessPosition.get(ChessCol.E, ChessRow.FIVE) to MOCK_PIECE_PLAYER_2,
        ))


        val result = KNIGHT.move(ChessPosition.get(ChessCol.D, ChessRow.FOUR), ChessPosition.get(col, row))

        Assertions.assertThat(result.canMoveWith(board)).isTrue()
    }

    @Test
    fun `Knight은 destination이 아군 기물일 경우 이동할 수 없다`() {
        val board = ChessBoard(mapOf(
                ChessPosition.get(ChessCol.D, ChessRow.FOUR) to KING_WHITE,
                ChessPosition.get(ChessCol.B, ChessRow.THREE) to MOCK_PIECE_PLAYER_1,
        ))

        val result = KNIGHT.move(ChessPosition.get(ChessCol.D, ChessRow.FOUR), ChessPosition.get(ChessCol.B, ChessRow.THREE))

        Assertions.assertThat(result.canMoveWith(board)).isFalse()
    }
}
