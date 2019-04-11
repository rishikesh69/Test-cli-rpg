package org.auto1.rpg.exception;

import org.auto1.rpg.exception.ConfigurationException;

import static org.auto1.rpg.common.utils.ColorFormatter.bold;
import static org.auto1.rpg.common.utils.ColorFormatter.red;
import static org.auto1.rpg.exception.ExceptionConstants.START_NEW_GAME;

public class LoadGameException extends ConfigurationException {
	public LoadGameException(Throwable cause) {
		super(bold(red(START_NEW_GAME)), cause);
	}
}