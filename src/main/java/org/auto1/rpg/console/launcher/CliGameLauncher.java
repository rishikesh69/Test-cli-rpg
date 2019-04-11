package org.auto1.rpg.console.launcher;

import static org.auto1.rpg.console.launcher.CliGameConstants.CHECK_SOLUTION;
import static org.auto1.rpg.console.launcher.CliGameConstants.CONFIG_ISSUE;
import static org.auto1.rpg.console.launcher.CliGameConstants.GAME_ISSUE;

import org.auto1.rpg.console.io.UserInputParseException;
import org.auto1.rpg.context.ApplicationContext;
import org.auto1.rpg.menu.MainMenuManager;
import org.auto1.rpg.exception.ConfigurationException;

public class CliGameLauncher {
	
	private CliGameLauncher(){
		//No ops
	}
    private static MainMenuManager mainMenuManager = ApplicationContext.getBean(MainMenuManager.class);

    public static void startGame() {
        try {
            mainMenuManager.showMenu();
        } catch (UserInputParseException e) {
            shutdown(e.getMessage(), e);
        } catch (ConfigurationException e) {
            if (null != e.getMessage()) {
                shutdown(e.getMessage(), e);
            } else {
                String msg = CONFIG_ISSUE + CHECK_SOLUTION;
                shutdown(msg, e);
            }

        } catch (Exception e) {
            shutdown(GAME_ISSUE, e);
        }
    }

    private static void shutdown(String msg, Exception e) {
        System.out.println(msg);
        System.out.println(e.getMessage());
        System.exit(1);
    }
}
