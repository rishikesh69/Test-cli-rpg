package org.auto1.rpg.map.character;

import org.auto1.rpg.common.utils.Color;
import org.auto1.rpg.map.LocationType;

public class Enemy extends NonPlayer {
    public Enemy(String name, String description, String greeting, int maxHp, int damage, int damageVariation) {
        super(name, description, greeting, maxHp, damage, damageVariation);
    }

    public Enemy(EnemyConfiguration conf) {
        this(conf.getName(), conf.getDescription(), conf.getGreeting(), conf.getMaxHp(), conf.getDamage(), conf.getDamageVariation());
    }

    @Override
    protected LocationType locationTypeSpecificForNpc() {
        return LocationType.ENEMY;
    }

    @Override
    protected Color greetingColor() {
        return Color.RED;
    }
}
