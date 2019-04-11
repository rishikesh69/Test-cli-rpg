package org.auto1.rpg.exception;

import static org.auto1.rpg.exception.ExceptionConstants.NOT_VALID;
import static org.auto1.rpg.exception.ExceptionConstants.VALUE;
import static org.auto1.rpg.exception.ExceptionConstants.PROVIDED;;

public class WrongParameterException extends GameException {
	public WrongParameterException(String valueType, String value) {
		super(PROVIDED + valueType + VALUE + value + NOT_VALID);
	}
}
