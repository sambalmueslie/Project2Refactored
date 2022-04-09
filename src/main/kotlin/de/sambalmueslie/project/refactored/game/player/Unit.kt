package de.sambalmueslie.project.refactored.game.player

import de.sambalmueslie.project.refactored.game.card.Card

interface Unit {
    val name: String
    var focusPoints: Int
    var hitPoints: Int
    var availableCards: List<Card>
}
