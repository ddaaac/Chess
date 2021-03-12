package com.example.demo.chess.domain.board

import com.example.demo.chess.domain.piece.ChessPieceInGame

class ChessPath(private val path: List<ChessPosition>) {
    fun getPieces(pieceFinder: (ChessPosition) -> ChessPieceInGame) = path.map(pieceFinder)
}

val EMPTY_PATH = ChessPath(emptyList())

fun List<ChessPath>.getPath(): ChessPath {
    return first { isNotEmpty() }
}
