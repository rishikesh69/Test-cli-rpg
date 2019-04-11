package org.auto1.rpg.exception;

import org.auto1.rpg.map.character.Player;
import org.auto1.rpg.map.World;

import static org.auto1.rpg.exception.ExceptionConstants.VICTORY;

/**
 * however strange that looks... its the easiest way to end all those pesky loops...
 */
public class Victory extends GameException {
    public Victory(String worldName, String playerName) {
        super(String.format(VICTORY, playerName, worldName));
    }

    public static void victory(World world, Player player) {
        throw new Victory(world.getName(), player.getName());
    }
}
