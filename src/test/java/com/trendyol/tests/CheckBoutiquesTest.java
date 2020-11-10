
package com.trendyol.tests;

import java.io.IOException;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.trendyol.conf.BrowserTypeEnum;
import com.trendyol.conf.Common;
import com.trendyol.conf.Driver;
import com.trendyol.pages.BasketPage;
import com.trendyol.pages.BoutiqueDetailsPage;
import com.trendyol.pages.HomePage;
import com.trendyol.pages.LoginPage;
import com.trendyol.pages.ProductDetailsPage;



public class CheckBoutiquesTest extends Driver {

	// Browser type should be selected. The values it will take are into the
	// com/trendyol/com/BrowserTypeEnum.
	public CheckBoutiquesTest() {
		super(BrowserTypeEnum.chrome);
	}

	Common common = new Common();
	LoginPage loginPage;
	HomePage homePage;
	BoutiqueDetailsPage butikDetails;
	ProductDetailsPage productDetails;
	BasketPage basket;

	// User information must be specified in the user.properties file.
	Map<String, String> userProperty = common.readUserProperties("test1");
	String userName = userProperty.get("userName");
	String password = userProperty.get("password");
	String accountNameSurname = userProperty.get("accountNameSurname");

	String loginPageHeader = "Trendyol’a giriş yap veya hesap oluştur, indirimleri kaçırma!";

	/**
	 * <b>Test Name:</b>Login<br>
	 * <b>Description:</b>Login to Trendyol website. Url is kept into the com/trendyol/com/Driver
	 * class.<br>
	 * <p>
	 * 
	 * @throws InterruptedException
	 * @throws IOException
	 */
	@Test()
	public void login() throws InterruptedException, IOException {

		homePage = new HomePage(driver);

		Assert.assertEquals("En Trend Ürünler Türkiye'nin Online Alışveriş Sitesi Trendyol'da",
				driver.getTitle());
		loginPage = homePage.login();

		Assert.assertEquals(loginPage.getLoginTitle(), loginPageHeader);
		loginPage.loginToTrendyol(userName, password);
		Assert.assertTrue(homePage.checkUsername(accountNameSurname));


	}

	/**
	 * <b>Test Name:</b>Check Boutique's Images In Tab <br>
	 * <b>Description:</b>Clicks each tab and logs the boutiques that dont have any image.<br>
	 * <p>
	 */
	@Test(dependsOnMethods = {"login"})
	public void checkBoutiquesImageInTab() {
		homePage.checkBoutiquesImages();
	}

	/**
	 * <b>Test Name:</b>Check Product's Images In The Boutique <br>
	 * <b>Description:</b>Go to the boutique in the tab given as parameter and logs the products
	 * that don't have any image.<br>
	 * <p>
	 */
	@Test(dependsOnMethods = {"checkBoutiquesImageInTab"})
	public void checkProductsImages() {
		butikDetails = homePage.goBoutiqueDetails(2, 2);
		butikDetails.checkProductsImages();
	}

	/**
	 * <b>Test Name:</b>Add Product To The Basket <br>
	 * <b>Description:</b>Add the product given as a parameter to the basket.<br>
	 * <p>
	 */

	@Test(dependsOnMethods = {"checkProductsImages"})
	public void addBasket() {
		String prodDesc = butikDetails.getProductDesc(1);
		productDetails = butikDetails.goProductDetails(1);
		productDetails.addToBasket();
		// Assert.assertTrue(productDetails.getProductDesc().contains(prodDesc));
	}

	/**
	 * <b>Test Name:</b>Enter The Basket<br>
	 * <b>Description:</b>After the product has been added to the basket, it enters my basket.<br>
	 * <p>
	 */

	@Test(dependsOnMethods = {"addBasket"})
	public void enterBasket() {
		String prodDesc = productDetails.getProductDesc();
		basket = productDetails.goMyBasket();
		// Assert.assertTrue(prodDesc.contains(basket.getProductBrandInBasket()));
	}

}
