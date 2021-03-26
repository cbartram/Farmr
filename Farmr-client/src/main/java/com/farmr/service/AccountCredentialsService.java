package com.farmr.service;

import com.farmr.model.RuneScapeCredentials;
import com.farmr.util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class AccountCredentialsService {
	private static final String LOWER = "abcdefghijklmnopqrstuvwxyz";
	private static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static final String DIGITS = "0123456789";
	private static final Random random = new Random(System.nanoTime());
	private static final String[] domains = new String[] {
			"@bsmitao.com",
			"@dwgtcm.com",
			"@irahada.com",
			"@kindbest.com",
			"@gmail.com",
			"@yahoo.com",
			"@msn.com",
	};


	public RuneScapeCredentials generateCredentials() {
		String[] dob = new String[3];
		RuneScapeCredentials credentials = new RuneScapeCredentials();
		dob[0] = String.valueOf(Util.randomNumberBetween(1, 12));
		dob[1] = String.valueOf(Util.randomNumberBetween(1, 28));
		dob[2] = String.valueOf(Util.randomNumberBetween(1960, 2010));
		credentials.setMonth(dob[0]);
		credentials.setDay(dob[1]);
		credentials.setYear(dob[2]);
		credentials.setEmail(this.generateEmailAddress());
		credentials.setPassword(this.generatePassword(random.nextInt(15)));
		return credentials;
	}

	private String generateEmailAddress() {
		final String domain = domains[random.nextInt(domains.length)];
		final String emailPrefix = Util.generateRandomWords(1)[0];
		return emailPrefix + domain;
	}

	/**
	 * This method will generate a password depending the use* properties you
	 * define. It will use the categories with a probability. It is not sure
	 * that all of the defined categories will be used.
	 *
	 * @param length the length of the password you would like to generate.
	 * @return a password that uses the categories you define when constructing
	 * the object with a probability.
	 */
	private String generatePassword(int length) {
		if (length <= 0) {
			return "";
		}

		StringBuilder password = new StringBuilder(length);
		Random random = new Random(System.nanoTime());

		// Collect the categories to use.
		List<String> charCategories = new ArrayList<>(4);
		charCategories.add(LOWER);
		charCategories.add(UPPER);
		charCategories.add(DIGITS);

		// Build the password.
		for (int i = 0; i < length; i++) {
			String charCategory = charCategories.get(random.nextInt(charCategories.size()));
			int position = random.nextInt(charCategory.length());
			password.append(charCategory.charAt(position));
		}
		return new String(password);
	}
}
