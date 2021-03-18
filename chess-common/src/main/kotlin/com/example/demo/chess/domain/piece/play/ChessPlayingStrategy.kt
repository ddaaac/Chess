package com.example.demo.chess.domain.piece.play

import com.example.demo.chess.domain.board.ChessBoard
import com.example.demo.chess.domain.board.ChessPath
import com.example.demo.chess.domain.piece.ChessPieceInGame

fun interface ChessPlayingStrategy {

    fun canPlay(path: ChessPath, board: ChessBoard): Boolean
}

abstract class DefaultPlayingStrategy : ChessPlayingStrategy {

    override fun canPlay(path: ChessPath, board: ChessBoard): Boolean {
        val pieces: List<ChessPieceInGame> = board.getPieces(path)
        if (pieces.first().isEmpty()) {
            return false
        }
        return canPlay(pieces)
    }

    protected abstract fun canPlay(pieces: List<ChessPieceInGame>): Boolean
}
