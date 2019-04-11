package org.auto1.rpg.exception;

import static org.auto1.rpg.exception.ExceptionConstants.PROGRAM_BROKEN;

/**
 *  Common Exception being used by ApplicationContext
 */
public class BeanException extends RuntimeException {

	public BeanException() {
		super(PROGRAM_BROKEN);
	}

	public BeanException(Exception e) {
		super(PROGRAM_BROKEN, e);
	}
}
