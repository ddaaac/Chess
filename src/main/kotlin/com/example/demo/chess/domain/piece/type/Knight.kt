package com.example.demo.chess.domain.piece.type

import com.example.demo.chess.domain.board.ChessPosition
import com.example.demo.chess.domain.board.getExistPath
import com.example.demo.chess.domain.piece.ChessPiece
import com.example.demo.chess.domain.piece.move.*

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

    override fun move(start: ChessPosition, end: ChessPosition): PieceMovingResults {
        return MOVE_DIRECTIONS.map { start.getPathTo(end, it, 1) }
                .getExistPath()
                ?.let { PieceMovingResults(PieceMovingResult(it, justOrAttackMoving())) }
                ?: NO_RESULT
    }
}
