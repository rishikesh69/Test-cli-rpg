package org.auto1.rpg;

import org.auto1.rpg.console.launcher.CliGameLauncher;

/**
 * Launcher Main class for the Role Playing Game.
 * 
 * @author RISHIKESH
 */
public class Launcher {
	private Launcher() {
		// No ops
	}

	public static void main(String[] args) {
		CliGameLauncher.startGame();
	}
}
