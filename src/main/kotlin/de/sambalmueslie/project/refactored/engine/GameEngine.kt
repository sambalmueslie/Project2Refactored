package de.sambalmueslie.project.refactored.engine


import de.sambalmueslie.project.refactored.engine.input.GameInputDevice
import de.sambalmueslie.project.refactored.engine.input.QuitGameException
import de.sambalmueslie.project.refactored.engine.output.GameOutputDevice
import de.sambalmueslie.project.refactored.game.Game
import de.sambalmueslie.project.refactored.game.player.PlayerType
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class GameEngine(
    private val outputDevice: GameOutputDevice,
    private val inputDevice: GameInputDevice
) {

    companion object {
        val logger: Logger = LoggerFactory.getLogger(GameEngine::class.java)
    }

    private val game = Game()

    fun run() {
        try {
            outputDevice.showWelcomeMessage()
            selectPlayerType()
            shuffleCardsAndMonsters()


        } catch (e: QuitGameException) {
            outputDevice.showGoodbyeMessage()
        }
    }


    private fun selectPlayerType() {
        var type: PlayerType?
        do {
            val playerTypes = game.playerTypes
            outputDevice.showPlayerTypeSelection(playerTypes)
            val input = inputDevice.waitForNumberInput()
            type = playerTypes.getOrNull(input)
            outputDevice.showPlayerTypeSelectionResult(type)
        } while (type == null)

        game.setPlayer(type)
    }


    private fun shuffleCardsAndMonsters() {
        var seeds: List<Int>
        do {
            outputDevice.showShuffleCardsMessage()
            seeds = inputDevice.waitForNumbersInput()
        } while (seeds.size != 2)

        game.shufflePlayerCards(seeds[0])
        game.shuffleMonster(seeds[1])

    }
}
