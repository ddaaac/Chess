package com.example.demo.chess.domain.piece.move

import com.example.demo.chess.domain.board.ChessBoard
import com.example.demo.chess.domain.board.ChessPath
import com.example.demo.chess.domain.piece.ChessPieceInGame

class BlockMoving : AbstractPieceMovingStrategy() {
    override fun canMove(pieces: List<ChessPieceInGame>): Boolean {
        return pieces.slice(1 until pieces.lastIndex)
                .all { it.isEmpty() }
    }
}

class JustMoving : AbstractPieceMovingStrategy() {
    override fun canMove(pieces: List<ChessPieceInGame>): Boolean {
        return pieces.last().isEmpty()
    }
}

class AttackMoving : AbstractPieceMovingStrategy() {
    override fun canMove(pieces: List<ChessPieceInGame>): Boolean {
        val (source, destination) = pieces.run { first() to last() }
        return source.isOpponentOf(destination)
    }
}

class ApplyAll(private val strategies: List<PieceMovingStrategy>) : PieceMovingStrategy {

    constructor(vararg strategies: PieceMovingStrategy) : this(strategies.toList())

    override fun canMove(path: ChessPath, board: ChessBoard): Boolean {
        return strategies.all { it.canMove(path, board) }
    }
}

class ApplyAny(private val strategies: List<PieceMovingStrategy>) : PieceMovingStrategy {

    constructor(vararg strategies: PieceMovingStrategy) : this(strategies.toList())

    override fun canMove(path: ChessPath, board: ChessBoard): Boolean {
        return strategies.any { it.canMove(path, board) }
    }
}
