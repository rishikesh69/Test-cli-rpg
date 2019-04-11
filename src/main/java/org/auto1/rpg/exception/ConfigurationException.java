package org.auto1.rpg.exception;

/**
 *  Game configuration Exception
 */
public class ConfigurationException extends Exception {
    public ConfigurationException(String message) {
        super(message);
    }

    public ConfigurationException(String message, Throwable cause) {
        super(message, cause);
    }
}
