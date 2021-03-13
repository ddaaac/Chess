package com.example.demo.chess.domain.piece.move

import com.example.demo.chess.domain.board.ChessBoard
import com.example.demo.chess.domain.board.ChessPath

data class PieceMovingResult(val path: ChessPath, val moving: PieceMovingStrategy)

data class PieceMovingResults(val results: List<PieceMovingResult>) {

    constructor(result: PieceMovingResult) : this(listOf(result))

    fun canMoveWith(board: ChessBoard): Boolean {
        return results.any {
            it.moving.canMove(it.path, board)
        }
    }
}

val NO_RESULT = PieceMovingResults(emptyList())
