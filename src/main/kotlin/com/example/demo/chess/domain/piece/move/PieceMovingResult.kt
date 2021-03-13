package com.example.demo.chess.domain.piece.move

import com.example.demo.chess.domain.board.ChessBoard
import com.example.demo.chess.domain.board.ChessPath

data class PieceMovingResult(private val path: ChessPath, private val movingStrategy: PieceMovingStrategy) {

    fun canMoveWith(board: ChessBoard) = movingStrategy.canMove(path, board)
}

data class PieceMovingResults(private val results: List<PieceMovingResult>) {

    constructor(result: PieceMovingResult) : this(listOf(result))

    fun canMoveWith(board: ChessBoard) = results.any { it.canMoveWith(board) }
}

val NO_RESULT = PieceMovingResults(emptyList())
