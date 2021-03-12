package com.example.demo.chess.domain.board

import com.example.demo.chess.domain.piece.ChessPieceInGame
import com.example.demo.chess.domain.piece.EMPTY_PIECE

class ChessBoard(val pieces: Map<ChessPosition, ChessPieceInGame>) {
    fun getPieces(path: ChessPath): List<ChessPieceInGame> {
        return path.getPieces { pieces.getOrDefault(it, EMPTY_PIECE) }
    }
}
