package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.logs.Log;
import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.TimeUtil;

import io.qameta.allure.Step;

public class LoginPage {
//Page Class/Page Library//Page Objects
	WebDriver driver;
	private ElementUtil eliUtil;
	// Private By Locator
	private By emaiId = By.id("input-email");
	private By password = By.id("input-password");
	private By forgotPwdLink = By.linkText("Forgotten Password");
	private By loginButton = By.xpath("//input[@value='Login']");
	private By registerLink = By.linkText("Register");
	private By naveen = By.linkText("naveen");
	// public page Class Constructor
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eliUtil=new ElementUtil(driver);
	}

	// Public page actions
	@Step("Getting login page title")
	public String getLoginPageTitle() {
		
		String title =eliUtil.waitForTitleIs(AppConstants.LOGIN_PAGE_TITLE, TimeUtil.DEFAULT_MEDIUM_TIME);
		//String pageTitle=driver.getTitle();	
		//System.out.println("Page Title is " + title);
		Log.info("Page Title is " + title);
		return title;
	}
	@Step("Getting login page URL")
	public String getLoginPageURL() {
		String url =eliUtil.waitForURLContains(AppConstants.LOGIN_PAGE_URL_FRACTION, TimeUtil.DEFAULT_MEDIUM_TIME);
		//String acturl = driver.getCurrentUrl();
		//System.out.println("Page Url " + url);
		Log.info("Page Url " + url);
		return url;
	}
	@Step("Getting the status of forgot pass link")
	public boolean isForgotPwdLinkExist() {
		return eliUtil.isElementDisplayed(forgotPwdLink);

	}
     @Step("Login with username {0} and password {1}")
	public AccountsPage doLogin(String username, String pwd) {
		//System.out.println("User Credentials :" +username+" :"+pwd);
		Log.info("User Credentials :" +username+" :"+pwd);
		eliUtil.waitForElementVisible(emaiId, TimeUtil.DEFAULT_LONG_TIME).sendKeys(username);
		eliUtil.doSendKeys(password, pwd);
		eliUtil.doClick(loginButton);
		//driver.findElement(emaiId).sendKeys("KameshKumar@gmail.com");
		//driver.findElement(password).sendKeys("kaku0916@123");
		//driver.findElement(loginButton).click();
		return new AccountsPage(driver);
	}
	@Step("Navigating to registration page")
	public RegisteratioPage navigateToRegistrationPage() {
		eliUtil.waitForElementVisible(registerLink, TimeUtil.DEFAULT_LONG_TIME).click();
		return new RegisteratioPage(driver);
	}
}
