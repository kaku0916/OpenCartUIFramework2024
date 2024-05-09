package com.qa.opencart.test;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.CSVUtil;
import com.qa.opencart.utils.ExcelUtil;

public class ProductInfoPageTest extends BaseTest {

	@BeforeClass
	public void productInfoSetup() {
		accountsPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));

	}
      @DataProvider
	public Object[][] getSerachProductData() {
		return new Object[][] {
			{"macbook","MacBook Pro"},
			{"mac","iMac"},
			{"samsung","Samsung SyncMaster 941BW"},
			{"samsung","Samsung Galaxy Tab 10.1"},
		};
	}
     
	
	
	@Test(dataProvider = "getSerachProductData")
	public void ProductHeaderTest(String serchKey,String productName)  {
		searchResultPage = accountsPage.doSearch(serchKey);
		productInfoPage = searchResultPage.selectProduct(productName);
		Assert.assertEquals(productInfoPage.getProductHeader(), productName);
	}
	@DataProvider
	public Object[][] getSerachImageData() {
		return new Object[][] {
			{"macbook","MacBook Pro",4},
			{"mac","iMac",3},
			{"samsung","Samsung SyncMaster 941BW",1},
			{"samsung","Samsung Galaxy Tab 10.1",7},
		};
	}
	@DataProvider
	public Object[][] getSerachImageDataFromExcel() {
		return ExcelUtil.getTestData(AppConstants.PRODUCT_SHEET_NAME);
	}
	@DataProvider
	public Object[][] getSerachImageDataFromCSV() {
		return CSVUtil.csvData(AppConstants.PRODUCT_SHEET_NAME);
	}
	
	@Test(dataProvider ="getSerachImageDataFromCSV" )
	public void ProductImageCountTest(String serchKey,String productName,String imagesCount) {
		searchResultPage = accountsPage.doSearch(serchKey);
		productInfoPage = searchResultPage.selectProduct(productName);
		Assert.assertEquals(productInfoPage.getProductImageCount(),Integer.parseInt(imagesCount));
	}
	@Test
	public void ProductInfoTest() {
		searchResultPage = accountsPage.doSearch("macbook");
		productInfoPage = searchResultPage.selectProduct("MacBook Pro");
		Map<String, String>	actProductDetailsMap=productInfoPage.getProductDetailsMap();
		softAssert.assertEquals(actProductDetailsMap.get("Brand"), "Apple");
		softAssert.assertEquals(actProductDetailsMap.get("Product Code"), "Product 18");
		softAssert.assertEquals(actProductDetailsMap.get("Reward Points"), "800");
		softAssert.assertEquals(actProductDetailsMap.get("Availability"), "In Stock");
		softAssert.assertEquals(actProductDetailsMap.get("productPrice"), "$2,000.00");
		softAssert.assertEquals(actProductDetailsMap.get("externalTax"), "$2,000.00");
		softAssert.assertAll();
	}
	

}
