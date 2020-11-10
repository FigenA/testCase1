
package com.trendyol.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductDetailsPage extends PageObject {


	public ProductDetailsPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(className = "add-to-bs-tx")
	WebElement basketButton;

	@FindBy(xpath = "//li[@id='myBasketListItem']/div")
	WebElement myBasket;

	@FindBy(xpath = "//h1[@class='pr-new-br']")
	WebElement brandName;

	public void addToBasket() {
		basketButton.click();
	}

	public String getProductDesc() {
		return brandName.getText();
	}


	public BasketPage goMyBasket() {
		myBasket.click();
		return new BasketPage(driver);

	}
}
