package com.example.demo.chess.domain.piece.type

import com.example.demo.chess.domain.board.ChessBoard
import com.example.demo.chess.domain.board.ChessCol
import com.example.demo.chess.domain.board.ChessPosition
import com.example.demo.chess.domain.board.ChessRow
import com.example.demo.chess.domain.board.path.ChessPath
import com.example.demo.chess.domain.board.path.getExistPath
import com.example.demo.chess.domain.piece.ChessPiece
import com.example.demo.chess.domain.piece.move.*

private val MOVE_DIRECTION = PieceDirection.N
private val ATTACK_DIRECTIONS = listOf(PieceDirection.NE, PieceDirection.NW)
private const val NORMAL_MOVE_COUNT = 1
private const val INITIAL_MOVE_COUNT = 2
private const val INITIAL_MOVE_ALLOW_PATH_SIZE = 3

class Pawn : ChessPiece {

    override fun move(start: ChessPosition, end: ChessPosition): PieceMovingResults {
        return listOfNotNull(
                getMoveResult(start, end),
                getDoubleForwardMoveResult(start, end),
                getAttackResult(start, end),
        )
                .let(::PieceMovingResults)
    }

    private fun getMoveResult(start: ChessPosition, end: ChessPosition): PieceMovingResult? {
        return start.getPathTo(end, MOVE_DIRECTION, NORMAL_MOVE_COUNT)
                ?.let { PieceMovingResult(it, blockJustMoving()) }
    }

    private fun getDoubleForwardMoveResult(start: ChessPosition, end: ChessPosition): PieceMovingResult? {
        return start.getPathTo(end, MOVE_DIRECTION, INITIAL_MOVE_COUNT)
                ?.let { PieceMovingResult(it, CombinationOf(DoubleForwardAtInitialPosition(), blockJustMoving())) }
    }

    private fun getAttackResult(start: ChessPosition, end: ChessPosition): PieceMovingResult? {
        return ATTACK_DIRECTIONS.map { start.getPathTo(end, it, NORMAL_MOVE_COUNT) }
                .getExistPath()
                ?.let { PieceMovingResult(it, blockAttackMoving()) }
    }
}

private class DoubleForwardAtInitialPosition : PieceMovingStrategy {
    private val initialPawnPositions: List<ChessPosition> = listOf(
            ChessPosition.get(ChessCol.B, ChessRow.ONE),
            ChessPosition.get(ChessCol.B, ChessRow.TWO),
            ChessPosition.get(ChessCol.B, ChessRow.THREE),
            ChessPosition.get(ChessCol.B, ChessRow.FOUR),
            ChessPosition.get(ChessCol.B, ChessRow.FIVE),
            ChessPosition.get(ChessCol.B, ChessRow.SIX),
            ChessPosition.get(ChessCol.B, ChessRow.SEVEN),
            ChessPosition.get(ChessCol.B, ChessRow.EIGHT),
    )

    override fun canMove(path: ChessPath, board: ChessBoard): Boolean {
        if (!path.isSizeOf(INITIAL_MOVE_ALLOW_PATH_SIZE)) {
            return false
        }
        if (path.source !in initialPawnPositions) {
            return false
        }
        if ((path.destination.row != ChessRow.FOUR) || (path.source.col != path.destination.col)) {
            return false
        }
        return true
    }

}
