package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class AccountsPage {

	WebDriver driver;
	private ElementUtil eliUtil;
//Page Library/Page Class//Page Objects
	// By Loacators
	private By logoutLink = By.linkText("Logout");
	private By myAccountLink = By.linkText("My Account");
	private By headers = By.cssSelector("div #content h2");
	private By search = By.name("search");
	private By searchIcon = By.cssSelector("div #search button");

	// Public Page Class Consrtrcutor

	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		eliUtil = new ElementUtil(driver);
	}

	// public page actions

	public String getAccountPageTitle() {
		String title = eliUtil.waitForTitleIs(AppConstants.ACCOUNTS_PAGE_TITLE, 5);
		System.out.println("Acc Page Title is " + title);
		return title;
	}

	public String getAccountPageURL() {
		String url = eliUtil.waitForURLContains(AppConstants.ACCOUNTS_PAGE_URL_FRACTION, 5);
		System.out.println("Acc Page Url" + url);
		return url;
	}
	
	public boolean isLogoutLinkExist() {
		return eliUtil.waitForElementVisible(logoutLink, 5).isDisplayed();
	}
	
	public boolean ismyAccountLinkExist() {
		return eliUtil.waitForElementVisible(myAccountLink, 5).isDisplayed();
	}

	public List<String>  accPageHeadersList() {
		List<WebElement>headersEleList=eliUtil.getElements(headers);
		List<String> headersList=new ArrayList<String>();
		for(WebElement e:headersEleList) {
			String header=e.getText();
			headersList.add(header);
			
		}
		return headersList;
	}

	

	public SearchResultsPage doSearch(String SearchKey) {
		System.out.println("Searching result for "+SearchKey);
		eliUtil.doSendKeys(search, SearchKey);
		eliUtil.doClick(searchIcon);
		return new SearchResultsPage(driver);
	}

	
}
