package com.farmr.service;

import com.farmr.model.RuneScapeCredentials;
import com.google.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

/**
 * AccountCreationService - Service class which handles the creation of new RuneScape accounts.
 */
@Slf4j
public class AccountCreationService {

	private static final String RUNESCAPE_HOST="https://secure.runescape.com";
	private static final String RUNESCAPE_CREATE_ACCOUNT_PATH = "/m=account-creation/create_account?theme=oldschool";
	private final WebDriver driver;

	@Inject
	private AccountCredentialsService credentialsService;

	public AccountCreationService() {
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
		this.driver = new ChromeDriver();
		Capabilities cap = ((ChromeDriver) driver).getCapabilities();
		log.info("Chrome Version: {}", cap.getVersion());

		// TODO if chromedriver doesnt exist download it to the correct location
		// If it exists continue: example url https://chromedriver.storage.googleapis.com/90.0.4430.24/chromedriver_mac64.zip

	}

	public RuneScapeCredentials create() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5).toMillis());
		final RuneScapeCredentials credentials = credentialsService.generateCredentials();

		log.info("Creating new RuneScape Account with email: {} and password: {}", credentials.getEmail(), credentials.getPassword());
		try {
			driver.get(RUNESCAPE_HOST + RUNESCAPE_CREATE_ACCOUNT_PATH);
			driver.findElement(By.id("create-email")).sendKeys(credentials.getEmail());
			driver.findElement(By.id("create-password")).sendKeys(credentials.getPassword());
			driver.findElement(By.className("m-date-entry__day-field")).sendKeys(credentials.getDay());
			driver.findElement(By.className("m-date-entry__month-field")).sendKeys(credentials.getMonth());
			driver.findElement(By.className("m-date-entry__year-field")).sendKeys(credentials.getYear());
			driver.findElement(By.id("create-submit")).click();

			WebElement firstResult = wait.until(presenceOfElementLocated(By.cssSelector("h3")));
			log.info(firstResult.getAttribute("textContent"));
		} finally {
			driver.quit();
		}

		return credentials;
	}
}
