package org.auto1.rpg.map.character;

import java.io.Serializable;

import org.auto1.rpg.common.utils.ToStringBuilder;

import static org.auto1.rpg.map.constants.MapMessageConstants.*;

/**
 * This class contains Enemy config
 * 
 * @author RISHIKESH
 *
 */
public class EnemyConfiguration implements Serializable {
	private final String name;
	private final String description;
	private final String greeting;
	private final int maxHp;
	private final int damage;
	private final int damageVariation;

	public EnemyConfiguration(String name, String description, String greeting, int maxHp, int damage,
			int damageVariation) {
		this.name = name;
		this.description = description;
		this.greeting = greeting;
		this.maxHp = maxHp;
		this.damage = damage;
		this.damageVariation = damageVariation;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public String getGreeting() {
		return greeting;
	}

	public int getDamage() {
		return damage;
	}

	public int getMaxHp() {
		return maxHp;
	}

	public int getDamageVariation() {
		return damageVariation;
	}

	@Override
	public String toString() {
		return ToStringBuilder.defaultBuilder(this).append(NAME, name).append(DESCRIPTION, description)
				.append(GREETINGS, greeting).append(MAX_HP, maxHp).append(DAMAGE, damage)
				.append(DAMAGE_VARIATION, damageVariation).build();
	}
}
