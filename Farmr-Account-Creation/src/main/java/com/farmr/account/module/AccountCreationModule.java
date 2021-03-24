package com.farmr.account.module;

import com.farmr.account.service.AccountCreationService;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;

/**
 * AccountCreationModule - Guice Module which provides a new Account Creation Service class
 * to help create new RuneScape accounts.
 */
public class AccountCreationModule extends AbstractModule {

	@Provides
	static AccountCreationService provideCount() {
		return new AccountCreationService();
	}
}