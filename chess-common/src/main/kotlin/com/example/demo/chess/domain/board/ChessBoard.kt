package com.example.demo.chess.domain.board

import com.example.demo.chess.domain.piece.ChessPieceInGame
import com.example.demo.chess.domain.piece.type.EMPTY_PIECE

class ChessBoard(
        val pieces: Map<ChessPosition, ChessPieceInGame>,
        val turn: Int = 1,
) {

    private val playerOfTurn: ChessPlayer = if (turn % 2 == 1) {
        ChessPlayer.PLAYER_1
    } else {
        ChessPlayer.PLAYER_2
    }

    fun move(source: ChessPosition, destination: ChessPosition): ChessBoard {
        if (playerOfTurn.isWhite()) {
            return reversed()
                    .getMovedBoard(source.reversed(), destination.reversed())
                    .reversed()
        }
        return getMovedBoard(source, destination)
    }

    fun getPieces(path: ChessPath): List<ChessPieceInGame> {
        return path.path.map { pieces.getOrDefault(it, EMPTY_PIECE) }
    }

    private fun reversed(): ChessBoard {
        return ChessBoard(
                pieces = pieces.map { it.key.reversed() to it.value }.toMap(),
                turn = turn
        )
    }

    private fun getMovedBoard(source: ChessPosition, destination: ChessPosition): ChessBoard {
        if (canMove(source, destination)) {
            return ChessBoard(replace(source, destination), turn + 1)
        }
        return this
    }

    private fun canMove(source: ChessPosition, destination: ChessPosition): Boolean {
        return getSourcePiece(source)
                .getMovingResult(source, destination)
                .canMoveWith(this)
    }

    private fun getSourcePiece(source: ChessPosition): ChessPieceInGame {
        return requireNotNull(pieces[source]) { "No piece exist in source position" }
                .apply { require(isPlayerOf(playerOfTurn)) { "It is not turn of source piece." } }
    }

    private fun replace(source: ChessPosition, destination: ChessPosition): Map<ChessPosition, ChessPieceInGame> {
        return pieces.minus(source)
                .minus(destination)
                .plus(destination to pieces.getValue(source))
    }
}
