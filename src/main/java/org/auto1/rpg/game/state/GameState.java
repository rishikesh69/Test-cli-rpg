package org.auto1.rpg.game.state;

import org.auto1.rpg.map.character.Player;
import org.auto1.rpg.map.World;

import java.io.Serializable;

public class GameState implements Serializable {
    private final World world;
    private final Player player;

    public GameState(World world, Player player) {
        this.world = world;
        this.player = player;
    }

    public World getWorld() {
        return world;
    }

    public Player getPlayer() {
        return player;
    }
}
