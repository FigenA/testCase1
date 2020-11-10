
package com.trendyol.conf;

import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;

public class Driver {
	protected static WebDriver driver;
	protected static WebDriverWait wait;
	protected static BrowserTypeEnum browserType = BrowserTypeEnum.chrome;

	public Driver(BrowserTypeEnum browser) {
		browserType = browser;
	}

	@BeforeClass
	public void getDriver() {
		switch (browserType) {
			case chrome:
				System.setProperty("webdriver.chrome.driver",
						"src/main/resources/drivers/chromedriver/chromedriver.exe");

				driver = new ChromeDriver();
				break;
			case firefox:
				System.setProperty("webdriver.gecko.driver",
						"src/main/resources/drivers/firefoxdriver/geckodriver.exe");

				driver = new FirefoxDriver();
				break;
			case internetexplorer:
				System.setProperty("webdriver.ie.driver",
						"src/main/resources/drivers/ieDriver/IEDriverServer");

				driver = new InternetExplorerDriver();
				break;
			default:
				System.setProperty("webdriver.chrome.driver",
						"src/main/resources/drivers/chromedriver/chromedriver.exe");

				driver = new ChromeDriver();
				break;
		}
		driver.get("https://www.trendyol.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, 20);
	}

	@AfterClass
	public static void tearDown() {
		driver.close();
		driver.quit();
	}

}
