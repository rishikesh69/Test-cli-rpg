package org.auto1.rpg.menu;

public interface BaseMenu<T> {
    void showMessage(String message);

    void printAllOptions(String message);

    void printAllOptions();

    T selectOption();

    T showMenu();
}
