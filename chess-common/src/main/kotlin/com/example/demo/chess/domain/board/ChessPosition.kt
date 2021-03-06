package com.example.demo.chess.domain.board

import com.example.demo.chess.domain.piece.play.PieceDirection

data class ChessPosition private constructor(val row: ChessRow, val col: ChessCol) {

    fun getPathTo(destination: ChessPosition, direction: PieceDirection, limit: Int = 8): ChessPath? {
        val path = mutableListOf<ChessPosition>()

        var position: ChessPosition? = this
        while (position != NOT_EXIST_POSITION && position != destination) {
            path.add(position)
            position = position.getNextPosition(direction)
        }

        if ((position == NOT_EXIST_POSITION) || (path.size > limit)) {
            return emptyPath()
        }
        return ChessPath(path.apply { add(position) })
    }

    fun reversed(): ChessPosition = get(col.reversed(), row.reversed())

    private fun getNextPosition(direction: PieceDirection): ChessPosition? {
        return try {
            val (nextCol, nextRow) = direction.nextOf(col, row)
            ChessPosition(nextRow, nextCol)
        } catch (e: IllegalArgumentException) {
            NOT_EXIST_POSITION
        }
    }

    companion object {
        private val POSITION_CACHE: Map<Pair<ChessRow, ChessCol>, ChessPosition> = createCache()
        private val NOT_EXIST_POSITION = null

        private fun createCache(): Map<Pair<ChessRow, ChessCol>, ChessPosition> {
            val cache: MutableMap<Pair<ChessRow, ChessCol>, ChessPosition> = mutableMapOf()

            for (row in ChessRow.values()) {
                for (col in ChessCol.values()) {
                    cache[row to col] = ChessPosition(row, col)
                }
            }

            return cache
        }

        fun get(col: ChessCol, row: ChessRow): ChessPosition {
            return POSITION_CACHE[row to col]
                    ?: throw IllegalArgumentException("Range of position is between A1 to H8")
        }
    }
}
