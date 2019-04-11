package org.auto1.rpg.exception;

import static org.auto1.rpg.exception.ExceptionConstants.NEVER_HAPPEN;

public class ShouldNeverHappen extends GameException {
    public ShouldNeverHappen() {
        super(NEVER_HAPPEN);
    }
}
