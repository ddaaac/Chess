package com.example.demo.chess.domain.piece

import com.example.demo.chess.domain.board.ChessPosition

interface ChessPiece {
    fun move(start: ChessPosition, end: ChessPosition): PieceMovingResults
}

