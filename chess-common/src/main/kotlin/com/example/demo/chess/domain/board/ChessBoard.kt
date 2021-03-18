package com.example.demo.chess.domain.board

import com.example.demo.chess.domain.piece.ChessPieceInGame
import com.example.demo.chess.domain.piece.type.EMPTY_PIECE

class ChessBoard(
        val pieces: Map<ChessPosition, ChessPieceInGame>,
        val turn: Int = 0,
) {

    fun move(source: ChessPosition, destination: ChessPosition): ChessBoard {
        if (canMove(source, destination)) {
            return ChessBoard(replace(source, destination), turn + 1)
        }
        return this
    }

    fun getPieces(path: ChessPath): List<ChessPieceInGame> {
        return path.path.map { pieces.getOrDefault(it, EMPTY_PIECE) }
    }

    private fun canMove(source: ChessPosition, destination: ChessPosition): Boolean {
        return pieces[source]?.getMovingResult(source, destination)
                ?.canMoveWith(this)
                ?: throw IllegalArgumentException("No piece exist in source position")
    }

    private fun replace(source: ChessPosition, destination: ChessPosition): Map<ChessPosition, ChessPieceInGame> {
        return pieces.minus(source)
                .minus(destination)
                .plus(destination to pieces.getValue(source))
    }
}
