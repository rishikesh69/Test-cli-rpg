package org.auto1.rpg.map.legend;

import org.auto1.rpg.map.character.Player;
import org.auto1.rpg.common.utils.ToStringBuilder;
import org.auto1.rpg.map.World;

public class StatisticsBuilder extends GameInfoBuilder<StatisticsBuilder> {
    public static String buildStatistics(World world, Player player) {
        return statistics().withWorld(world).withPlayer(player).build();
    }

    public static StatisticsBuilder statistics() {
        return new StatisticsBuilder();
    }

    @Override
    protected StatisticsBuilder that() {
        return this;
    }

    @Override
    protected String buildInner() {
        //TODO
        return ToStringBuilder.fieldsWithNewlinesAndTabs(this)
                .append("enemies left", world.aliveEnemiesLeft() + "/" + world.getEnemies().size())
                .append("", player.toStringWithColors())
                .build();
    }
}
