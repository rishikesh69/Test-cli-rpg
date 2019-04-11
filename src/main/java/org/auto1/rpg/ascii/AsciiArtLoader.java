package org.auto1.rpg.ascii;

import org.auto1.rpg.common.io.InternalIO;

public class AsciiArtLoader {
    public static final String ASCII_ART_FOLDER = "ascii_art";

    public static String loadIfPossible(String characterName) {
        try {
            return InternalIO.readAsString(ASCII_ART_FOLDER, characterName);
        } catch (NullPointerException e) {
            return "";
        }
    }

    private AsciiArtLoader() {
    }
}
