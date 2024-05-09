package com.qa.opencart.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.CSVUtil;
import com.qa.opencart.utils.ExcelUtil;
import com.qa.opencart.utils.StringUtil;

import io.qameta.allure.Step;

public class RegisteratioPageTest extends BaseTest {

	@BeforeClass
	public void regisSetup() {
		registeratioPage = loginPage.navigateToRegistrationPage();
	}

	@DataProvider
	public Object[][] getUserRegTestData() {
		return new Object[][] {
			{"Saranya","Suresh","8975343498","Nita@126","yes"},
			{"Akhla","Suresh","8975343490","Nita@127","no"},
			{"Jahnav","Suresh","8975343495","Nita@128","yes"},
		};
	}
	@DataProvider
	public void getUserRegTestDataFromExcel() {
		ExcelUtil.getTestData(AppConstants.RGISTER_SHEET_NAME);
		
	}
	@DataProvider
	public Object[][] getUserRegTestDataFromCSV() {
			return CSVUtil.csvData(AppConstants.RGISTER_SHEET_NAME);		
	}
@Step("Checking user registration")
	@Test(dataProvider = "getUserRegTestDataFromCSV")
	public void userRegistrationPageTest(String firstName,String lastName,String telephone,String password,String subscribe){
	Assert.assertTrue(registeratioPage.userRegisteration("Saranya", "Suresh", 
			StringUtil.getRandomEmailId(),
				"8975343498", "Nita@126", "yes"));
	}
}
