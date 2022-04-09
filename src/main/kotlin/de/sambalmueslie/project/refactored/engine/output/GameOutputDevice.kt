package de.sambalmueslie.project.refactored.engine.output

import de.sambalmueslie.project.refactored.game.player.PlayerType

interface GameOutputDevice {

    fun showWelcomeMessage()
    fun showPlayerTypeSelection(playerTypes: List<PlayerType>)
    fun showPlayerTypeSelectionResult(type: PlayerType?)
    fun showShuffleCardsMessage()
    fun showGoodbyeMessage()

}
