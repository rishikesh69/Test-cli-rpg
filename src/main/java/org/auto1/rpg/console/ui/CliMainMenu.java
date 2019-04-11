package org.auto1.rpg.console.ui;

import org.auto1.rpg.console.io.InputParser;
import org.auto1.rpg.console.io.OutputWriter;
import org.auto1.rpg.menu.MainMenu;
import org.auto1.rpg.menu.item.MainMenuItem;
import org.auto1.rpg.menu.utils.CliEnumMenuBase;

public class CliMainMenu extends CliEnumMenuBase<MainMenuItem> implements MainMenu {

    public CliMainMenu(InputParser inputParser, OutputWriter outputWriter) {
        super(inputParser, outputWriter, MainMenuItem.values());
    }

    @Override
    public MainMenuItem showMenu() {
        printAllOptions();
        return selectOption();
    }
}
