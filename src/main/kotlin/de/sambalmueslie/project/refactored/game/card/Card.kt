package de.sambalmueslie.project.refactored.game.card

data class Card(
    val type: CardType,
    val level: Int,
    val price: Int,
    val canAttack: Boolean,
    val magical: Boolean,
    val rule: CardRule
)
