package org.auto1.rpg.map.character;

import java.io.Serializable;

import org.auto1.rpg.common.utils.ToStringBuilder;

import static org.auto1.rpg.common.utils.Color.GREEN;
import static org.auto1.rpg.common.utils.Color.RED;
import static org.auto1.rpg.common.utils.Color.YELLOW;
import static org.auto1.rpg.common.utils.CommonUtil.randomIntInclusive;
import static org.auto1.rpg.common.constants.CommonMessages.FIGHT_DAMAGE_VARIATION_MULTIPLIER;

/**
 * This class contains basic characteristics of a player/Enemy
 * 
 * @author RISHIKESH
 *
 */
public class Character implements Serializable {
	protected final String name;
	protected final String description;

	protected int maxHp;
	protected int currentHp;
	protected int damage;
	protected int damageVariation;

	protected boolean isAlive;

	public Character(String name, String description, int maxHp, int damage, int damageVariation) {
		this.name = name;
		this.description = description;

		this.maxHp = maxHp;
		this.currentHp = maxHp;
		this.damage = damage;
		this.damageVariation = damageVariation;

		this.isAlive = true;
	}

	/**
	 * This method deals with damage during attack to enemy
	 *
	 * @param otherCharacter
	 *            - enemy
	 * @return damage dealt
	 */
	public int attack(Character otherCharacter) {
		return otherCharacter.receiveDamage(calculateDamageToDeal());
	}

	/**
	 * Calculates damage done to the player by enemy
	 * 
	 * @param damage
	 * @return damage received
	 */
	private int receiveDamage(int damage) {
		int damageReceived = calculateDamageReceived(damage);
		currentHp -= damageReceived;
		if (currentHp <= 0) {
			die();
		}

		return damageReceived;
	}

	/**
	 * @return
	 */
	protected int calculateDamageToDeal() {
		return randomIntInclusive(damage, damage + damageVariation * FIGHT_DAMAGE_VARIATION_MULTIPLIER);
	}

	protected int calculateDamageReceived(int damage) {
		return damage;
	}

	public void die() {
		isAlive = false;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public boolean isAlive() {
		return isAlive;
	}

	public boolean isDead() {
		return !isAlive;
	}

	public String currentStatus() {
		return "\n" + getClass().getSimpleName() + " current status is: \n" + toStringWithColors();
	}

	@Override
	public String toString() {
		return toStringCommon().build();
	}

	public String toStringWithColors() {
		return toStringCommon().build(true);
	}

	private ToStringBuilder toStringCommon() {
		return ToStringBuilder.fieldsWithNewlinesAndTabs(this).append("name", name, YELLOW)
				.append("description", description).append("health", currentHp + "/" + maxHp, GREEN)
				.append("damage", damage + "-" + (damage + damageVariation * FIGHT_DAMAGE_VARIATION_MULTIPLIER), RED);
	}
}
