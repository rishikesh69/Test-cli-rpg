package org.auto1.rpg.common.utils;

import java.util.concurrent.ThreadLocalRandom;

public class CommonUtil {
	
	private CommonUtil(){
		//No ops
	}
	public static int randomIntInclusive(int maxInclusive) {
		return randomIntInclusive(0, maxInclusive);
	}

	public static int randomIntInclusive(int minInclusive, int maxInclusive) {
		return ThreadLocalRandom.current().nextInt(minInclusive, maxInclusive + 1);
	}

	public static int randomIntExclusive(int maxExclusive) {
		return randomIntExclusive(0, maxExclusive);
	}

	public static int randomIntExclusive(int minInclusive, int maxExclusive) {
		return ThreadLocalRandom.current().nextInt(minInclusive, maxExclusive);
	}

	public static boolean isNotBetweenZeroAndMaxExclusive(int tested, int maxExclusive) {
		return !isBetweenZeroAndMaxExclusive(tested, maxExclusive);
	}

	private static boolean isBetweenZeroAndMaxExclusive(int tested, int maxExclusive) {
		return isWithinRangeExclusive(tested, 0, maxExclusive);
	}

	private static boolean isWithinRangeExclusive(int tested, int minInclusive, int maxExclusive) {
		return tested >= minInclusive && tested < maxExclusive;
	}
	
	public static boolean isBlank(String key) {
		return null == key || key.trim().length() == 0;
	}
	
	public static String formatString(String value) {
		if(isBlank(value))
			return "";
		return value;
	}
}
