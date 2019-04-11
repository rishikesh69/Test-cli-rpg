package org.auto1.rpg.exception;

import static org.auto1.rpg.exception.ExceptionConstants.NOT_NULL;

public class NullParameterException extends GameException {
	public NullParameterException(String valueType) {
		super(valueType + NOT_NULL);
	}
}
