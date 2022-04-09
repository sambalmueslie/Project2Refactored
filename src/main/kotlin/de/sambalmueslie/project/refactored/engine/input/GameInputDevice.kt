package de.sambalmueslie.project.refactored.engine.input

interface GameInputDevice {
    fun waitForNumberInput(): Int
    fun waitForNumbersInput(): List<Int>
}
