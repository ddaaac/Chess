package com.example.demo.chess.domain.piece.move

import com.example.demo.chess.domain.piece.ChessPieceInGame

class BlockMoving : AbstractPieceMovingStrategy() {
    override fun canMove(pieces: List<ChessPieceInGame>): Boolean {
        return pieces.slice(1 until pieces.lastIndex)
                .all { it.isEmpty() }
    }
}
