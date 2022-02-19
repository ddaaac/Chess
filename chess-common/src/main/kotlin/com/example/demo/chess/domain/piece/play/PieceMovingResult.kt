package com.example.demo.chess.domain.piece.play

import com.example.demo.chess.domain.board.ChessBoard
import com.example.demo.chess.domain.board.ChessPath

class PieceMovingResult private constructor(
        private val path: ChessPath?,
        private val playingStrategy: ChessPlayingStrategy,
) {

    constructor(playingStrategy: ChessPlayingStrategy, path: ChessPath) : this(path, playingStrategy)

    fun canMoveWith(board: ChessBoard): Boolean {
        if (path == null) {
            return false
        }
        return playingStrategy.canPlay(path, board)
    }

    companion object {
        val NO_RESULT = PieceMovingResult(null) { _, _ -> false }
    }
}
