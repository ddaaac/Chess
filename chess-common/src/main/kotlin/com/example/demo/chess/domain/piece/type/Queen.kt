package com.example.demo.chess.domain.piece.type

import com.example.demo.chess.domain.board.ChessPosition
import com.example.demo.chess.domain.board.findNotEmptyPath
import com.example.demo.chess.domain.piece.ChessPiece
import com.example.demo.chess.domain.piece.play.*

private val MOVE_DIRECTIONS = listOf(
        PieceDirection.N,
        PieceDirection.S,
        PieceDirection.W,
        PieceDirection.E,
        PieceDirection.NW,
        PieceDirection.NE,
        PieceDirection.SW,
        PieceDirection.SE,
)

class Queen : ChessPiece {

    override fun move(start: ChessPosition, end: ChessPosition): PieceMovingResults {

        return MOVE_DIRECTIONS.map { start.getPathTo(end, it) }
                .findNotEmptyPath()
                ?.let { PieceMovingResults(it, attackOrMovingWithBlock()) }
                ?: NO_RESULT
    }
}
