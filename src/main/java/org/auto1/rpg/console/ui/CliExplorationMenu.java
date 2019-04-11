package org.auto1.rpg.console.ui;

import org.auto1.rpg.console.io.InputParser;
import org.auto1.rpg.console.io.OutputWriter;
import org.auto1.rpg.console.ui.CliMessageConstants;
import org.auto1.rpg.menu.ExplorationMenu;
import org.auto1.rpg.menu.item.ExplorationMenuItem;
import org.auto1.rpg.menu.utils.CliEnumMenuBase;
import org.auto1.rpg.menu.utils.ExplorationMenuToStringFormatter;
import org.auto1.rpg.menu.utils.MenuToStringFormatter;

public class CliExplorationMenu extends CliEnumMenuBase<ExplorationMenuItem> implements ExplorationMenu {
    public CliExplorationMenu(InputParser inputParser, OutputWriter outputWriter) {
        super(inputParser, outputWriter, ExplorationMenuItem.values());
    }

    @Override
    public void showMap(String map) {
        outputWriter.showMessageWithSpace(map);
    }

    @Override
    public void showStatistics(String statistics) {
        outputWriter.showMessageWithSpace(statistics);
    }

    @Override
    protected MenuToStringFormatter<ExplorationMenuItem> menuFormatter() {
        return new ExplorationMenuToStringFormatter();
    }

    @Override
    protected ExplorationMenuItem readUserChoice() {
        ExplorationMenuItem choice = ExplorationMenuItem.fromString(inputParser.readUserInputAsString());
        if (null == choice) {
            showMessage(CliMessageConstants.WRONG_OPTION_SELECTED);
            return ExplorationMenuItem.COMMANDS;
        }

        return choice;
    }

}
