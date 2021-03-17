package com.example.demo.chess.domain.piece.type

import com.example.demo.chess.domain.board.ChessPosition
import com.example.demo.chess.domain.piece.ChessPiece
import com.example.demo.chess.domain.piece.play.PieceMovingResults

class EmptyPiece : ChessPiece {
    override fun move(start: ChessPosition, end: ChessPosition): PieceMovingResults {
        throw UnsupportedOperationException()
    }
}
