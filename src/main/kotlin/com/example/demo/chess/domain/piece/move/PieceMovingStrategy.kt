package com.example.demo.chess.domain.piece.move

import com.example.demo.chess.domain.board.ChessBoard
import com.example.demo.chess.domain.board.path.ChessPath
import com.example.demo.chess.domain.piece.ChessPieceInGame

interface PieceMovingStrategy {
    fun canMove(path: ChessPath, board: ChessBoard): Boolean
}

abstract class AbstractPieceMovingStrategy : PieceMovingStrategy {
    override fun canMove(path: ChessPath, board: ChessBoard): Boolean {
        val pieces: List<ChessPieceInGame> = board.getPieces(path)
        if (pieces.first().isEmpty()) {
            return false
        }
        return canMove(pieces)
    }

    protected abstract fun canMove(pieces: List<ChessPieceInGame>): Boolean
}
