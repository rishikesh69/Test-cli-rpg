package org.auto1.rpg.exception;

import static org.auto1.rpg.common.utils.ColorFormatter.bold;
import static org.auto1.rpg.common.utils.ColorFormatter.green;
import static org.auto1.rpg.common.utils.ColorFormatter.red;

public class ExceptionConstants {
	
	private ExceptionConstants(){
		// Util class shouldn't have public constructor
	}
	public static final String PROGRAM_BROKEN = "Abnormal Err Occured! Shutting everything down...";
	public static final String CANNOT_GO = "Cannot go to ";
	public static final String CANNOT_MOVE_BEYOND = "! You can't just move beyond this point!";
	public static final String START_NEW_GAME = "Failed to load game, please start a new one";
	public static final String NOT_NULL = " cannot be null";
	public static final String DEFEAT = bold(red("You died! Try harder next time!"));
	public static final String NEVER_HAPPEN = "Should never happen";
	public static final String VICTORY = bold(green(("Congratulations, %s, you WON! Enemies of Earth demolished and %s world is safe again.")));
	public static final String NOT_VALID = " is not valid";
	public static final String VALUE = " value ";
	public static final String PROVIDED = "Provided ";		
}
