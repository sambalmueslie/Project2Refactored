package de.sambalmueslie.project.refactored.game

import de.sambalmueslie.project.refactored.game.card.Card
import de.sambalmueslie.project.refactored.game.player.Unit

data class Turn(
    val oppositeCard: Card?,
    val diceRoll: Int,
    val activeUnit: Unit
)
