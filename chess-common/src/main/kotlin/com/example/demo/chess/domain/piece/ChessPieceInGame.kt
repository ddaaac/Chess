package com.example.demo.chess.domain.piece

import com.example.demo.chess.domain.board.ChessPlayer
import com.example.demo.chess.domain.board.ChessPosition
import com.example.demo.chess.domain.piece.play.PieceMovingResults
import com.example.demo.chess.domain.piece.type.EMPTY_PIECE

class ChessPieceInGame(private val piece: ChessPiece, private val player: ChessPlayer) {

    fun move(start: ChessPosition, end: ChessPosition): PieceMovingResults = piece.move(start, end)

    fun isEmpty() = (piece == EMPTY_PIECE.piece)

    fun isOpponentOf(other: ChessPieceInGame) = (player.isOpponentOf(other.player))
}
