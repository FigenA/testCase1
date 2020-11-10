
package com.trendyol.pages;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.trendyol.conf.LogHelper;

public class HomePage extends PageObject {
	private LogHelper logHelper;

	public HomePage(WebDriver driver) {
		super(driver);
		this.logHelper = new LogHelper();
	}

	@FindBy(id = "accountBtn")
	WebElement accountButton;

	@FindBy(xpath = "//div[text()='Giriş Yap']")
	WebElement loginButton;

	@FindBy(className = "user-name")
	WebElement userName;

	@FindAll(@FindBy(xpath = "//ul[@class='main-nav']/li"))
	List<WebElement> allTabs;

	private void clickLoginButton() {
		loginButton.click();
	}

	public LoginPage login() throws InterruptedException {

		boolean isPopupExist = driver.findElement(By.className("homepage-popup")).isDisplayed();
		if (isPopupExist) {
			driver.findElement(By.xpath("//a[contains(@class, 'fancybox-close')]")).click();
		}

		Actions actions = new Actions(driver);
		actions.moveToElement(accountButton).click().perform();
		clickLoginButton();
		return new LoginPage(driver);
	}

	public boolean checkUsername(String username) throws InterruptedException, IOException {

		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("circled-slider")));
		PageFactory.initElements(driver, HomePage.class);
		Actions actions = new Actions(driver);
		actions.moveToElement(accountButton).build().perform();
		WebElement accountButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("accountBtn")));
		actions.moveToElement(accountButton).perform();
		boolean checkUserNameEquality = this.userName.getText().toString().equals(username);
		return checkUserNameEquality;
	}

	public void checkBoutiquesImages() {
		for (int i = 0; i < allTabs.size(); i++) {
			clickTab(i);
			checkBoutiqueImageInTab();
		}
	}

	private List<WebElement> getBoutiqueInTab() {
		WebElement butikBigList = driver.findElement(By.xpath("//div[contains(@class, 'component-big-list')]"));
		List<WebElement> butikList = butikBigList.findElements(By.tagName("article"));
		return butikList;
	}

	private void clickTab(int index) {
		List<WebElement> item_list = allTabs;
		item_list.get(index).click();
	}

	private void clickBoutique(int index) {
		List<WebElement> item_list = getBoutiqueInTab();
		item_list.get(index).click();
	}

	private void checkBoutiqueImageInTab() {
		List<WebElement> item_list = getBoutiqueInTab();
		for (int i = 0; i < item_list.size(); i++) {
			boolean isPopupExist = item_list.get(i).findElement(By.xpath("//a/span/img")).isDisplayed();
			if (!isPopupExist) {
				this.logHelper.writeLog("Element's image is not found in this coordinate : " + item_list.get(i).getLocation().x + "," + item_list.get(i)
						.getLocation().y);
			}
		}
	}

	public BoutiqueDetailsPage goBoutiqueDetails(int indexTab, int indexButik) {
		clickTab(indexTab);
		clickBoutique(indexButik);
		return new BoutiqueDetailsPage(driver);
	}

}
