package org.auto1.rpg.game;

import static org.auto1.rpg.common.utils.ColorFormatter.blue;
import static org.auto1.rpg.common.utils.ColorFormatter.bold;
import static org.auto1.rpg.common.utils.ColorFormatter.boldMagenta;
import static org.auto1.rpg.common.utils.ColorFormatter.green;
import static org.auto1.rpg.common.utils.ColorFormatter.red;
import static org.auto1.rpg.common.utils.ColorFormatter.yellow;
import static org.auto1.rpg.map.legend.LegendBuilder.buildLegend;
import static org.auto1.rpg.map.legend.WorldViewBuilder.buildWorldView;
import static org.auto1.rpg.map.character.PlayerBaseStatistics.BASE_DMG;
import static org.auto1.rpg.map.character.PlayerBaseStatistics.BASE_DMG_VARIATION;
import static org.auto1.rpg.map.character.PlayerBaseStatistics.BASE_HP;

import org.auto1.rpg.map.character.Player;
import org.auto1.rpg.map.World;
import org.auto1.rpg.menu.QuestionsToPlayer;

/*
 * TODO: move all messages here, probably then divide them to groups and probably change to below
 * TODO: change to an outgoing port
 */
public interface StaticMessages {
	
	// TODO - move to external config

	String REALM_QUESTION = "In which realm would you like to begin your journey?";
	String REALM_CONFIRMATION_MESSAGE = boldMagenta("%s") + "? How exciting! Good luck!";

	// TODO: split this up, so the player can see the greeting, before getting
	// the map. Maybe something like:

	String TRAVEL_INFO = "You just traveled and you find that...";
	String GET_AWAY_FROM_THE_FIGHT = "Ufff, was close!";

	String GAME_SAVED = "Game successfully saved!";
	String GAME_LOADED = "Welcome again, traveler! Here, let me remind you where you are";

	final static String GREETING_MESSAGE = boldMagenta("Wow, %s")
			+ ", you have some amazing origin story. And those stats? Marvelous!\n" + "%s\n"
			+ "You will fit perfectly in the world of " + boldMagenta("%s") + "\n\n" + "Here, have a " + blue("map")
			+ ". You'll be able to use it whenever you feel lost!\n" + "%s\n" + "%s";

	static String greeting(Player player, World world) {
		return String.format(GREETING_MESSAGE, player.getName(), player.toStringWithColors(), world.getName(),
				buildWorldView(world, player), buildLegend());
	}

	static String realmConfigDone(World world) {
		return String.format(REALM_CONFIRMATION_MESSAGE, world.getName());
	}
}
