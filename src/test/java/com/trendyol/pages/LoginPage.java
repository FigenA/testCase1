
package com.trendyol.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends PageObject {

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(id = "login-email")

	WebElement email;

	@FindBy(id = "login-password-input")

	WebElement password;

	@FindBy(xpath = "//div[@class='lr-container']/div/form/button")

	WebElement loginButton;

	@FindBy(xpath = "//div[@class='lr-title']/H3")
	WebElement title;

	private void setEmail(String strEmail) {

		email.sendKeys(strEmail);
	}

	private void setPassword(String strPass) {

		password.sendKeys(strPass);
	}

	private void clickLogin() {

		loginButton.click();
	}

	public String getLoginTitle() {

		return title.getText();

	}

	public void loginToTrendyol(String strEmail, String strPasword) {
		this.setEmail(strEmail);
		this.setPassword(strPasword);
		this.clickLogin();
	}
}
