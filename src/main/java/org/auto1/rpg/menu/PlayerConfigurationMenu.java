package org.auto1.rpg.menu;

import org.auto1.rpg.map.character.PlayerConfiguration;
import org.auto1.rpg.menu.QuestionsToPlayer;

public interface PlayerConfigurationMenu {
    /**
     * should return a valid player configuration
     * adapter is responsible for implementing initial validation
     */
    PlayerConfiguration askForPlayerConfig(QuestionsToPlayer questionsToPlayer);

    void showIntroduction(String introduction);

    void greetPlayer(String greetingMessage);
}
