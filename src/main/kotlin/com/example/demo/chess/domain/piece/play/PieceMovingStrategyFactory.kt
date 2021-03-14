package com.example.demo.chess.domain.piece.play

private val BLOCK: ChessPlayingStrategy = Block()
private val MOVING: ChessPlayingStrategy = Moving()
private val ATTACK: ChessPlayingStrategy = Attack()

fun attackOrMoving(): ChessPlayingStrategy = ApplyAny(MOVING, ATTACK)
fun blockMoving(): ChessPlayingStrategy = ApplyAll(BLOCK, MOVING)
fun blockAttack(): ChessPlayingStrategy = ApplyAll(BLOCK, ATTACK)
fun attackOrMovingWithBlock(): ChessPlayingStrategy = ApplyAll(BLOCK, ApplyAny(MOVING, ATTACK))
