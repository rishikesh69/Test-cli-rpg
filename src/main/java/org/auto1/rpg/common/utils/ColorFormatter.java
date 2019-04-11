package org.auto1.rpg.common.utils;

import static org.auto1.rpg.common.utils.Color.BLUE;
import static org.auto1.rpg.common.utils.Color.BOLD;
import static org.auto1.rpg.common.utils.Color.CYAN;
import static org.auto1.rpg.common.utils.Color.GREEN;
import static org.auto1.rpg.common.utils.Color.MAGENTA;
import static org.auto1.rpg.common.utils.Color.RED;
import static org.auto1.rpg.common.utils.Color.UNDERLINE;
import static org.auto1.rpg.common.utils.Color.YELLOW;

public class ColorFormatter {
	
	private ColorFormatter(){
		//No ops
	}
    public static String red(String input) {
        return RED.format(input);
    }

    public static String blue(String input) {
        return BLUE.format(input);
    }

    public static String green(String input) {
        return GREEN.format(input);
    }

    public static String yellow(String input) {
        return YELLOW.format(input);
    }

    public static String cyan(String input) {
        return CYAN.format(input);
    }

    public static String bold(String input) {
        return BOLD.format(input);
    }

    public static String boldMagenta(String input) {
        return bold(MAGENTA.format(input));
    }

    public static String underlinedBlue(String input) {
        return UNDERLINE.format(BLUE.format(input));
    }


}
