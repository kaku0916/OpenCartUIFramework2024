package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegisteratioPage;
import com.qa.opencart.pages.SearchResultsPage;

import io.qameta.allure.Step;

public class BaseTest {
	DriverFactory df;
	WebDriver driver;
	protected Properties prop;
	protected LoginPage loginPage;
	protected AccountsPage accountsPage;
	protected SearchResultsPage searchResultPage;
	protected ProductInfoPage productInfoPage;
	protected RegisteratioPage registeratioPage;
	protected SoftAssert softAssert; {
		
	}
	@Step("Setup: Launching {0} browser with init properties")
	@Parameters({"browser","browserversion","testname"})
	@BeforeTest
	public void setUp(String browserName, String browserVersion,String testName) {
		df = new DriverFactory();
		prop=df.initProp();
		
		if(browserName!=null) {
			prop.setProperty("browser", browserName);
			prop.setProperty("browserversion", browserVersion);
			prop.setProperty("testname", testName);
		}
		
		driver =df.initDriver(prop);
        loginPage=new LoginPage(driver);
        softAssert=new SoftAssert();
	}
    @Step("Closing the browser")
	@AfterTest
	public void tearDown() {
		driver.quit();

	}

}
