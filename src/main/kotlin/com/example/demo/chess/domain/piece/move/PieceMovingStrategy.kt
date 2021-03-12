package com.example.demo.chess.domain.piece.move

import com.example.demo.chess.domain.board.ChessPath
import com.example.demo.chess.domain.board.ChessPosition
import com.example.demo.chess.domain.piece.ChessPieceInGame
import com.example.demo.chess.domain.piece.type.EMPTY_PIECE

interface PieceMovingStrategy {
    fun canMove(path: ChessPath, board: Map<ChessPosition, ChessPieceInGame>): Boolean
}

abstract class AbstractPieceMovingStrategy : PieceMovingStrategy {
    override fun canMove(path: ChessPath, board: Map<ChessPosition, ChessPieceInGame>): Boolean {
        val pieces: List<ChessPieceInGame> = board.getPieces(path)
        if (pieces.first().isEmpty()) {
            return false
        }
        return canMove(pieces)
    }

    protected abstract fun canMove(pieces: List<ChessPieceInGame>): Boolean
}

private fun Map<ChessPosition, ChessPieceInGame>.getPieces(path: ChessPath): List<ChessPieceInGame> {
    return path.getPieces { getOrDefault(it, EMPTY_PIECE) }
}

class CombinationOf(private val strategies: List<PieceMovingStrategy>) : PieceMovingStrategy {

    constructor(vararg strategies: PieceMovingStrategy) : this(strategies.toList())

    override fun canMove(path: ChessPath, board: Map<ChessPosition, ChessPieceInGame>): Boolean {
        return strategies.all { it.canMove(path, board) }
    }
}
