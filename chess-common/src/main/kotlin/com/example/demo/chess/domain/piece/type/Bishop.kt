package com.example.demo.chess.domain.piece.type

import com.example.demo.chess.domain.board.ChessPosition
import com.example.demo.chess.domain.board.findNotEmptyPath
import com.example.demo.chess.domain.piece.ChessPiece
import com.example.demo.chess.domain.piece.play.PieceDirection
import com.example.demo.chess.domain.piece.play.PieceMovingResult
import com.example.demo.chess.domain.piece.play.PieceMovingResult.Companion.NO_RESULT
import com.example.demo.chess.domain.piece.play.attackOrMovingWithBlock

private val MOVE_DIRECTIONS = listOf(
        PieceDirection.NW,
        PieceDirection.NE,
        PieceDirection.SW,
        PieceDirection.SE,
)

class Bishop : ChessPiece {

    override fun move(start: ChessPosition, end: ChessPosition): PieceMovingResult {
        return MOVE_DIRECTIONS.map { start.getPathTo(end, it) }
                .findNotEmptyPath()
                ?.let { PieceMovingResult(attackOrMovingWithBlock(), it) }
                ?: NO_RESULT
    }
}
