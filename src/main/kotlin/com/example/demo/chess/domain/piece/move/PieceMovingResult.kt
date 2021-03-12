package com.example.demo.chess.domain.piece.move

import com.example.demo.chess.domain.board.ChessPath

data class PieceMovingResult(val path: ChessPath, val moving: PieceMovingStrategy)

data class PieceMovingResults(val results: List<PieceMovingResult>) {
    constructor(result: PieceMovingResult) : this(listOf(result))
}
