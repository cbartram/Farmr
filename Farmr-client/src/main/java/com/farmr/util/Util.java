package com.farmr.util;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

@Slf4j
public class Util {
	private static final String IP_URL = "http://checkip.amazonaws.com";

	public static String fetchIp() {
		try {
			URL ipUrl = new URL(IP_URL);
			BufferedReader in = new BufferedReader(new InputStreamReader(
					ipUrl.openStream()));
			return in.readLine();
		} catch(MalformedURLException e) {
			log.error("The URL provided to the Ip Service is invalid or malformed. ", e);
		} catch(IOException e) {
			log.error("IOException thrown while attempting to fetch Ip address from url: {}", IP_URL, e);
		}
		return null;
	}
}
