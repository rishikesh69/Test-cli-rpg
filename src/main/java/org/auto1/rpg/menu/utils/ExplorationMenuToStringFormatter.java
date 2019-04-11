package org.auto1.rpg.menu.utils;

import org.auto1.rpg.menu.item.ExplorationMenuItem;

public class ExplorationMenuToStringFormatter extends MenuToStringFormatter<ExplorationMenuItem> {
    public ExplorationMenuToStringFormatter() {
    }

    public String format(ExplorationMenuItem item, int itemNumberInList) {
        return format(item.getDescription(), item.getKeyBinding());
    }
}
