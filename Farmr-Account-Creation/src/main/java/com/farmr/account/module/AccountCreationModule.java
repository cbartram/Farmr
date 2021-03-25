package com.farmr.account.module;

import com.farmr.account.service.AccountCreationService;
import com.farmr.account.service.AccountCredentialsService;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;

/**
 * AccountCreationModule - Guice Module which provides a new Account Creation Service class
 * to help create new RuneScape accounts.
 */
public class AccountCreationModule extends AbstractModule {

	@Provides
	@Singleton
	static AccountCreationService provideAccountCreationService() {
		return new AccountCreationService();
	}

	@Provides
	@Singleton
	static AccountCredentialsService provideCredentialService() {
		return new AccountCredentialsService();
	}

}