package com.farmr.module;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;

public class FarmrModule extends AbstractModule {

	@Provides
	@Singleton
	static String provideString() {
		return "hi";
	}
}
