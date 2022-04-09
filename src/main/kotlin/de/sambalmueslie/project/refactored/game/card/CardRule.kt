package de.sambalmueslie.project.refactored.game.card

import de.sambalmueslie.project.refactored.game.Turn

fun interface CardRule {
    fun evaluate(ownCard: Card, turn: Turn): CardAction
}

