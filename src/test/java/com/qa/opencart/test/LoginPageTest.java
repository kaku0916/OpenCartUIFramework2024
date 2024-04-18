package com.qa.opencart.test;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.errors.AppError;


public class LoginPageTest extends BaseTest{
	
  @Test(priority = 1)
	public void getLoginPageTitle()  {
		String actTitle=loginPage.getLoginPageTitle();
		Assert.assertEquals(actTitle, AppConstants.LOGIN_PAGE_TITLE,AppError.TITLE_NOT_FOUND);
	}
	
	@Test(priority = 2)
	public void getPageURLTest() {
		String acturl  =loginPage.getLoginPageURL();
		Assert.assertTrue(acturl.contains(AppConstants.LOGIN_PAGE_URL_FRACTION),AppError.URL_NOT_FOUND);
	}
	
	
	@Test(priority = 3)
	public void isForgotPWDLInkExistTest() {
		Assert.assertTrue(loginPage.isForgotPwdLinkExist());
	}
	@Test(priority = 4)
	public void doLoginTest() {
		loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
}
