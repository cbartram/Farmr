package com.farmr.account.model;

import lombok.Data;

@Data
public class RuneScapeCredentials {
	private String email;
	private String password;
	private String day;
	private String month;
	private String year;
}
