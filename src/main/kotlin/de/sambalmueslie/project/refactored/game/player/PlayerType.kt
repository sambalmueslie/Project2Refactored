package de.sambalmueslie.project.refactored.game.player

import de.sambalmueslie.project.refactored.game.card.Card

data class PlayerType(
    val name: String,
    val cards: List<Card>
)
