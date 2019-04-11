package org.auto1.rpg.menu;

import org.auto1.rpg.exception.ShouldNeverHappen;
import org.auto1.rpg.game.GameManager;
import org.auto1.rpg.exception.ConfigurationException;
import org.auto1.rpg.game.state.GameStateProvider;
import org.auto1.rpg.menu.MainMenu;
import org.auto1.rpg.menu.RealmConfigurationProvider;
import org.auto1.rpg.menu.item.MainMenuItem;

import static org.auto1.rpg.common.utils.ColorFormatter.boldMagenta;
import static org.auto1.rpg.menu.item.MainMenuItem.EXIT;

public class MainMenuManager {
    private final RealmConfigurationProvider configurationProvider;
    private final GameStateProvider gameStateProvider;
    private final AllMenus allMenus;
    private final MainMenu mainMenu;

    public MainMenuManager(RealmConfigurationProvider configurationProvider, AllMenus allMenus, GameStateProvider gameStateProvider) {
        this.allMenus = allMenus;
        this.mainMenu = allMenus.mainMenu();
        this.configurationProvider = configurationProvider;
        this.gameStateProvider = gameStateProvider;
    }

    public void showMenu() throws ConfigurationException {
        MainMenuItem choice;
        do {
            mainMenu.showMessage(boldMagenta("\nWelcome to Main Menu"));
            choice = mainMenu.showMenu();

            switch (choice) {
                case START:
                    GameManager.newGame(gameStateProvider, allMenus, configurationProvider.load());
                    break;
                case LOAD:
                    GameManager.loadGame(gameStateProvider, allMenus);
                    break;
                case EXIT:
                    mainMenu.showMessage("Come back soon! :)");
                    break;
                default:
                    throw new ShouldNeverHappen();
            }
        } while (EXIT != choice);


    }


}
