package com.example.demo.chess.domain.piece

import com.example.demo.chess.domain.board.ChessPosition
import com.example.demo.chess.domain.piece.move.PieceMovingResults
import com.example.demo.chess.domain.piece.type.EMPTY_PIECE
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ChessPieceInGameTest {
    @Test
    fun `EMPTY_PIECE의 isEmpty()가 참이다`() {
        assertThat(EMPTY_PIECE.isEmpty()).isTrue()
    }
}

class MockPiece : ChessPiece {
    override fun move(start: ChessPosition, end: ChessPosition): PieceMovingResults {
        throw UnsupportedOperationException()
    }
}
