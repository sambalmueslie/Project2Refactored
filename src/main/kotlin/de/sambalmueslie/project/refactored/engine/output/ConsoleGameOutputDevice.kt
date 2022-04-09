package de.sambalmueslie.project.refactored.engine.output


import de.sambalmueslie.project.refactored.game.player.PlayerType
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class ConsoleGameOutputDevice : GameOutputDevice {

    companion object {
        val logger: Logger = LoggerFactory.getLogger(ConsoleGameOutputDevice::class.java)
    }

    override fun showWelcomeMessage() {
        output("Welcome to Runa's Strive")
    }

    override fun showGoodbyeMessage() {
        output("Goodbye, have a nice day")
    }

    override fun showPlayerTypeSelection(playerTypes: List<PlayerType>) {
        output("Select Runa's character class")
        playerTypes.forEachIndexed { index, playerType -> output(" $index: ${playerType.name}") }
        output(" q: Quit Game")
    }

    override fun showPlayerTypeSelectionResult(type: PlayerType?) {
        if (type == null) {
            output("Invalid character class selected, try again")
        } else {
            output("You selected ${type.name} for the character class")
        }
    }

    override fun showShuffleCardsMessage() {
        output("To shuffle ability cards and monsters, enter two seeds (comma separated)")
    }


    private fun output(message: String) {
        println("[GAME OUTPUT]: $message")
    }


}
