package com.qa.opencart.test;

import java.util.List;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

public class AccountsPageTest extends BaseTest {

	@BeforeClass
	public void accSetup() {
		accountsPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test(priority = 0)
	public void getAccountsPageTitleTest() {
		String acttitle = accountsPage.getAccountPageTitle();
		Assert.assertEquals(acttitle, AppConstants.ACCOUNTS_PAGE_TITLE);
	}

	@Test(priority = 1)
	public void getAccountsPageURLTest() {
		String actURL = accountsPage.getAccountPageURL();
		Assert.assertTrue(actURL.contains(AppConstants.ACCOUNTS_PAGE_URL_FRACTION));
	}

	@Test(priority = 2)
	public void isLogoutLinkExistTest() {
		Assert.assertTrue(accountsPage.isLogoutLinkExist());
	}

	@Test(priority = 3)
	public void isMyAccountLinkExistTest() {
		Assert.assertTrue(accountsPage.ismyAccountLinkExist());
	}

	@Test(priority = 4)
	public void getAllAccountsPageHeaderTest() {
		List<String> actHeaderList = accountsPage.accPageHeadersList();
		System.out.println(actHeaderList);
		// Assert.assertEquals(headerCount, 4);

	}

	@Test(priority = 5)
	public void SearchTest() {
		accountsPage.doSearch("macbook");
	}
}
