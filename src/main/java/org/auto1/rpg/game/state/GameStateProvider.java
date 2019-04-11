package org.auto1.rpg.game.state;

import org.auto1.rpg.exception.ConfigurationException;
import org.auto1.rpg.game.state.GameState;

/**
 * TODO: extend to support loading/saving multiple game states
 */
public interface GameStateProvider extends ResourceProvider<GameState> {
    default GameState loadGame() throws ConfigurationException {
        return loadOne();
    }

    default void saveGame(GameState gameState) throws ConfigurationException {
        saveOne(gameState);
    }
}
