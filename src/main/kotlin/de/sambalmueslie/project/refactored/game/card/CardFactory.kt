package de.sambalmueslie.project.refactored.game.card


class CardFactory {
    companion object {
        fun attack(type: CardType, level: Int, price: Int = 0, action: CardRule) = Card(type, level, price, canAttack = true, false, action)
        fun defense(type: CardType, level: Int, price: Int = 0, action: CardRule) = Card(type, level, price, canAttack = false, false, action)

        fun magicalAttack(type: CardType, level: Int, price: Int = 0, action: CardRule) = Card(type, level, price, canAttack = true, true, action)
        fun magicalDefense(type: CardType, level: Int, price: Int = 0, action: CardRule) = Card(type, level, price, canAttack = false, true, action)
    }
}
