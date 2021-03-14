package com.example.demo.chess.domain.piece.type

import com.example.demo.chess.domain.board.ChessPlayer
import com.example.demo.chess.domain.piece.ChessPiece
import com.example.demo.chess.domain.piece.ChessPieceInGame

val EMPTY_PIECE: ChessPieceInGame = ChessPieceInGame(EmptyPiece(), ChessPlayer.NONE)
val PAWN: ChessPiece = Pawn()
val KING: ChessPiece = King()
val KNIGHT: ChessPiece = Knight()
val QUEEN: ChessPiece = Queen()
val ROOK: ChessPiece = Rook()
val BISHOP: ChessPiece = Bishop()
