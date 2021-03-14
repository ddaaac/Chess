package com.example.demo.chess.domain.piece.play

import com.example.demo.chess.domain.board.ChessCol
import com.example.demo.chess.domain.board.ChessRow

enum class PieceDirection(private val nextRow: Int, private val nextCol: Int) {
    N(1, 0),
    S(-1, 0),
    W(0, -1),
    E(0, 1),
    NW(1, -1),
    NE(1, 1),
    SW(-1, -1),
    SE(-1, 1),
    NNW(2, -1),
    NWW(1, -2),
    NNE(2, 1),
    NEE(1, 2),
    SSW(-2, -1),
    SWW(-1, -2),
    SSE(-2, 1),
    SEE(-1, 2);

    fun nextOf(col: ChessCol, row: ChessRow): Pair<ChessCol, ChessRow> {
        return col.next(nextCol) to row.next(nextRow)
    }
}
