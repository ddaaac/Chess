package com.example.demo.chess.domain.piece.move

private val BLOCK = BlockMoving()
private val JUST = JustMoving()
private val ATTACK = AttackMoving()

fun blockMoving(): PieceMovingStrategy = BLOCK
fun justMoving(): PieceMovingStrategy = JUST
fun attackMoving(): PieceMovingStrategy = ATTACK
fun blockJustMoving(): PieceMovingStrategy = CombinationOf(BLOCK, JUST)
fun blockAttackMoving(): PieceMovingStrategy = CombinationOf(BLOCK, ATTACK)
