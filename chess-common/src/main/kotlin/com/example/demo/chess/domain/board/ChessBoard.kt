package com.example.demo.chess.domain.board

import com.example.demo.chess.domain.piece.ChessPieceInGame
import com.example.demo.chess.domain.piece.type.EMPTY_PIECE

class ChessBoard(
        val pieces: Map<ChessPosition, ChessPieceInGame>,
        val turn: Int = 1,
) {

    private val playerOfTurn: ChessPlayer = when (turn % 2) {
        1 -> ChessPlayer.PLAYER_1
        else -> ChessPlayer.PLAYER_2
    }

    fun move(source: ChessPosition, destination: ChessPosition): ChessBoard {
        if (playerOfTurn.isWhite()) {
            return reversed()
                    .moveIfPossible(source.reversed(), destination.reversed())
                    .reversed()
        }
        return moveIfPossible(source, destination)
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

    private fun moveIfPossible(source: ChessPosition, destination: ChessPosition): ChessBoard {
        if (canMove(source, destination)) {
            return ChessBoard(replace(source, destination), turn + 1)
        }
        return this
    }

    private fun canMove(source: ChessPosition, destination: ChessPosition): Boolean {
        val sourcePiece = getSourcePiece(source)
        val result = sourcePiece.move(source, destination)
        return result.canMoveWith(this)
    }

    private fun getSourcePiece(source: ChessPosition): ChessPieceInGame {
        return requireNotNull(pieces[source]) { "No piece exist in source position" }
                .also { require(it.isPlayerOf(playerOfTurn)) { "It is not turn of source piece." } }
    }

    private fun replace(removed: ChessPosition, added: ChessPosition): Map<ChessPosition, ChessPieceInGame> {
        return pieces.minus(removed)
                .minus(added)
                .plus(added to pieces.getValue(removed))
    }
}
