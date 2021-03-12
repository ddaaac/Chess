package com.example.demo.chess.domain.piece.move

import com.example.demo.chess.domain.board.ChessBoard
import com.example.demo.chess.domain.board.path.ChessPath

class CombinationOf(private val strategies: List<PieceMovingStrategy>) : PieceMovingStrategy {

    constructor(vararg strategies: PieceMovingStrategy) : this(strategies.toList())

    override fun canMove(path: ChessPath, board: ChessBoard): Boolean {
        return strategies.all { it.canMove(path, board) }
    }
}
