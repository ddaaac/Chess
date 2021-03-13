package com.example.demo.chess.domain.piece.move

import com.example.demo.chess.domain.board.ChessBoard
import com.example.demo.chess.domain.board.path.ChessPath

data class PieceMovingResult(val path: ChessPath, val moving: PieceMovingStrategy)

data class PieceMovingResults(val results: List<PieceMovingResult>) {
    fun canMoveWith(board: ChessBoard): Boolean {
        return results.any {
            it.moving.canMove(it.path, board)
        }
    }
}
