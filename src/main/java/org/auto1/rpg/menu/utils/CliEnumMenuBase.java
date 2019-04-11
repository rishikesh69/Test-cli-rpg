package org.auto1.rpg.menu.utils;

import java.util.Arrays;

import org.auto1.rpg.console.io.InputParser;
import org.auto1.rpg.console.io.OutputWriter;

public class CliEnumMenuBase<T extends Enum> extends CliMenuBase<T> {
	protected CliEnumMenuBase(InputParser inputParser, OutputWriter outputWriter, T[] menuItems) {
		super(inputParser, outputWriter, Arrays.asList(menuItems));
	}
}
