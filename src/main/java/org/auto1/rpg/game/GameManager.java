package org.auto1.rpg.game;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.auto1.rpg.map.character.Player;
import org.auto1.rpg.map.explore.ExplorationManager;
import org.auto1.rpg.exception.LoadGameException;
import org.auto1.rpg.exception.PlayerDied;
import org.auto1.rpg.exception.Victory;
import org.auto1.rpg.menu.AllMenus;
import org.auto1.rpg.map.World;
import org.auto1.rpg.exception.ConfigurationException;
import org.auto1.rpg.game.state.GameStateProvider;
import org.auto1.rpg.menu.MainMenu;
import org.auto1.rpg.menu.PlayerConfigurationMenu;
import org.auto1.rpg.menu.WorldConfigurationMenu;
import org.auto1.rpg.game.state.GameState;
import org.auto1.rpg.map.character.PlayerConfiguration;
import org.auto1.rpg.map.realm.RealmConfiguration;

import java.util.List;

import static org.auto1.rpg.map.legend.WorldViewBuilder.buildWorldView;
import static org.auto1.rpg.game.StaticMessages.GAME_LOADED;
import static org.auto1.rpg.common.constants.CommonMessages.INTRODUCTION;
import static org.auto1.rpg.game.StaticMessages.REALM_QUESTION;
import static org.auto1.rpg.game.StaticMessages.greeting;
import static org.auto1.rpg.menu.QuestionsToPlayer.buildQuestion;
import static org.auto1.rpg.game.StaticMessages.realmConfigDone;

//TODO: add tests
public class GameManager {
    private static final Logger LOG = LogManager.getLogger(GameManager.class);

    private final GameStateProvider gameStateProvider;
    private final AllMenus allMenus;

    private final World world;
    private final Player player;

    public static void newGame(GameStateProvider gameStateProvider, AllMenus allMenus, List<RealmConfiguration> realmConfig) throws ConfigurationException {
        GameManager gameManager = new GameManager(gameStateProvider, allMenus, realmConfig);
        gameManager.startGame();
    }

    private GameManager(GameStateProvider gameStateProvider, AllMenus allMenus, List<RealmConfiguration> realmConfig) throws ConfigurationException {
        this.gameStateProvider = gameStateProvider;
        this.allMenus = allMenus;

        this.world = initWorld(realmConfig);
        this.player = initPlayer();
    }

    public static void loadGame(GameStateProvider gameStateProvider, AllMenus allMenus) throws ConfigurationException {
        try {
            GameManager gameManager = new GameManager(gameStateProvider, allMenus);
            gameManager.startGame();
        } catch (LoadGameException e) {
            allMenus.mainMenu().showMessage(e.getMessage());
        }

    }

    public GameManager(GameStateProvider gameStateProvider, AllMenus allMenus) throws ConfigurationException {
        this.gameStateProvider = gameStateProvider;
        this.allMenus = allMenus;

        GameState loadedGame = gameStateProvider.loadGame();
        this.world = loadedGame.getWorld();
        this.player = loadedGame.getPlayer();

        allMenus.explorationMenu().showMessage(GAME_LOADED);
        allMenus.explorationMenu().showMap(buildWorldView(world, player));
    }

    private World initWorld(List<RealmConfiguration> realmConfigs) throws ConfigurationException {
        WorldConfigurationMenu worldConfigMenu = allMenus.worldConfigMenu();

        RealmConfiguration realmConfig = worldConfigMenu.chooseConfiguration(REALM_QUESTION, realmConfigs);

        World world = new World(realmConfig);
        worldConfigMenu.confirmRealmConfiguration(realmConfigDone(world));

        return world;
    }

    private Player initPlayer() {
        PlayerConfigurationMenu playerConfigMenu = allMenus.playerConfigMenu();

        playerConfigMenu.showIntroduction(INTRODUCTION);
        PlayerConfiguration playerConfig = playerConfigMenu.askForPlayerConfig(buildQuestion());

        Player player = new Player(playerConfig, world.randomCoordinatesWithoutAnyone());
        playerConfigMenu.greetPlayer(greeting(player, world));

        return player;
    }

    void startGame() throws ConfigurationException {
        LOG.traceEntry();
        MainMenu mainMenu = allMenus.mainMenu();

        ExplorationManager explorationManager = new ExplorationManager(gameStateProvider, allMenus, world, player);
        try {
            explorationManager.startExploring();
        } catch (Victory e) {
            mainMenu.showMessage(e.getMessage());
        } catch (PlayerDied e) {
            mainMenu.showMessage(e.getMessage());
        }

        LOG.traceExit();
    }
}
