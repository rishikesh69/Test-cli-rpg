package org.auto1.rpg.console.ui;

import org.auto1.rpg.console.io.InputParser;
import org.auto1.rpg.console.io.OutputWriter;
import org.auto1.rpg.menu.BeforeFightMenu;
import org.auto1.rpg.menu.item.BeforeFightMenuItem;
import org.auto1.rpg.menu.utils.CliEnumMenuBase;

public class CliBeforeFightMenu extends CliEnumMenuBase<BeforeFightMenuItem> implements BeforeFightMenu {
    public CliBeforeFightMenu(InputParser inputParser, OutputWriter outputWriter) {
        super(inputParser, outputWriter, BeforeFightMenuItem.values());
    }
}
