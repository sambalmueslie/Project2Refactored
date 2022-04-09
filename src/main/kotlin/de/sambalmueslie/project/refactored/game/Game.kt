package de.sambalmueslie.project.refactored.game


import de.sambalmueslie.project.refactored.game.level.GameLevel
import de.sambalmueslie.project.refactored.game.player.Player
import de.sambalmueslie.project.refactored.game.player.PlayerType
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import kotlin.random.Random

class Game() {


    companion object {
        val logger: Logger = LoggerFactory.getLogger(Game::class.java)
        const val MIN_ROOM = 1
        const val MAX_ROOM = 4
        const val MIN_LEVEL = 1
        const val MAX_LEVEL = 2
    }

    private val playerCards = createPlayerCards().groupBy { it.level }
    private val monsterCards = createMonsterCards().groupBy { it.level }
    private val monsters = createMonsters(monsterCards).groupBy { it.level }
    val playerTypes = createPlayerTypes(playerCards)

    private lateinit var player: Player
    private val gameLevels = createGameLevels(playerCards, monsters)
    private var currentLevel: GameLevel = gameLevels.first()


    fun setPlayer(type: PlayerType) {
        val initialCards = currentLevel.playerCards.filter { !type.cards.contains(it) }
        player = Player("Runa", PLAYER_INITIAL_HIT_POINT, PLAYER_INITIAL_FOCUS_POINTS, initialCards, type)
    }

    fun shufflePlayerCards(seed: Int) {
        player.availableCards = player.availableCards.shuffled(Random(seed))
    }

    fun shuffleMonster(seed: Int) {
        gameLevels.forEach { level ->
            val nonBossMonster = level.availableMonster.shuffled(Random(seed))
            level.rooms.forEachIndexed { index, room ->
                val bossRoom = index == level.rooms.size - 1
                if (bossRoom) {
                    room.monster = level.availableMonster.filter { it.isBoss }
                } else {
                    room.monster = nonBossMonster.subList(index, index + room.space)
                }
            }

        }
    }


}
