package de.sambalmueslie.project.refactored.game.level

import de.sambalmueslie.project.refactored.game.card.Card
import de.sambalmueslie.project.refactored.game.player.Monster


data class GameLevel(
    val number: Int,
    val playerCards: List<Card>,
    val availableMonster: List<Monster>,
    val rooms: List<Room>,
)
