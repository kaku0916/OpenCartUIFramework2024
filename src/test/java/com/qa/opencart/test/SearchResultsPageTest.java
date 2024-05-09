package com.qa.opencart.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.qa.opencart.base.BaseTest;

public class SearchResultsPageTest extends BaseTest {

	@BeforeClass
	public void searchResultSetup() {
		accountsPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));

	}

	@DataProvider
	public Object[][] getProductCountData() {
		return new Object[][] {
			{"macbook",3},
			{"imac",1},
			{"samsung",2},
			{"apple",1}
		};
	}

	@Test(dataProvider = "getProductCountData")
	public void searchResultTest(String productKey, int productValue) {
		searchResultPage = accountsPage.doSearch(productKey);
		Assert.assertEquals(searchResultPage.getSearchProductCount(), productValue);
	}
	
	

}
