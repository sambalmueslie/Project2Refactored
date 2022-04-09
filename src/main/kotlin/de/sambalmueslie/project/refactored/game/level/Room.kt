package de.sambalmueslie.project.refactored.game.level

import de.sambalmueslie.project.refactored.game.player.Monster

data class Room(
    val number: Int,
    val space: Int,
    var monster: List<Monster> = emptyList()
)
