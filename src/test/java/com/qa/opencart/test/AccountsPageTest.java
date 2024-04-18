package com.qa.opencart.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

public class AccountsPageTest extends BaseTest {

	@BeforeClass
	public void setupAccountPage() {
		accountsPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test(priority = 0)
	public void getAccountsPageTitleTest() {
	String 	acttitle=accountsPage.getAccountsPageTitle();
	Assert.assertEquals(acttitle, AppConstants.ACCOUNTS_PAGE_TITLE);
	}

	@Test(priority = 1)
	public void getAccountsPageURLTest() {
		String 	actURL=accountsPage.getAccountsPageURLTest();
		Assert.assertTrue(actURL.contains(AppConstants.ACCOUNTS_PAGE_URL_FRACTION));
	}
	@Test(priority = 2)
	public void getAllAccountsPageHeaderTest() {
     int headerCount=accountsPage.getAllAccountsPageHeader();
     Assert.assertEquals(headerCount, 4);
	}
	@Test(priority = 3)
	public void isLogoutLinkExistTest() {
		Assert.assertTrue(accountsPage.isLogoutLinkExist()); 
		System.out.println("Logout Link Exist");
	}
	@Test(priority = 4)
	public void getSearchResultTest() {
		accountsPage.getSearchResultTest();
		System.out.println("SearchResultPassed");
	}

}
