package com.example.demo.chess.domain.piece

import com.example.demo.chess.domain.board.ChessPlayer
import com.example.demo.chess.domain.board.ChessPosition
import com.example.demo.chess.domain.piece.play.PieceMovingResults
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

val MOCK_PIECE_PLAYER_1 = ChessPieceInGame(MockPiece(), ChessPlayer.PLAYER_1)
val MOCK_PIECE_PLAYER_2 = ChessPieceInGame(MockPiece(), ChessPlayer.PLAYER_2)
