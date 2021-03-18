package com.example.demo.chess.domain.piece

import com.example.demo.chess.domain.board.ChessPosition
import com.example.demo.chess.domain.piece.play.PieceMovingResult

interface ChessPiece {

    fun move(start: ChessPosition, end: ChessPosition): PieceMovingResult
}

