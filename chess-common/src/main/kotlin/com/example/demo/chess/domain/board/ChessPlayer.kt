package com.example.demo.chess.domain.board

enum class ChessPlayer {
    PLAYER_1, PLAYER_2, NONE;

    fun isOpponentOf(player: ChessPlayer): Boolean {
        return when (this) {
            PLAYER_1 -> (player == PLAYER_2)
            PLAYER_2 -> (player == PLAYER_1)
            else -> false
        }
    }

    fun isBlack() = (this == PLAYER_1)

    fun isWhite() = (this == PLAYER_2)
}
