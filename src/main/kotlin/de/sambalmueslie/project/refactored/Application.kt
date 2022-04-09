package de.sambalmueslie.project.refactored


import de.sambalmueslie.project.refactored.engine.input.ConsoleGameInputDevice
import de.sambalmueslie.project.refactored.engine.output.ConsoleGameOutputDevice
import de.sambalmueslie.project.refactored.engine.GameEngine
import org.slf4j.Logger
import org.slf4j.LoggerFactory


fun main(args: Array<String>) {
    Application(args).run()
}

class Application(args: Array<String>) {

    companion object {
        val logger: Logger = LoggerFactory.getLogger(Application::class.java)
    }

    private val outputDevice = ConsoleGameOutputDevice()
    private val inputDevice = ConsoleGameInputDevice()
    private val engine = GameEngine(outputDevice, inputDevice)

    fun run() {
        logger.info("Run application")
        engine.run()
    }
}
