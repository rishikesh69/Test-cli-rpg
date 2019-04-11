package org.auto1.rpg.menu;

import static org.auto1.rpg.common.utils.ColorFormatter.yellow;
import static org.auto1.rpg.map.character.PlayerBaseStatistics.BASE_DMG;
import static org.auto1.rpg.map.character.PlayerBaseStatistics.BASE_DMG_VARIATION;
import static org.auto1.rpg.map.character.PlayerBaseStatistics.BASE_HP;
import static org.auto1.rpg.common.constants.CommonMessages.FIGHT_DAMAGE_VARIATION_MULTIPLIER;

public class QuestionsToPlayer {
	
	private final static int MAX_BONUS_POINTS = 5;
	private final static String NAME_QUESTION = "What's your name?";
	private final static String DESC_QUESTION = "What's your story?";
	private final static String BONUS_STATS_DESC = "\nWhat are your skills?\n" +
            "you may distribute " + yellow(MAX_BONUS_POINTS + " skill points") + " to 3 attributes: bonus hp, bonus dmg and bonus dmg variation\n" +
            "base statistics are: " + yellow("100 hp and 10-1" + FIGHT_DAMAGE_VARIATION_MULTIPLIER + " dmg")
            + " (10 base damage and 1 damage variation. every variation point gives 0-" + FIGHT_DAMAGE_VARIATION_MULTIPLIER + " dmg)";
	
	private final static String BONUS_STATS_HP_QUESTION = "What is your " + yellow("bonus HP") + "? (base = " + BASE_HP + ")";
	private final static String BONUS_STATS_DMG_QUESTION = "What is your " + yellow("bonus damage") + "? (base = " + BASE_DMG + ")";
	private final static String BONUS_STATS_DMG_VAR_QUESTION = "What is your " + yellow("bonus damage variation") + "? (base = " + BASE_DMG_VARIATION + ")";

    private final String nameQuestion;
    private final String descQuestion;
    private final String bonusStatsDescription;
    private final String bonusStatsBonusHpQuestion;
    private final String bonusStatsBonusDanageQuestion;
    private final String bonusStatsBonusDanageVariationQuestion;
    private final int maxBonusPoints;

    private QuestionsToPlayer(String nameQuestion, String descQuestion, String bonusStatsDescription, String bonusStatsBonusHpQuestion,
                              String bonusStatsBonusDanageQuestion, String bonusStatsBonusDanageVariationQuestion, int maxBonusPoints) {
        this.nameQuestion = nameQuestion;
        this.descQuestion = descQuestion;
        this.bonusStatsDescription = bonusStatsDescription;
        this.bonusStatsBonusHpQuestion = bonusStatsBonusHpQuestion;
        this.bonusStatsBonusDanageQuestion = bonusStatsBonusDanageQuestion;
        this.bonusStatsBonusDanageVariationQuestion = bonusStatsBonusDanageVariationQuestion;
        this.maxBonusPoints = maxBonusPoints;
    }

    public String getNameQuestion() {
        return nameQuestion;
    }

    public String getDescQuestion() {
        return descQuestion;
    }

    public String getBonusStatsDescription() {
        return bonusStatsDescription;
    }

    public String getBonusStatsBonusHpQuestion() {
        return bonusStatsBonusHpQuestion;
    }

    public String getBonusStatsBonusDanageQuestion() {
        return bonusStatsBonusDanageQuestion;
    }

    public String getBonusStatsBonusDanageVariationQuestion() {
        return bonusStatsBonusDanageVariationQuestion;
    }

    public int getMaxBonusPoints() {
        return maxBonusPoints;
    }

    public static QuestionsToPlayerBuilder builder() {
        return new QuestionsToPlayerBuilder();
    }
    
    public static QuestionsToPlayer buildQuestion() {
        return QuestionsToPlayer.builder()
                .withMaxBonusPoints(MAX_BONUS_POINTS)
                .withNameQuestion(NAME_QUESTION)
                .withDescQuestion(DESC_QUESTION)
                .withBonusStatsDescription(BONUS_STATS_DESC)
                .withBonusStatsBonusHpQuestion(BONUS_STATS_HP_QUESTION)
                .withBonusStatsBonusDamageQuestion(BONUS_STATS_DMG_QUESTION)
                .withBonusStatsBonusDamageVariationQuestion(BONUS_STATS_DMG_VAR_QUESTION)
                .build();
    }

    public static class QuestionsToPlayerBuilder {
        private String nameQuestion;
        private String descQuestion;
        private String bonusStatsDescription;
        private String bonusStatsBonusHpQuestion;
        private String bonusStatsBonusDanageQuestion;
        private String bonusStatsBonusDanageVariationQuestion;
        private int maxBonusPoints;

        private QuestionsToPlayerBuilder() {
        }

        public QuestionsToPlayerBuilder withNameQuestion(String nameQuestion) {
            this.nameQuestion = nameQuestion;
            return this;
        }

        public QuestionsToPlayerBuilder withDescQuestion(String descQuestion) {
            this.descQuestion = descQuestion;
            return this;
        }

        public QuestionsToPlayerBuilder withBonusStatsDescription(String bonusStatsDescription) {
            this.bonusStatsDescription = bonusStatsDescription;
            return this;
        }

        public QuestionsToPlayerBuilder withBonusStatsBonusHpQuestion(String bonusStatsBonusHpQuestion) {
            this.bonusStatsBonusHpQuestion = bonusStatsBonusHpQuestion;
            return this;
        }

        public QuestionsToPlayerBuilder withBonusStatsBonusDamageQuestion(String bonusStatsBonusDanageQuestion) {
            this.bonusStatsBonusDanageQuestion = bonusStatsBonusDanageQuestion;
            return this;
        }

        public QuestionsToPlayerBuilder withBonusStatsBonusDamageVariationQuestion(String bonusStatsBonusDanageVariationQuestion) {
            this.bonusStatsBonusDanageVariationQuestion = bonusStatsBonusDanageVariationQuestion;
            return this;
        }

        public QuestionsToPlayerBuilder withMaxBonusPoints(int maxBonusPoints) {
            this.maxBonusPoints = maxBonusPoints;
            return this;
        }

        public QuestionsToPlayer build() {
            return new QuestionsToPlayer(nameQuestion, descQuestion, bonusStatsDescription, bonusStatsBonusHpQuestion,
                    bonusStatsBonusDanageQuestion, bonusStatsBonusDanageVariationQuestion, maxBonusPoints);
        }
    }
}
