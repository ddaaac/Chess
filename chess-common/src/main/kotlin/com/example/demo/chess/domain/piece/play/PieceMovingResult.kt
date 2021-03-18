package com.example.demo.chess.domain.piece.play

import com.example.demo.chess.domain.board.ChessBoard
import com.example.demo.chess.domain.board.ChessPath
import com.example.demo.chess.domain.board.emptyPath

data class PieceMovingResult private constructor(
        private val path: ChessPath?,
        private val movingStrategy: ChessPlayingStrategy
) {

    constructor(movingStrategy: ChessPlayingStrategy, path: ChessPath) : this(path, movingStrategy)

    fun canMoveWith(board: ChessBoard): Boolean {
        if (path == null) {
            return false
        }
        return movingStrategy.canPlay(path, board)
    }

    companion object {
        val NO_RESULT = PieceMovingResult(emptyPath()) { _, _ -> false }
    }
}
