package org.auto1.rpg.map.character;

import static org.auto1.rpg.map.constants.MapMessageConstants.ENEMY_DEFEATED;
import static org.auto1.rpg.map.constants.MapMessageConstants.LEVEL_UP;

public enum ExperienceStatus {
    DIDNT_LEVEL_UP(ENEMY_DEFEATED),
    LEVELED_UP(ENEMY_DEFEATED + LEVEL_UP),
    DOUBLE_LEVELED_UP(ENEMY_DEFEATED + LEVEL_UP + "Twice!"),
    TRIPLE_LEVELED_UP(ENEMY_DEFEATED + LEVEL_UP + "Three times!");

    private final String desc;

    private ExperienceStatus(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return desc;
    }

    public static ExperienceStatus fromLevelDiff(int levelDiff) {
        for (ExperienceStatus experienceStatus : ExperienceStatus.values()) {
            if (experienceStatus.ordinal() == levelDiff) {
                return experienceStatus;
            }
        }

        return DIDNT_LEVEL_UP;
    }
}
