package de.sambalmueslie.project.refactored.game

import de.sambalmueslie.project.refactored.game.card.CardRule
import de.sambalmueslie.project.refactored.game.card.CardType
import de.sambalmueslie.project.refactored.game.card.action.SkipAction
import de.sambalmueslie.project.refactored.game.card.rules.on

val SCRATCH_RULE = CardRule { card, turn ->
    card on turn hurt { it.level * 5 } except (CardType.PARRY) reduce { it.level * 7 }
}

val CLAW_RULE = CardRule { card, turn ->
    card on turn hurt { it.level * 6 } except (CardType.PARRY) reduce { it.level * 7 }
}

val SMASH_RULE = CardRule { card, turn ->
    card on turn hurt { it.level * 8 } except (CardType.PARRY) reduce { it.level * 7 }
}

val BITE_RULE = CardRule { card, turn ->
    card on turn hurt { it.level * 10 } except (CardType.PARRY) reduce { it.level * 7 }
}

val MONSTER_FOCUS_RULE = CardRule { card, turn ->
    card on turn focus { it.level } except { oppositeCard, initialFocus ->
        when (oppositeCard.type) {
            CardType.SLASH -> 0
            CardType.SWING -> 0
            else -> initialFocus
        }
    }
}

val BLOCK_RULE = CardRule { _, _ -> SkipAction() }
val DEFLECT_RULE = CardRule { _, _ -> SkipAction() }

val MONSTER_WATER_RULE = CardRule { card, turn ->
    card on turn hurt { it.level * 8 + 2 } except (CardType.REFLECT) reduce { it.level * 10 }
}

val MONSTER_ICE_RULE = CardRule { card, turn ->
    card on turn hurt { it.level * 10 + 2 } except (CardType.REFLECT) reduce { it.level * 10 }
}

val MONSTER_FIRE_RULE = CardRule { card, turn ->
    card on turn hurt { it.level * 12 + 2 } except (CardType.REFLECT) reduce { it.level * 10 }
}

val MONSTER_LIGHTNING_RULE = CardRule { card, turn ->
    card on turn hurt { it.level * 14 + 2 } except (CardType.REFLECT) reduce { it.level * 10 }
}


val SLASH_RULE = CardRule { card, turn ->
    card on turn hurt { it.level * 4 + turn.diceRoll } except (CardType.REFLECT) reduce { it.level * 7 }
}

val SWING_RULE = CardRule { card, turn ->
    card on turn hurt { it.level * 5 + turn.diceRoll } except (CardType.REFLECT) reduce { it.level * 7 }
}

val THRUST_RULE = CardRule { card, turn ->
    card on turn hurt { it.level * 6 + turn.diceRoll } except { oppositeCard, initialDamage ->
        if (turn.diceRoll >= 6) {
            initialDamage + 4 * oppositeCard.level
        } else if (oppositeCard.type == CardType.BLOCK) {
            initialDamage - 7 * oppositeCard.level
        } else {
            initialDamage
        }
    }
}

val PIERCE_RULE = CardRule { card, turn ->
    card on turn hurt { it.level * 6 + turn.diceRoll } except { oppositeCard, initialDamage ->
        if (turn.diceRoll >= 6) {
            initialDamage + 5 * oppositeCard.level
        } else if (oppositeCard.type == CardType.BLOCK) {
            initialDamage - 7 * oppositeCard.level
        } else {
            initialDamage
        }
    }
}

val PARRY_RULE = CardRule { _, _ -> SkipAction() }

val PLAYER_FOCUS_RULE = CardRule { card, turn ->
    card on turn focus { it.level } except { oppositeCard, initialFocus ->
        when (oppositeCard.type) {
            CardType.SCRATCH -> 0
            CardType.CLAW -> 0
            else -> initialFocus // TODO consider some mysterious "die" which the runna has
        }
    }
}

val REFLECT_RULE = CardRule { _, _ -> SkipAction() }


val PLAYER_WATER_RULE = CardRule { card, turn ->
    // TODO consider opposite player type (LIGHTING)
    card on turn hurt { it.level * 2 + turn.activeUnit.focusPoints } except (CardType.DEFLECT) reduce { it.level * 11 + 2 }
}

val PLAYER_ICE_RULE = CardRule { card, turn ->
    // TODO consider opposite player type (WATER)
    card on turn hurt { it.level * 2 + turn.activeUnit.focusPoints } except (CardType.DEFLECT) reduce { it.level * 11 + 2 }
}

val PLAYER_FIRE_RULE = CardRule { card, turn ->
    // TODO consider opposite player type (ICE)
    card on turn hurt { it.level * 2 + turn.activeUnit.focusPoints } except (CardType.DEFLECT) reduce { it.level * 11 + 2 }
}

val PLAYER_LIGHTNING_RULE = CardRule { card, turn ->
    // TODO consider opposite player type (FIRE)
    card on turn hurt { it.level * 2 + turn.activeUnit.focusPoints } except (CardType.DEFLECT) reduce { it.level * 11 + 2 }
}
