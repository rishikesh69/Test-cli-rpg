package org.auto1.rpg.exception;

import static org.auto1.rpg.exception.ExceptionConstants.DEFEAT;

public class PlayerDied extends GameException {
	public PlayerDied() {
		super(DEFEAT);
	}
}
