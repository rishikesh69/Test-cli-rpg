package org.auto1.rpg.console.ui;

import org.auto1.rpg.console.io.InputParser;
import org.auto1.rpg.console.io.OutputWriter;
import org.auto1.rpg.menu.FightMenu;
import org.auto1.rpg.menu.item.FightMenuItem;
import org.auto1.rpg.menu.utils.CliEnumMenuBase;

public class CliFightMenu extends CliEnumMenuBase<FightMenuItem> implements FightMenu {
    public CliFightMenu(InputParser inputParser, OutputWriter outputWriter) {
        super(inputParser, outputWriter, FightMenuItem.values());
    }
}
