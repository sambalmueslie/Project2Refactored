package de.sambalmueslie.project.refactored.game.player

import de.sambalmueslie.project.refactored.game.card.Card

data class Player(
    override val name: String,
    override var hitPoints: Int,
    override var focusPoints: Int,
    override var availableCards: List<Card>,
    val type: PlayerType,
) : Unit
