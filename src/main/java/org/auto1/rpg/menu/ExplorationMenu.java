package org.auto1.rpg.menu;

import org.auto1.rpg.menu.item.ExplorationMenuItem;

public interface ExplorationMenu extends BaseMenu<ExplorationMenuItem> {

    //TODO: change to some DTO, so client side may choose the rendering option
    void showMap(String map);

    //TODO: change to some DTO, so client side may choose the rendering option
    void showStatistics(String statistics);
}
