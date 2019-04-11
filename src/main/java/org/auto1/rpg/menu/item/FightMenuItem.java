package org.auto1.rpg.menu.item;

import static org.auto1.rpg.common.utils.ColorFormatter.blue;
import static org.auto1.rpg.common.utils.ColorFormatter.red;
import static org.auto1.rpg.common.utils.ColorFormatter.yellow;
import static org.auto1.rpg.common.constants.CommonMessages.FIGHT_BONUS_ARMOR_FOR_DEFENCE;
import static org.auto1.rpg.common.constants.CommonMessages.FIGHT_FLEEING_HP_REDUCTION;

public enum FightMenuItem {
    ATTACK(red("Attack") + " the enemy with your weapon"),
    DEFEND(blue("Raise defences") + ", which will reduce any future damage taken in that skirmish by " + FIGHT_BONUS_ARMOR_FOR_DEFENCE),
    FLEE(yellow("Flee like a coward.") + " This will save your life, but reduce maxHp by " + FIGHT_FLEEING_HP_REDUCTION);

    private final String description;

    FightMenuItem(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}
