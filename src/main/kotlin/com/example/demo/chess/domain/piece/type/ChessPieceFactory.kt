package com.example.demo.chess.domain.piece.type

import com.example.demo.chess.domain.board.ChessPlayer
import com.example.demo.chess.domain.piece.ChessPiece
import com.example.demo.chess.domain.piece.ChessPieceInGame

val PAWN: ChessPiece = Pawn()
val KING: ChessPiece = King()
val KNIGHT: ChessPiece = Knight()
val QUEEN: ChessPiece = Queen()
val ROOK: ChessPiece = Rook()
val BISHOP: ChessPiece = Bishop()

val EMPTY_PIECE: ChessPieceInGame = ChessPieceInGame(EmptyPiece(), ChessPlayer.NONE)
val PAWN_WHITE: ChessPieceInGame = ChessPieceInGame(PAWN, ChessPlayer.PLAYER_1)
val PAWN_BLACK: ChessPieceInGame = ChessPieceInGame(PAWN, ChessPlayer.PLAYER_2)
val KING_WHITE: ChessPieceInGame = ChessPieceInGame(KING, ChessPlayer.PLAYER_1)
val KING_BLACK: ChessPieceInGame = ChessPieceInGame(KING, ChessPlayer.PLAYER_2)
val KNIGHT_WHITE: ChessPieceInGame = ChessPieceInGame(KNIGHT, ChessPlayer.PLAYER_1)
val KNIGHT_BLACK: ChessPieceInGame = ChessPieceInGame(KNIGHT, ChessPlayer.PLAYER_2)
val QUEEN_WHITE: ChessPieceInGame = ChessPieceInGame(QUEEN, ChessPlayer.PLAYER_1)
val QUEEN_BLACK: ChessPieceInGame = ChessPieceInGame(QUEEN, ChessPlayer.PLAYER_2)
val ROOK_WHITE: ChessPieceInGame = ChessPieceInGame(ROOK, ChessPlayer.PLAYER_1)
val ROOK_BLACK: ChessPieceInGame = ChessPieceInGame(ROOK, ChessPlayer.PLAYER_2)
val BISHOP_WHITE: ChessPieceInGame = ChessPieceInGame(BISHOP, ChessPlayer.PLAYER_1)
val BISHOP_BLACK: ChessPieceInGame = ChessPieceInGame(BISHOP, ChessPlayer.PLAYER_2)
