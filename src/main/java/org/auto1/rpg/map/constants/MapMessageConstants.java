package org.auto1.rpg.map.constants;

import static org.auto1.rpg.common.utils.ColorFormatter.blue;
import static org.auto1.rpg.common.utils.ColorFormatter.boldMagenta;
import static org.auto1.rpg.map.legend.LegendBuilder.buildLegend;
import static org.auto1.rpg.map.legend.WorldViewBuilder.buildWorldView;

import org.auto1.rpg.map.World;
import org.auto1.rpg.map.character.Player;

public class MapMessageConstants {
	
	private MapMessageConstants(){
		// No Ops
	}
	public static final String NAME = "name";
	public static final String DESCRIPTION = "description";
	public static final String GREETINGS = "greeting";
	public static final String MAX_HP = "maxHp";
	public static final String DAMAGE = "damage";
	public static final String DAMAGE_VARIATION = "damageVariation";
	public static final String LEVEL_UP = "You earned enough xp to " + boldMagenta("level up") + " your hero! ";
	public static final String ENEMY_DEFEATED = "Defeating this enemy gave you %d xp. ";
	public static final int EXP_TO_FIRST_LEVEL_UP = 100;
	public static final float NEXT_LEVEL_EXP_MULTIPLIER = 1.1f;
}
