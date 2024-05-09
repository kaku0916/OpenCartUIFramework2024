package com.qa.opencart.utils;

public class StringUtil {

	public static String getRandomEmailId() {
		String emailId="testautomation"+System.currentTimeMillis()+"@opecart.com";
		//System.out.println(emailId);
		return emailId;
	}
}
