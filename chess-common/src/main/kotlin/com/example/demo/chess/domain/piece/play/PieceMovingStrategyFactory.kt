package com.example.demo.chess.domain.piece.play

private val BLOCK: ChessPlayingStrategy = Block()
private val MOVING: ChessPlayingStrategy = Moving()
private val ATTACK: ChessPlayingStrategy = Attack()

val ATTACK_OR_MOVING: ChessPlayingStrategy = ApplyAny(MOVING, ATTACK)
val BLOCK_MOVING: ChessPlayingStrategy = ApplyAll(BLOCK, MOVING)
val BLOCK_ATTACK: ChessPlayingStrategy = ApplyAll(BLOCK, ATTACK)
val ATTACK_OR_MOVING_WITH_BLOCK: ChessPlayingStrategy = ApplyAll(BLOCK, ApplyAny(MOVING, ATTACK))
