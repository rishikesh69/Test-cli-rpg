package org.auto1.rpg.menu.item;

import static org.auto1.rpg.common.utils.ColorFormatter.blue;
import static org.auto1.rpg.common.utils.ColorFormatter.red;

public enum BeforeFightMenuItem {
    ATTACK(red("Attack!!!")),
    FALL_BACK(blue("Fall back from this fight."));
    private final String description;

    BeforeFightMenuItem(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }
}
