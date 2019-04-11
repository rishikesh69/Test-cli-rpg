package org.auto1.rpg.map.explore;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.auto1.rpg.map.character.NonPlayer;
import org.auto1.rpg.map.character.Player;
import org.auto1.rpg.exception.ExplorationException;
import org.auto1.rpg.exception.ShouldNeverHappen;
import org.auto1.rpg.game.FightManager;
import org.auto1.rpg.menu.AllMenus;
import org.auto1.rpg.ascii.AsciiArtLoader;
import org.auto1.rpg.map.World;
import org.auto1.rpg.map.Coordinates;
import org.auto1.rpg.map.Location;
import org.auto1.rpg.exception.ConfigurationException;
import org.auto1.rpg.menu.ExplorationMenu;
import org.auto1.rpg.game.state.GameStateProvider;
import org.auto1.rpg.menu.item.ExplorationMenuItem;
import org.auto1.rpg.game.state.GameState;

import static org.auto1.rpg.map.legend.LegendBuilder.buildLegend;
import static org.auto1.rpg.map.legend.StatisticsBuilder.buildStatistics;
import static org.auto1.rpg.map.legend.WorldViewBuilder.buildWorldView;
import static org.auto1.rpg.exception.Victory.victory;
import static org.auto1.rpg.game.StaticMessages.GAME_SAVED;
import static org.auto1.rpg.game.StaticMessages.TRAVEL_INFO;

public class ExplorationManager {
    private static final Logger LOG = LogManager.getLogger(ExplorationManager.class);

    private final GameStateProvider gameStateProvider;
    private final ExplorationMenu explorationMenu;
    private final AllMenus allMenus;
    private final World world;
    private final Player player;


    public ExplorationManager(GameStateProvider gameStateProvider, AllMenus allMenus, World world, Player player) {
        this.gameStateProvider = gameStateProvider;
        this.explorationMenu = allMenus.explorationMenu();
        this.allMenus = allMenus;
        this.world = world;
        this.player = player;
    }

    public void startExploring() throws ConfigurationException {
        ExplorationMenuItem choice = explorationMenu.showMenu();

        while (ExplorationMenuItem.EXIT != choice) {
            LOG.debug("{} selected", choice);

            switch (choice) {
                case UP:
                    travel(player.up());
                    break;
                case DOWN:
                    travel(player.down());
                    break;
                case LEFT:
                    travel(player.left());
                    break;
                case RIGHT:
                    travel(player.right());
                    break;
                case COMMANDS:
                    explorationMenu.printAllOptions();
                    break;
                case MAP:
                    showMap();
                    break;
                case LEGEND:
                    showLegend();
                    break;
                case PLAYER:
                    explorationMenu.showMessage(player.toStringWithColors());
                    break;
                case STATS:
                    explorationMenu.showStatistics(buildStatistics(world, player));
                    break;
                case SAVE:
                    saveGame();
                    break;
                case EXIT:
                default:
                    throw new ShouldNeverHappen();
            }

            choice = explorationMenu.selectOption();
        }

        //TODO: ask about saving the game before leaving
    }

    void showMap() {
        LOG.traceEntry();
        explorationMenu.showMap(buildWorldView(world, player));
        LOG.traceExit();
    }

    //TODO: figure out how to use AOP without Spring AOP... logging entry and exit gets annoying...
    void showLegend() {
        LOG.traceEntry();
        explorationMenu.showMessage(buildLegend());
        LOG.traceExit();
    }

    void travel(Coordinates coordinates) {
        LOG.traceEntry();
        try {
            Location newLocation = world.getLocation(coordinates);
            if (newLocation.isAnyoneThere()) {
                interactWithNpc(newLocation);
            } else {
                moveToEmptySpace(newLocation);
            }
        } catch (ExplorationException e) {
            explorationMenu.showMessage(e.getMessage());
        }
        showMap();
        LOG.traceExit();
    }

    void interactWithNpc(Location newLocation) {
        LOG.traceEntry();
        NonPlayer npc = newLocation.getNpc();
        explorationMenu.showMessage(AsciiArtLoader.loadIfPossible(npc.getName()));
        explorationMenu.showMessage(npc.toStringWithColors());
        explorationMenu.showMessage(npc.greeting());
        if (npc.isEnemy()) {
            fight(newLocation);

            if (world.allEnemiesDead()){
                victory(world, player);
            }
        }
        LOG.traceExit();
    }

    void fight(Location newLocation) {
        new FightManager(allMenus, player, newLocation).fight();
    }

    void moveToEmptySpace(Location newLocation) {
        LOG.traceEntry();
        player.setCoordinates(newLocation.getCoordinates());
        explorationMenu.showMessage(TRAVEL_INFO + newLocation.desc());
        LOG.traceExit();
    }

    void saveGame() throws ConfigurationException {
        LOG.traceEntry();
        gameStateProvider.saveGame(new GameState(world, player));
        explorationMenu.showMessage(GAME_SAVED);
        LOG.traceExit();
    }

}
