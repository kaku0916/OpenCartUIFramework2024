package com.qa.opencart.test;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.errors.AppError;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("EPIC:100: Design the opencart login page")
@Story("US:100:Design login page feature for opencart application")
@Feature("Feature:201 :Adding login features")
public class LoginPageTest extends BaseTest{
  @Description("Checking login page title...")
  @Severity(SeverityLevel.MINOR)
  @Test(priority = 1)
	public void getLoginPageTitle()  {
		String actTitle=loginPage.getLoginPageTitle();
		System.out.println(actTitle);
		Assert.assertEquals(actTitle, AppConstants.LOGIN_PAGE_TITLE,AppError.TITLE_NOT_FOUND);
	}
  @Description("Checking log page URL...")
  @Severity(SeverityLevel.MINOR)
	@Test(priority = 2)
	public void getPageURLTest() {
		String acturl  =loginPage.getLoginPageURL();
		System.out.println(acturl);
		Assert.assertTrue(acturl.contains(AppConstants.LOGIN_PAGE_URL_FRACTION),AppError.URL_NOT_FOUND);
	}
	
  @Description("Checking forgot pwd link on login page...")
  @Severity(SeverityLevel.CRITICAL)
	@Test(priority = 3)
	public void isForgotPWDLInkExistTest() {
		Assert.assertTrue(loginPage.isForgotPwdLinkExist());
	}
  
  @Description("Checking user able to login...")
  @Severity(SeverityLevel.BLOCKER)
	@Test(priority = 4)
	public void doLoginTest() {
		accountsPage=loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertEquals(accountsPage.getAccountPageTitle(), AppConstants.ACCOUNTS_PAGE_TITLE);
	}
}
