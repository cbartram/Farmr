package com.farmr.account.util;

import java.util.Random;

public class Util {

	/**
	 * Generates a random collection of characters between A-Z
	 * @param numberOfWords int The number of words to generate
	 * @return String[] of random words
	 */
	public static String[] generateRandomWords(int numberOfWords) {
		String[] randomStrings = new String[numberOfWords];
		Random random = new Random();
		for(int i = 0; i < numberOfWords; i++)
		{
			char[] word = new char[random.nextInt(8)+5]; // words of length 3 through 10. (1 and 2 letter words are boring.)
			for(int j = 0; j < word.length; j++)
			{
				word[j] = (char)('a' + random.nextInt(26));
			}
			randomStrings[i] = new String(word);
		}
		return randomStrings;
	}


	/**
	 * Generates a random number between the specified minimum and maximum
	 * @param min int Minimum number to generate
	 * @param max int Maximum number to generate
	 * @return int random number between min and max as an integer
	 */
	public static int randomNumberBetween(int min, int max) {
		Random random = new Random();
		return random.nextInt(max - min) + min;
	}
}
