package de.sambalmueslie.project.refactored.game.card.rules

import de.sambalmueslie.project.refactored.game.Turn
import de.sambalmueslie.project.refactored.game.card.Card
import de.sambalmueslie.project.refactored.game.card.CardType
import de.sambalmueslie.project.refactored.game.card.action.AddFocusPointsAction
import de.sambalmueslie.project.refactored.game.card.action.ReduceHitPointsAction

infix fun Card.on(turn: Turn) = TurnBuilder(this, turn)

class TurnBuilder(private val card: Card, private val turn: Turn) {
 infix fun hurt(damage: (card: Card) -> Int) = ReduceHitPointsBuilder(turn, damage.invoke(card))
 infix fun focus(focus: (card: Card) -> Int) = AddFocusPointsBuilder(turn, focus.invoke(card))
}


class ReduceHitPointsBuilder(
    private val turn: Turn,
    private val damage: Int
) {
    infix fun except(type: CardType) = ReduceHitPointsExceptionBuilder(turn, damage, type)
    infix fun except(function: (oppositeCard: Card, initialDamage: Int) -> Int): ReduceHitPointsAction {
        val oppositeCard = turn.oppositeCard ?: return ReduceHitPointsAction(damage)
        return ReduceHitPointsAction(function.invoke(oppositeCard, damage))
    }

    fun get() = ReduceHitPointsAction(damage)
}

class ReduceHitPointsExceptionBuilder(
    private val turn: Turn,
    private val damage: Int,
    private val exceptionType: CardType
) {
    infix fun reduce(function: (oppositeCard: Card) -> Int): ReduceHitPointsAction {
        val oppositeCard = turn.oppositeCard ?: return ReduceHitPointsAction(damage)
        if (oppositeCard.type != exceptionType) return ReduceHitPointsAction(damage)

        val reduce = function.invoke(oppositeCard)
        val result = damage - reduce

        return ReduceHitPointsAction(if (result < 0) 0 else result)
    }
}

class AddFocusPointsBuilder(
    private val turn: Turn,
    private val focus: Int
) {
    infix fun except(function: (oppositeCard: Card, initialFocus: Int) -> Int): AddFocusPointsAction {
        val oppositeCard = turn.oppositeCard ?: return AddFocusPointsAction(focus)
        return AddFocusPointsAction(function.invoke(oppositeCard, focus))

    }
}
