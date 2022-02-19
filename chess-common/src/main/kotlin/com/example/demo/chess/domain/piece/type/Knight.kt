package com.example.demo.chess.domain.piece.type

import com.example.demo.chess.domain.board.ChessPosition
import com.example.demo.chess.domain.piece.ChessPiece
import com.example.demo.chess.domain.piece.play.PieceDirection
import com.example.demo.chess.domain.piece.play.PieceMovingResult
import com.example.demo.chess.domain.piece.play.PieceMovingResult.Companion.NO_RESULT
import com.example.demo.chess.domain.piece.play.ATTACK_OR_MOVING

private val MOVE_DIRECTIONS = listOf(
        PieceDirection.NNW,
        PieceDirection.NNE,
        PieceDirection.NEE,
        PieceDirection.NWW,
        PieceDirection.SSW,
        PieceDirection.SSE,
        PieceDirection.SEE,
        PieceDirection.SWW,
)

class Knight : ChessPiece {

    override fun move(start: ChessPosition, end: ChessPosition): PieceMovingResult {
        val path = MOVE_DIRECTIONS
                .mapNotNull { start.findPathTo(end, it, 1) }
                .firstOrNull()
        return when (path) {
            null -> NO_RESULT
            else -> PieceMovingResult(ATTACK_OR_MOVING, path)
        }
    }
}
