package com.example.demo.chess.domain.piece.play

import com.example.demo.chess.domain.board.ChessBoard
import com.example.demo.chess.domain.board.ChessPath

data class PieceMovingResult(private val path: ChessPath, private val movingStrategy: ChessPlayingStrategy) {

    fun canMoveWith(board: ChessBoard) = movingStrategy.canPlay(path, board)
}

data class PieceMovingResults(private val results: List<PieceMovingResult>) {

    constructor(path: ChessPath, movingStrategy: ChessPlayingStrategy) : this(listOf(PieceMovingResult(path, movingStrategy)))

    fun canMoveWith(board: ChessBoard) = results.any { it.canMoveWith(board) }
}

val NO_RESULT = PieceMovingResults(emptyList())
