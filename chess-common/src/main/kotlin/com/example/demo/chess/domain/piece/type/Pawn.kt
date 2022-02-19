package com.example.demo.chess.domain.piece.type

import com.example.demo.chess.domain.board.*
import com.example.demo.chess.domain.piece.ChessPiece

import com.example.demo.chess.domain.piece.play.*
import com.example.demo.chess.domain.piece.play.PieceMovingResult.Companion.NO_RESULT

private const val NORMAL_MOVE_COUNT = 1
private const val INITIAL_MOVE_COUNT = 2

private val MOVE_DIRECTION = PieceDirection.N
private val ATTACK_DIRECTIONS = listOf(PieceDirection.NE, PieceDirection.NW)
private val INITIAL_PAWN_POSITIONS: List<ChessPosition> = listOf(
        ChessPosition.get(ChessRow.TWO, ChessCol.A),
        ChessPosition.get(ChessRow.TWO, ChessCol.B),
        ChessPosition.get(ChessRow.TWO, ChessCol.C),
        ChessPosition.get(ChessRow.TWO, ChessCol.D),
        ChessPosition.get(ChessRow.TWO, ChessCol.E),
        ChessPosition.get(ChessRow.TWO, ChessCol.F),
        ChessPosition.get(ChessRow.TWO, ChessCol.G),
        ChessPosition.get(ChessRow.TWO, ChessCol.H),
)

class Pawn : ChessPiece {

    override fun move(start: ChessPosition, end: ChessPosition): PieceMovingResult {
        return getMoveResult(start, end)
                ?: getAttackResult(start, end)
                ?: getDoubleForwardMoveResult(start, end)
                ?: NO_RESULT
    }

    private fun getMoveResult(start: ChessPosition, end: ChessPosition): PieceMovingResult? {
        return start.findPathTo(end, MOVE_DIRECTION, NORMAL_MOVE_COUNT)
                ?.let { PieceMovingResult(BLOCK_MOVING, it) }
    }

    private fun getDoubleForwardMoveResult(start: ChessPosition, end: ChessPosition): PieceMovingResult? {
        return start.findPathTo(end, MOVE_DIRECTION, INITIAL_MOVE_COUNT)
                ?.let { PieceMovingResult(ApplyAll(DoubleForwardAtInitialPosition(), BLOCK_MOVING), it) }
    }

    private fun getAttackResult(start: ChessPosition, end: ChessPosition): PieceMovingResult? {
        return ATTACK_DIRECTIONS
                .mapNotNull { start.findPathTo(end, it, NORMAL_MOVE_COUNT) }
                .firstOrNull()
                ?.let { PieceMovingResult(BLOCK_ATTACK, it) }
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

    companion object {

        private const val INITIAL_MOVE_ALLOW_PATH_SIZE = 3
    }
}
