package com.example.demo.chess.domain.piece

import com.example.demo.chess.domain.board.ChessBoard
import com.example.demo.chess.domain.board.ChessPath

interface PieceMovingStrategy {
    fun canMove(path: ChessPath, board: ChessBoard): Boolean
}

