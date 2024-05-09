package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class RegisteratioPage {
	// Page Class/Page Library//Page Objects
	WebDriver driver;
	private ElementUtil eliUtil;
	// Private By Locator
	private By firstName = By.id("input-firstname");
	private By lastName = By.id("input-lastname");
	private By email = By.id("input-email");
	private By telephone = By.id("input-telephone");
	private By password = By.id("input-password");
	private By confirmpassword = By.id("input-confirm");
	private By subscribeYes = By.xpath("//label[@class='radio-inline']//input[@type='radio' and @value = '1']");
	private By subscribeNo = By.xpath("//label[@class='radio-inline']//input[@type='radio' and @value = '0']");
	private By agreeCheckBox = By.name("agree");
	private By continueBtn = By.xpath("//input[@type='submit' and @value='Continue']");

	private By sucessMessg = By.cssSelector("div#content h1");

	private By logoutLink = By.linkText("Logout");
	private By registerLink = By.linkText("Register");
	private By pop = By.linkText("pop");

	// public page Class Constructor
	public RegisteratioPage(WebDriver driver) {
		this.driver = driver;
		eliUtil = new ElementUtil(driver);
	}
@Step("User registration")
	public boolean userRegisteration(String firstName,String lastName,String email,String telephone,String password,String subscribe) {
		eliUtil.waitForElementVisible(this.firstName,10).sendKeys(firstName);
		eliUtil.doSendKeys(this.lastName,lastName);
		eliUtil.doSendKeys(this.email,email);
		eliUtil.doSendKeys(this.telephone,telephone);
		eliUtil.doSendKeys(this.password,password);
		eliUtil.doSendKeys(this.confirmpassword,password);
		
		if(subscribe.equalsIgnoreCase("yes")) {
			eliUtil.doClick(subscribeYes);
			
		}
		else{ 
			eliUtil.doClick(subscribeNo);	
		}
		eliUtil.doClick(agreeCheckBox);
		eliUtil.doClick(continueBtn);
		
		
		String successMessage=eliUtil.waitForElementVisible(sucessMessg, 10).getText();
		System.out.println(successMessage);
		if(successMessage.equals(AppConstants.USER_REGI_SUCCESS_MESSG)) {
			eliUtil.doClick(logoutLink);
			eliUtil.doClick(registerLink);
			return true;
		}
		else {
			return false;
		}
	}
}
