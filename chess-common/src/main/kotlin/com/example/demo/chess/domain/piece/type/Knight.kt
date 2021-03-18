package com.example.demo.chess.domain.piece.type

import com.example.demo.chess.domain.board.ChessPosition
import com.example.demo.chess.domain.board.findNotEmptyPath
import com.example.demo.chess.domain.piece.ChessPiece
import com.example.demo.chess.domain.piece.play.PieceDirection
import com.example.demo.chess.domain.piece.play.PieceMovingResult
import com.example.demo.chess.domain.piece.play.PieceMovingResult.Companion.NO_RESULT
import com.example.demo.chess.domain.piece.play.attackOrMoving

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
        return MOVE_DIRECTIONS.map { start.getPathTo(end, it, 1) }
                .findNotEmptyPath()
                ?.let { PieceMovingResult(attackOrMoving(), it) }
                ?: NO_RESULT
    }
}
