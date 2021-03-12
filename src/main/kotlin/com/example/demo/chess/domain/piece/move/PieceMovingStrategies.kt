package com.example.demo.chess.domain.piece.move

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
