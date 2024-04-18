package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountsPage {

	WebDriver driver;

//Page Library/Page Class//Page Objects
	// By Loacators
	private By allHeader = By.xpath("//h2");
	private By logoutButton = By.linkText("Logout");
	private By search = By.name("search");
	private By searchButton = By.xpath("//span[@class=\"input-group-btn\"]");

	// Public Page Class Consrtrcutor

	public AccountsPage(WebDriver driver) {
		this.driver = driver;
	}

	// public page actions

	public int getAllAccountsPageHeader() {
		int headerCount=driver.findElements(allHeader).size();
		System.out.println("Header Count is "+headerCount);
		return headerCount;
	}

	public String getAccountsPageTitle() {
		String title=driver.getTitle();
		System.out.println("MY Account Page Title is "+title);
		return title ;
	}

	public String getAccountsPageURLTest() {
		String URL=driver.getCurrentUrl();
		System.out.println("My Account Page Url is "+URL);
		return URL;
	}
	public boolean isLogoutLinkExist() {
		return driver.findElement(logoutButton).isDisplayed();
	}

	public void getSearchResultTest() {
    driver.findElement(search).sendKeys("macbook");
    driver.findElement(searchButton).click();
    new SerchResultPage();
	}
}
