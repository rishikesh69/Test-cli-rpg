package org.auto1.rpg.console.ui;

import org.auto1.rpg.exception.PlayerValidationException;
import org.auto1.rpg.menu.PlayerConfigurationMenu;
import org.auto1.rpg.map.character.PlayerConfiguration;
import org.auto1.rpg.console.io.InputParser;
import org.auto1.rpg.console.io.OutputWriter;
import org.auto1.rpg.menu.QuestionsToPlayer;
import org.auto1.rpg.menu.utils.CliMenuBase;

public class CliPlayerConfigurationMenu extends CliMenuBase<String> implements PlayerConfigurationMenu {

    public CliPlayerConfigurationMenu(InputParser inputParser, OutputWriter outputWriter) {
        super(inputParser, outputWriter);
    }

    @Override
    public PlayerConfiguration askForPlayerConfig(QuestionsToPlayer questions) {
        PlayerConfiguration playerConfiguration = null;

        PlayerConfiguration.PlayerConfigurationBuilder playerConfigurationBuilder = PlayerConfiguration.builder(questions.getMaxBonusPoints());
        playerConfigurationBuilder.withName(inputParser.readUserInputAsString(questions.getNameQuestion()));
        playerConfigurationBuilder.withDesc(inputParser.readUserInputAsString(questions.getDescQuestion()));

        do {
            try {
                showMessage(questions.getBonusStatsDescription());
                playerConfigurationBuilder.withHpBonus(inputParser.tryReadingInputAsInt(questions.getBonusStatsBonusHpQuestion()));
                playerConfigurationBuilder.withDamageBonus(inputParser.tryReadingInputAsInt(questions.getBonusStatsBonusDanageQuestion()));
                playerConfigurationBuilder.withDamageVariationBonus(inputParser.tryReadingInputAsInt(questions.getBonusStatsBonusDanageVariationQuestion()));

                playerConfiguration = playerConfigurationBuilder.build();
            } catch (PlayerValidationException e) {
                showMessage(e.getMessage());
            }catch (Exception e) {
                showMessage(e.getMessage());
            }
        } while (playerConfiguration == null);

        return playerConfiguration;
    }

    @Override
    public void showIntroduction(String introduction) {
        outputWriter.showMessageWithSpace(introduction);
    }

    @Override
    public void greetPlayer(String greetingMessage) {
        outputWriter.showMessageWithSpace(greetingMessage);
    }
}
