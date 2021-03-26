package com.farmr.module;

import com.farmr.service.AccountCreationService;
import com.farmr.service.AccountCredentialsService;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;

public class FarmrModule extends AbstractModule {
	@Provides
	@Singleton
	static AccountCreationService provideAccountCreationService() {
		return new AccountCreationService();
	}

	@Provides
	@Singleton
	static AccountCredentialsService provideAccountCredentialsService() {
		return new AccountCredentialsService();
	}
}
