package org.auto1.rpg.map.character;

import java.io.Serializable;

import org.auto1.rpg.common.utils.ToStringBuilder;

import static org.auto1.rpg.map.constants.MapMessageConstants.EXP_TO_FIRST_LEVEL_UP;
import static org.auto1.rpg.map.constants.MapMessageConstants.NEXT_LEVEL_EXP_MULTIPLIER;

public class Experience implements Serializable {
    private int level;
    private int currentExp;
    private int lastLevelUpExp;

    public Experience() {
        this.currentExp = 0;
        this.level = 1;
    }

    public int getCurrentExp() {
        return currentExp;
    }

    public int getLevel() {
        return level;
    }

    public ExperienceStatus addKillReward(int expReward) {
        currentExp += expReward;
        int startingLevel = level;
        while (currentExp >= getExpRequiredToLevelUp()) {
            levelUp();
        }

        return ExperienceStatus.fromLevelDiff(level - startingLevel);

    }

    int getExpRequiredToLevelUp() {
        if (1 == level) {
            return EXP_TO_FIRST_LEVEL_UP;
        } else {
            return (int) (lastLevelUpExp + lastLevelUpExp * NEXT_LEVEL_EXP_MULTIPLIER);
        }
    }

    private void levelUp() {
        lastLevelUpExp = getExpRequiredToLevelUp();
        level++;
    }

    @Override
    public String toString() {
        return ToStringBuilder.defaultBuilderWithoutBrackets(this)
                .append("level", level)
                .append("currentExp", currentExp + "/" + getExpRequiredToLevelUp())
                .build();
    }
}
