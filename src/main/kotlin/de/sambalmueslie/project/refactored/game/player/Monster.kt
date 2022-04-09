package de.sambalmueslie.project.refactored.game.player

import de.sambalmueslie.project.refactored.game.card.Card


data class Monster(
    override val name: String,
    val isBoss: Boolean,
    val level: Int,
    val type: MonsterType,
    override var hitPoints: Int,
    override var focusPoints: Int,
    override var availableCards: List<Card>,
    ) : Unit {

}
