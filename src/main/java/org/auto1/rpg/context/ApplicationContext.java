package org.auto1.rpg.context;

import org.auto1.rpg.console.io.InputParser;
import org.auto1.rpg.console.io.OutputWriter;
import org.auto1.rpg.console.ui.CliBeforeFightMenu;
import org.auto1.rpg.console.ui.CliExplorationMenu;
import org.auto1.rpg.console.ui.CliFightMenu;
import org.auto1.rpg.console.ui.CliMainMenu;
import org.auto1.rpg.console.ui.CliPlayerConfigurationMenu;
import org.auto1.rpg.console.ui.CliWorldConfigurationMenu;
import org.auto1.rpg.game.state.SerializedGameStateProvider;
import org.auto1.rpg.map.realm.SerializedRealmConfigProvider;
import org.auto1.rpg.exception.BeanException;
import org.auto1.rpg.menu.AllMenus;
import org.auto1.rpg.menu.MainMenuManager;
import org.auto1.rpg.menu.BeforeFightMenu;
import org.auto1.rpg.menu.ExplorationMenu;
import org.auto1.rpg.menu.FightMenu;
import org.auto1.rpg.game.state.GameStateProvider;
import org.auto1.rpg.menu.MainMenu;
import org.auto1.rpg.menu.PlayerConfigurationMenu;
import org.auto1.rpg.menu.RealmConfigurationProvider;
import org.auto1.rpg.menu.WorldConfigurationMenu;

import java.util.HashMap;
import java.util.Map;

/**
 * TODO: this should be done by some DI framework... but guess what... I can't
 * use them in here! so I just quickly wrote a very simple one, just because
 * it's useful
 */
public class ApplicationContext {

	private static final Map<Class, Object> context = new HashMap<>();

	static {
		consoleIo();
		configurationProviders();
		loadSaveGame();
		menus();
		managers();
	}

	private ApplicationContext() {
	}

	private static void consoleIo() {
		OutputWriter outputWriter = new OutputWriter(System.out);
		extendContext(OutputWriter.class, outputWriter);

		InputParser inputParser = new InputParser(outputWriter, System.in);
		extendContext(InputParser.class, inputParser);
	}

	private static void configurationProviders() {
		RealmConfigurationProvider realmConfigurationProvider = new SerializedRealmConfigProvider();
		extendContext(RealmConfigurationProvider.class, realmConfigurationProvider);
	}

	private static void loadSaveGame() {
		GameStateProvider gameStateProvider = new SerializedGameStateProvider();
		extendContext(GameStateProvider.class, gameStateProvider);
	}

	private static void menus() {
		OutputWriter outputWriter = getBean(OutputWriter.class);
		InputParser inputParser = getBean(InputParser.class);

		PlayerConfigurationMenu playerConfigurationMenu = new CliPlayerConfigurationMenu(inputParser, outputWriter);
		extendContext(PlayerConfigurationMenu.class, playerConfigurationMenu);

		WorldConfigurationMenu worldConfigurationMenu = new CliWorldConfigurationMenu(inputParser, outputWriter);
		extendContext(WorldConfigurationMenu.class, worldConfigurationMenu);

		MainMenu mainMenu = new CliMainMenu(inputParser, outputWriter);
		extendContext(MainMenu.class, mainMenu);

		ExplorationMenu explorationMenu = new CliExplorationMenu(inputParser, outputWriter);
		extendContext(ExplorationMenu.class, explorationMenu);

		FightMenu fightMenu = new CliFightMenu(inputParser, outputWriter);
		extendContext(FightMenu.class, fightMenu);

		BeforeFightMenu beforeFightMenu = new CliBeforeFightMenu(inputParser, outputWriter);
		extendContext(BeforeFightMenu.class, beforeFightMenu);

		AllMenus allMenus = new AllMenus(mainMenu, playerConfigurationMenu, worldConfigurationMenu, explorationMenu,
				beforeFightMenu, fightMenu);
		extendContext(AllMenus.class, allMenus);
	}

	private static void managers() {
		AllMenus allMenus = getBean(AllMenus.class);
		GameStateProvider gameStateProvider = getBean(GameStateProvider.class);
		RealmConfigurationProvider realmConfigurationProvider = getBean(RealmConfigurationProvider.class);

		MainMenuManager mainMenuManager = new MainMenuManager(realmConfigurationProvider, allMenus, gameStateProvider);
		extendContext(MainMenuManager.class, mainMenuManager);
	}

	private static void extendContext(Class type, Object impl) {
		context.put(impl.getClass(), impl);
		context.put(type, impl);
	}

	@SuppressWarnings("unchecked")
	public static <T> T getBean(Class<T> clazz) {
		T bean;

		try {
			bean = (T) context.get(clazz);
		} catch (Exception e) {
			throw new BeanException(e);
		}

		if (null == bean) {
			throw new BeanException();
		}

		return bean;
	}
}
