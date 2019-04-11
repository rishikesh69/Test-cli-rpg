package org.auto1.rpg.exception;

import static org.auto1.rpg.exception.ExceptionConstants.CANNOT_GO;
import static org.auto1.rpg.exception.ExceptionConstants.CANNOT_MOVE_BEYOND;

public class ExplorationException extends Exception {
	public static void cannotGo(int index) throws ExplorationException {
		throw new ExplorationException(CANNOT_GO + index + CANNOT_MOVE_BEYOND);
	}

	public ExplorationException(String message) {
		super(message);
	}
}
