package com.example.demo.chess.domain.piece.move

private val BLOCK = BlockMoving()
private val JUST = JustMoving()
private val ATTACK = AttackMoving()

fun blockMoving(): PieceMovingStrategy = BLOCK
fun justMoving(): PieceMovingStrategy = JUST
fun attackMoving(): PieceMovingStrategy = ATTACK
fun blockJustMoving(): PieceMovingStrategy = ApplyAll(BLOCK, JUST)
fun blockAttackMoving(): PieceMovingStrategy = ApplyAll(BLOCK, ATTACK)
fun blockJustOrAttackMoving(): PieceMovingStrategy = ApplyAll(BLOCK, ApplyAny(JUST, ATTACK))