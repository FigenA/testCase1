
package com.trendyol.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.trendyol.conf.LogHelper;

public class BoutiqueDetailsPage extends PageObject {
	private LogHelper logHelper;

	public BoutiqueDetailsPage(WebDriver driver) {
		super(driver);
		this.logHelper = new LogHelper();
	}

	// @FindAll(@FindBy(xpath = "//div[@class='products']/div"))

	List<WebElement> allProductsList = getProductList();

	private int controlProductsContainer() {
		int index = 0;
		try {
			driver.findElement(By.xpath("//div[@id='search-app']"));
			index = 1;
		} catch (org.openqa.selenium.NoSuchElementException e) {
			// (driver.findElement(By.xpath("//div[@id='boutique-detail-app']")).isDisplayed())
			// olması
			index = 2;
		}


		return index;
	}

	private List<WebElement> getProductList() {
		List<WebElement> productsList = null;
		if (controlProductsContainer() == 1) {
			productsList = driver.findElements(By.xpath("//div[@class='prdct-cntnr-wrppr']/div"));
		} else if (controlProductsContainer() == 2) {
			productsList = driver.findElements(By.xpath("//div[@class='products']/div"));
		}
		return productsList;
	}

	public void checkProductsImages() {
		if (controlProductsContainer() == 1) {
			List<WebElement> item_list = allProductsList;
			for (int i = 0; i < item_list.size(); i++) {
				boolean isPopupExist = item_list.get(i).findElement(By.xpath("//div/a/div/div/img")).isDisplayed();
				if (!isPopupExist) {
					this.logHelper.writeLog("Element's image is not found in this coordinate : " + item_list.get(i).getLocation().x + "," + item_list.get(i)
							.getLocation().y);
				}
			}
		} else if (controlProductsContainer() == 2) {

			List<WebElement> item_list = allProductsList;
			for (int i = 0; i < item_list.size(); i++) {
				boolean isPopupExist = item_list.get(i).findElement(By.xpath("//a/div/img")).isDisplayed();
				if (!isPopupExist) {
					this.logHelper.writeLog("Element's image is not found in this coordinate : " + item_list.get(i).getLocation().x + "," + item_list.get(i)
							.getLocation().y);
				}
			}
		}
	}

	private WebElement getProduct(int index) {

		return allProductsList.get(index);
	}

	private void clickProduct(int index) {

		allProductsList.get(index).click();
	}

	public String getProductDesc(int index) {
		WebElement product = getProduct(index);
		return product.findElement(By.xpath("//a/div[3]//span[2]")).getText();
	}

	public ProductDetailsPage goProductDetails(int index) {
		clickProduct(index);
		return new ProductDetailsPage(driver);
	}



}
