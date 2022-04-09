package de.sambalmueslie.project.refactored.game

import de.sambalmueslie.project.refactored.game.card.Card
import de.sambalmueslie.project.refactored.game.card.CardFactory
import de.sambalmueslie.project.refactored.game.card.CardType
import de.sambalmueslie.project.refactored.game.level.GameLevel
import de.sambalmueslie.project.refactored.game.level.Room
import de.sambalmueslie.project.refactored.game.player.Monster
import de.sambalmueslie.project.refactored.game.player.MonsterType
import de.sambalmueslie.project.refactored.game.player.PlayerType


const val PLAYER_INITIAL_FOCUS_POINTS = 1
const val PLAYER_INITIAL_HIT_POINT = 50

fun createPlayerTypes(cards: Map<Int, List<Card>>): List<PlayerType> {
    val level1Cards = cards[1] ?: emptyList()
    return listOf(
        PlayerType("Warrior", level1Cards.filter { it.type == CardType.THRUST || it.type == CardType.PARRY }),
        PlayerType("Mage", level1Cards.filter { it.type == CardType.FOCUS || it.type == CardType.WATER }),
        PlayerType("Paladin", level1Cards.filter { it.type == CardType.SLASH || it.type == CardType.REFLECT }),
    )
}

fun createMonsters(cards: Map<Int, List<Card>>): List<Monster> {
    return listOf(
        // level 1
        Monster("Spider King", true, 1, MonsterType.LIGHTING, 50, 0, cards.filter(1, setOf(CardType.BITE, CardType.BLOCK, CardType.FOCUS, CardType.LIGHTNING))),
        Monster("Frog", false, 1, MonsterType.WATER, 16, 0, cards.filter(1, setOf(CardType.FOCUS, CardType.WATER))),
        Monster("Ghost", false, 1, MonsterType.ICE, 15, 0, cards.filter(1, setOf(CardType.FOCUS, CardType.ICE))),
        Monster("Gorgon", false, 1, MonsterType.FIRE, 13, 0, cards.filter(1, setOf(CardType.FOCUS, CardType.FIRE))),
        Monster("Skeleton", false, 1, MonsterType.LIGHTING, 14, 0, cards.filter(1, setOf(CardType.FOCUS, CardType.LIGHTNING))),
        Monster("Spider", false, 1, MonsterType.DEFAULT, 15, 0, cards.filter(1, setOf(CardType.BITE, CardType.BLOCK))),
        Monster("Goblin", false, 1, MonsterType.DEFAULT, 12, 0, cards.filter(1, setOf(CardType.SMASH, CardType.DEFLECT))),
        Monster("Rat", false, 1, MonsterType.DEFAULT, 14, 0, cards.filter(1, setOf(CardType.BLOCK, CardType.CLAW))),
        Monster("Mushroomlin", false, 1, MonsterType.DEFAULT, 20, 0, cards.filter(1, setOf(CardType.DEFLECT, CardType.SCRATCH))),
        // level 2
        Monster(
            "Mega Saurus", true, 2, MonsterType.DEFAULT, 100, 0,
            cards.filter(2, setOf(CardType.BITE, CardType.BLOCK, CardType.FOCUS)) + cards.filter(1, setOf(CardType.FIRE, CardType.LIGHTNING))
        ),
        Monster("Snake", false, 2, MonsterType.ICE, 31, 0, cards.filter(2, setOf(CardType.BITE, CardType.FOCUS, CardType.ICE))),
        Monster(
            "Dark Elf", false, 2, MonsterType.DEFAULT, 34, 0,
            cards.filter(2, setOf(CardType.FOCUS)) + cards.filter(1, setOf(CardType.WATER, CardType.LIGHTNING))
        ),
        Monster("Shadow Blade", false, 2, MonsterType.LIGHTING, 27, 0, cards.filter(1, setOf(CardType.SCRATCH, CardType.FOCUS, CardType.LIGHTNING))),
        Monster(
            "Hornet", false, 2, MonsterType.FIRE, 32, 0,
            cards.filter(2, setOf(CardType.SCRATCH, CardType.FOCUS, CardType.FIRE)) + cards.filter(1, setOf(CardType.FIRE))
        ),
        Monster("Tarantula", false, 2, MonsterType.DEFAULT, 33, 0, cards.filter(1, setOf(CardType.BITE, CardType.BLOCK, CardType.SCRATCH))),
        Monster("Bear", false, 2, MonsterType.DEFAULT, 40, 0, cards.filter(1, setOf(CardType.CLAW, CardType.DEFLECT, CardType.BLOCK))),
        Monster("Mushroomlon", false, 2, MonsterType.DEFAULT, 50, 0, cards.filter(1, setOf(CardType.DEFLECT, CardType.SCRATCH, CardType.BLOCK))),
        Monster("Wild Boar", false, 2, MonsterType.DEFAULT, 27, 0, cards.filter(1, setOf(CardType.SCRATCH, CardType.DEFLECT, CardType.BLOCK))),
    )
}


fun Map<Int, List<Card>>.filter(key: Int, filter: Set<CardType>): List<Card> {
    return this[key]?.filter { filter.contains(it.type) } ?: emptyList()
}


fun createPlayerCards(): List<Card> {
    return listOf(
        CardFactory.attack(CardType.SLASH, Game.MIN_LEVEL, 0, SLASH_RULE),
        CardFactory.attack(CardType.SLASH, Game.MAX_LEVEL, 0, SLASH_RULE),
        CardFactory.attack(CardType.SWING, Game.MIN_LEVEL, 0, SWING_RULE),
        CardFactory.attack(CardType.SWING, Game.MAX_LEVEL, 0, SWING_RULE),
        CardFactory.attack(CardType.THRUST, Game.MIN_LEVEL, 0, THRUST_RULE),
        CardFactory.attack(CardType.THRUST, Game.MAX_LEVEL, 0, THRUST_RULE),
        CardFactory.attack(CardType.PIERCE, Game.MIN_LEVEL, 0, PIERCE_RULE),
        CardFactory.attack(CardType.PIERCE, Game.MAX_LEVEL, 0, PIERCE_RULE),

        CardFactory.defense(CardType.PARRY, Game.MIN_LEVEL, 0, PARRY_RULE),
        CardFactory.defense(CardType.PARRY, Game.MAX_LEVEL, 0, PARRY_RULE),

        CardFactory.magicalAttack(CardType.WATER, Game.MIN_LEVEL, 1, PLAYER_WATER_RULE),
        CardFactory.magicalAttack(CardType.WATER, Game.MIN_LEVEL, 1, PLAYER_WATER_RULE),
        CardFactory.magicalAttack(CardType.ICE, Game.MIN_LEVEL, 1, PLAYER_ICE_RULE),
        CardFactory.magicalAttack(CardType.ICE, Game.MIN_LEVEL, 1, PLAYER_ICE_RULE),
        CardFactory.magicalAttack(CardType.FIRE, Game.MIN_LEVEL, 1, PLAYER_FIRE_RULE),
        CardFactory.magicalAttack(CardType.FIRE, Game.MIN_LEVEL, 1, PLAYER_FIRE_RULE),
        CardFactory.magicalAttack(CardType.LIGHTNING, Game.MIN_LEVEL, 1, PLAYER_LIGHTNING_RULE),
        CardFactory.magicalAttack(CardType.LIGHTNING, Game.MIN_LEVEL, 1, PLAYER_LIGHTNING_RULE),

        CardFactory.magicalDefense(CardType.FOCUS, Game.MIN_LEVEL, 0, PLAYER_FOCUS_RULE),
        CardFactory.magicalDefense(CardType.FOCUS, Game.MAX_LEVEL, 0, PLAYER_FOCUS_RULE),
        CardFactory.magicalDefense(CardType.REFLECT, Game.MIN_LEVEL, 0, REFLECT_RULE),
        CardFactory.magicalDefense(CardType.REFLECT, Game.MAX_LEVEL, 0, REFLECT_RULE),
    )
}


fun createMonsterCards(): List<Card> {
    return listOf(
        CardFactory.attack(CardType.SCRATCH, Game.MIN_LEVEL, 0, SCRATCH_RULE),
        CardFactory.attack(CardType.SCRATCH, Game.MAX_LEVEL, 0, SCRATCH_RULE),
        CardFactory.attack(CardType.CLAW, Game.MIN_LEVEL, 0, CLAW_RULE),
        CardFactory.attack(CardType.CLAW, Game.MAX_LEVEL, 0, CLAW_RULE),
        CardFactory.attack(CardType.SMASH, Game.MIN_LEVEL, 0, SMASH_RULE),
        CardFactory.attack(CardType.SMASH, Game.MAX_LEVEL, 0, SMASH_RULE),
        CardFactory.attack(CardType.BITE, Game.MIN_LEVEL, 0, BITE_RULE),
        CardFactory.attack(CardType.BITE, Game.MAX_LEVEL, 0, BITE_RULE),

        CardFactory.defense(CardType.BLOCK, Game.MIN_LEVEL, 0, BLOCK_RULE),
        CardFactory.defense(CardType.BLOCK, Game.MAX_LEVEL, 0, BLOCK_RULE),

        CardFactory.magicalAttack(CardType.WATER, Game.MIN_LEVEL, Game.MIN_LEVEL, MONSTER_WATER_RULE),
        CardFactory.magicalAttack(CardType.WATER, Game.MIN_LEVEL, Game.MAX_LEVEL, MONSTER_WATER_RULE),
        CardFactory.magicalAttack(CardType.ICE, Game.MIN_LEVEL, Game.MIN_LEVEL, MONSTER_ICE_RULE),
        CardFactory.magicalAttack(CardType.ICE, Game.MIN_LEVEL, Game.MAX_LEVEL, MONSTER_ICE_RULE),
        CardFactory.magicalAttack(CardType.FIRE, Game.MIN_LEVEL, Game.MIN_LEVEL, MONSTER_FIRE_RULE),
        CardFactory.magicalAttack(CardType.FIRE, Game.MIN_LEVEL, Game.MAX_LEVEL, MONSTER_FIRE_RULE),
        CardFactory.magicalAttack(CardType.LIGHTNING, Game.MIN_LEVEL, Game.MIN_LEVEL, MONSTER_LIGHTNING_RULE),
        CardFactory.magicalAttack(CardType.LIGHTNING, Game.MIN_LEVEL, Game.MAX_LEVEL, MONSTER_LIGHTNING_RULE),

        CardFactory.magicalDefense(CardType.FOCUS, Game.MIN_LEVEL, 0, MONSTER_FOCUS_RULE),
        CardFactory.magicalDefense(CardType.FOCUS, Game.MAX_LEVEL, 0, MONSTER_FOCUS_RULE),
        CardFactory.magicalDefense(CardType.DEFLECT, Game.MIN_LEVEL, 0, DEFLECT_RULE),
        CardFactory.magicalDefense(CardType.DEFLECT, Game.MAX_LEVEL, 0, DEFLECT_RULE),
    )
}

fun createGameLevels(playerCards: Map<Int, List<Card>>, monster: Map<Int, List<Monster>>): List<GameLevel> {
    return listOf(
        GameLevel(
            1, playerCards[1] ?: emptyList(), monster[1] ?: emptyList(),
            listOf(Room(1, 1), Room(2, 2), Room(3, 2), Room(4, 1))
        ),
        GameLevel(
            2, playerCards[2] ?: emptyList(), monster[2] ?: emptyList(),
            listOf(Room(1, 1), Room(2, 2), Room(3, 2), Room(4, 1))
        ),
    )
}
