package com.example.demo.chess.domain.piece.type

import com.example.demo.chess.domain.board.*
import com.example.demo.chess.domain.piece.ChessPiece

import com.example.demo.chess.domain.piece.play.*
import com.example.demo.chess.domain.piece.play.PieceMovingResult.Companion.NO_RESULT

private val MOVE_DIRECTION = PieceDirection.N
private val ATTACK_DIRECTIONS = listOf(PieceDirection.NE, PieceDirection.NW)
private const val NORMAL_MOVE_COUNT = 1
private const val INITIAL_MOVE_COUNT = 2
private const val INITIAL_MOVE_ALLOW_PATH_SIZE = 3

private val INITIAL_PAWN_POSITIONS: List<ChessPosition> = listOf(
        ChessPosition.get(ChessCol.A, ChessRow.TWO),
        ChessPosition.get(ChessCol.B, ChessRow.TWO),
        ChessPosition.get(ChessCol.C, ChessRow.TWO),
        ChessPosition.get(ChessCol.D, ChessRow.TWO),
        ChessPosition.get(ChessCol.E, ChessRow.TWO),
        ChessPosition.get(ChessCol.F, ChessRow.TWO),
        ChessPosition.get(ChessCol.G, ChessRow.TWO),
        ChessPosition.get(ChessCol.H, ChessRow.TWO),
)

class Pawn : ChessPiece {

    override fun move(start: ChessPosition, end: ChessPosition): PieceMovingResult {
        return getMoveResult(start, end)
                ?: getAttackResult(start, end)
                ?: getDoubleForwardMoveResult(start, end)
                ?: NO_RESULT
    }

    private fun getMoveResult(start: ChessPosition, end: ChessPosition): PieceMovingResult? {
        return start.getPathTo(end, MOVE_DIRECTION, NORMAL_MOVE_COUNT)
                ?.let { PieceMovingResult(blockMoving(), it) }
    }

    private fun getDoubleForwardMoveResult(start: ChessPosition, end: ChessPosition): PieceMovingResult? {
        return start.getPathTo(end, MOVE_DIRECTION, INITIAL_MOVE_COUNT)
                ?.let { PieceMovingResult(ApplyAll(DoubleForwardAtInitialPosition(), blockMoving()), it) }
    }

    private fun getAttackResult(start: ChessPosition, end: ChessPosition): PieceMovingResult? {
        return ATTACK_DIRECTIONS.map { start.getPathTo(end, it, NORMAL_MOVE_COUNT) }
                .findNotEmptyPath()
                ?.let { PieceMovingResult(blockAttack(), it) }
    }
}

private class DoubleForwardAtInitialPosition : ChessPlayingStrategy {

    override fun canPlay(path: ChessPath, board: ChessBoard): Boolean {
        if (!path.isSizeOf(INITIAL_MOVE_ALLOW_PATH_SIZE)) {
            return false
        }
        if (path.source !in INITIAL_PAWN_POSITIONS) {
            return false
        }
        if ((path.destination.row != ChessRow.FOUR) || (path.source.col != path.destination.col)) {
            return false
        }
        return true
    }

}
