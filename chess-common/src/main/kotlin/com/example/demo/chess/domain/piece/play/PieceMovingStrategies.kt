package com.example.demo.chess.domain.piece.play

import com.example.demo.chess.domain.board.ChessBoard
import com.example.demo.chess.domain.board.ChessPath
import com.example.demo.chess.domain.piece.ChessPieceInGame

class Block : DefaultPlayingStrategy() {

    override fun canPlay(pieces: List<ChessPieceInGame>): Boolean {
        return pieces.slice(1 until pieces.lastIndex)
                .all { it.isEmpty() }
    }
}

class Moving : DefaultPlayingStrategy() {

    override fun canPlay(pieces: List<ChessPieceInGame>): Boolean {
        return pieces.last().isEmpty()
    }
}

class Attack : DefaultPlayingStrategy() {

    override fun canPlay(pieces: List<ChessPieceInGame>): Boolean {
        val source = pieces.first()
        val destination = pieces.last()
        return source.isOpponentOf(destination)
    }
}

class ApplyAll(private val strategies: List<ChessPlayingStrategy>) : ChessPlayingStrategy {

    constructor(vararg strategies: ChessPlayingStrategy) : this(strategies.toList())

    override fun canPlay(path: ChessPath, board: ChessBoard): Boolean {
        return strategies.all { it.canPlay(path, board) }
    }
}

class ApplyAny(private val strategies: List<ChessPlayingStrategy>) : ChessPlayingStrategy {

    constructor(vararg strategies: ChessPlayingStrategy) : this(strategies.toList())

    override fun canPlay(path: ChessPath, board: ChessBoard): Boolean {
        return strategies.any { it.canPlay(path, board) }
    }
}
