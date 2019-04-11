package org.auto1.rpg.console.ui;

import org.auto1.rpg.menu.WorldConfigurationMenu;
import org.auto1.rpg.menu.utils.CliMenuBase;
import org.auto1.rpg.console.io.InputParser;
import org.auto1.rpg.console.io.OutputWriter;
import org.auto1.rpg.map.realm.RealmConfiguration;

import java.util.List;

public class CliWorldConfigurationMenu extends CliMenuBase<RealmConfiguration> implements WorldConfigurationMenu {

    public CliWorldConfigurationMenu(InputParser inputParser, OutputWriter outputWriter) {
        super(inputParser, outputWriter);
    }

    @Override
    public RealmConfiguration chooseConfiguration(String realmQuestion, List<RealmConfiguration> realmConfigs) {
        setMenuItems(realmConfigs);

        printAllOptions(realmQuestion);
        return selectOption();
    }

    @Override
    public void confirmRealmConfiguration(String realmConfirmationMessage) {
        outputWriter.showMessageWithSpace(realmConfirmationMessage);
    }
}
