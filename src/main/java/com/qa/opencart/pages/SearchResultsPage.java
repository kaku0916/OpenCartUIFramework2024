package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.ElementUtil;

public class SearchResultsPage {

	WebDriver driver;
	private ElementUtil eliUtil;
	// Page Library/Page Class//Page Objects
	// By Loacators
	private By searchProducts = By.cssSelector("div.product-thumb");

	// Public Page Class Consrtrcutor

	public SearchResultsPage(WebDriver driver) {
		this.driver = driver;
		eliUtil = new ElementUtil(driver);
	}

	// public actions

	public int getSearchProductCount() {
     return  eliUtil.waitForElementsVisible(searchProducts, 10).size();
	}
	
	public ProductInfoPage selectProduct(String productName) {
		eliUtil.waitForElementVisible(By.linkText(productName), 10).click();
		return new ProductInfoPage(driver);
	}
}
