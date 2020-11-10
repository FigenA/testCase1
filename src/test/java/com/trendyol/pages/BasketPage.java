
package com.trendyol.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BasketPage extends PageObject {
	public BasketPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(className = "pb-item")
	WebElement brandName;

	public String getProductBrandInBasket() {
		return brandName.getText();
	}
}
