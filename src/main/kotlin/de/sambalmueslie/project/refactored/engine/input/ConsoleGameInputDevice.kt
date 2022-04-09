package de.sambalmueslie.project.refactored.engine.input


class ConsoleGameInputDevice : GameInputDevice {

    override fun waitForNumberInput(): Int {
        return input().toIntOrNull() ?: -1
    }

    override fun waitForNumbersInput(): List<Int> {
        return input().split(",").mapNotNull { it.toIntOrNull() }.toList()
    }

    private fun input(): String {
        val input = readln().trim()
        if (input.uppercase() == "Q") throw QuitGameException()
        return input
    }

}
