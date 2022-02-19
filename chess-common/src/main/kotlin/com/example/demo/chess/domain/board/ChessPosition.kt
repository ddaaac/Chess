package com.example.demo.chess.domain.board

import com.example.demo.chess.domain.piece.play.PieceDirection

class ChessPosition private constructor(val row: ChessRow, val col: ChessCol) {

    fun findPathTo(destination: ChessPosition, direction: PieceDirection, limit: Int = NO_LIMIT): ChessPath? {
        val path = mutableListOf<ChessPosition>()

        var position: ChessPosition? = this
        while (position != null && position != destination) {
            path.add(position)
            position = position.findNextPosition(direction)
        }

        if (position == null || path.size > limit) {
            return null
        }
        return ChessPath(path.apply { add(position) })
    }

    fun reversed(): ChessPosition = get(row.reversed(), col.reversed())

    private fun findNextPosition(direction: PieceDirection): ChessPosition? {
        val (nextRow, nextCol) = direction.nextOf(row, col) ?: return null
        return ChessPosition(nextRow, nextCol)
    }

    companion object {

        private const val NO_LIMIT = 8

        private val POSITION_CACHE: Map<Pair<ChessRow, ChessCol>, ChessPosition> = createCache()

        private fun createCache(): Map<Pair<ChessRow, ChessCol>, ChessPosition> {
            val cache: MutableMap<Pair<ChessRow, ChessCol>, ChessPosition> = mutableMapOf()

            for (row in ChessRow.values()) {
                for (col in ChessCol.values()) {
                    cache[row to col] = ChessPosition(row, col)
                }
            }

            return cache
        }

        fun get(row: ChessRow, col: ChessCol): ChessPosition {
            return POSITION_CACHE[row to col]
                    ?: throw IllegalArgumentException("Range of position is between A1 to H8")
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ChessPosition

        if (row != other.row) return false
        if (col != other.col) return false

        return true
    }

    override fun hashCode(): Int {
        var result = row.hashCode()
        result = 31 * result + col.hashCode()
        return result
    }
}
