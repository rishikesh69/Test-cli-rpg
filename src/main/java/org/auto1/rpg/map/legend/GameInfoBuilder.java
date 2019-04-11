package org.auto1.rpg.map.legend;

import org.auto1.rpg.map.character.Player;
import org.auto1.rpg.map.World;

abstract class GameInfoBuilder<T extends GameInfoBuilder<T>> extends OutputBuilderBase {
    
	protected World world;
    protected Player player;
    
    protected final static String ERROR_OCCURED = "Cannot build request information, an error occurred";
    
    public T withWorld(World world) {
        this.world = world;
        return that();
    }

    public T withPlayer(Player player) {
        this.player = player;
        return that();
    }

    protected abstract T that();

    public String build() {
        if (null == world) {
            return errorOccurred();
        }

        return buildInner();
    }

    protected String errorOccurred() {
        return ERROR_OCCURED;
    }

    //TODO: rename it to something meaningful
    protected abstract String buildInner();
}
