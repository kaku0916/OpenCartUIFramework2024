package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
//Page Class/Page Library//Page Objects
	WebDriver driver;
	// Private By Locator
	private By emaiId = By.id("input-email");
	private By password = By.id("input-password");
	private By forgotPwdLink = By.linkText("Forgotten Password");
	private By loginButton = By.xpath("//input[@value='Login']");

	// public page Class Constructor
	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}

	// Public page actions
	public String getLoginPageTitle() {
		String pageTitle = driver.getTitle();	
		System.out.println("Page Title is " + pageTitle);
		return pageTitle;
	}

	public String getLoginPageURL() {
		String url = driver.getCurrentUrl();
		System.out.println("Page Url" + url);
		return url;
	}

	public boolean isForgotPwdLinkExist() {
		return driver.findElement(forgotPwdLink).isDisplayed();

	}

	public AccountsPage doLogin(String username, String pwd) {
		System.out.println("User Credentials :" +username+" :"+pwd);
		driver.findElement(emaiId).sendKeys("KameshKumar@gmail.com");
		driver.findElement(password).sendKeys("kaku0916@123");
		driver.findElement(loginButton).click();
		return new AccountsPage(driver);
	}
}
