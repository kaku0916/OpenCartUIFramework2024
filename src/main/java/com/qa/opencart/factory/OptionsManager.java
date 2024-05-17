package com.qa.opencart.factory;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.Platform;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

import com.qa.opencart.logger.Log;


public class OptionsManager {
	private Properties prop;
	private ChromeOptions co;
	private FirefoxOptions fo;
	private EdgeOptions eo;

	OptionsManager(Properties prop) {
		this.prop = prop;
	}

	public ChromeOptions getChromeOptions() {
		co = new ChromeOptions();

		if (Boolean.parseBoolean(prop.getProperty("remote"))) {
			co.setCapability("browserName", "chrome");
			co.setBrowserVersion(prop.getProperty("browserversion").trim());
			//all these properties will be added when remote=true
			Map<String, Object> selenoidOptions = new HashMap<>();
			selenoidOptions.put("screenResolution", "1280x1024x24");
			selenoidOptions.put("enableVNC", true);
			selenoidOptions.put("name", prop.getProperty("testname"));
			co.setCapability("selenoid:options", selenoidOptions);
			
		}
		if (Boolean.parseBoolean(prop.getProperty("headless").trim())) {
			//System.out.println("Running chrome in headless mode");
			Log.info("Running chrome in headless mode");
			co.addArguments("--headless");
		}
		if (Boolean.parseBoolean(prop.getProperty("incognito").trim())) {
			//System.out.println("Running chrome in incognito mode");
			Log.info("Running chrome in incognito mode");
			co.addArguments("--incognito");
		}
		return co;
	}

	public EdgeOptions getEdgeOptions() {
		eo = new EdgeOptions();

		if (Boolean.parseBoolean(prop.getProperty("remote"))) {
			eo.setCapability("browserName", "edge");
			eo.setCapability("platform", Platform.LINUX);
		}
		
		if (Boolean.parseBoolean(prop.getProperty("headless").trim())) {
			//System.out.println("Running Edge in headless mode");
			Log.info("Running Edge in headless mode");
			eo.addArguments("--headless");
		}
		if (Boolean.parseBoolean(prop.getProperty("incognito").trim())) {
			//System.out.println("Running Edge in incognito mode");
			Log.info("Running Edge in incognito mode");
			eo.addArguments("--inprivate");
		}
		return eo;
	}

	public FirefoxOptions getFirefoxOptions() {
		fo = new FirefoxOptions();
		if (Boolean.parseBoolean(prop.getProperty("remote"))) {
			fo.setCapability("browserName", "firefox");
			fo.setBrowserVersion(prop.getProperty("browserversion").trim());
			//all these properties will be added when remote=true
			Map<String, Object> selenoidOptions = new HashMap<>();
			selenoidOptions.put("screenResolution", "1280x1024x24");
			selenoidOptions.put("enableVNC", true);
			selenoidOptions.put("name", prop.getProperty("testname"));
			//waherver testname we are supplying it will display on conatsiner as well
			fo.setCapability("selenoid:options", selenoidOptions);
		}
		
		if (Boolean.parseBoolean(prop.getProperty("headless").trim())) {
			//System.out.println("Running Firefox in headless mode");
			Log.info("Running Firefox in headless mode");
			fo.addArguments("--headless");
		}
		if (Boolean.parseBoolean(prop.getProperty("incognito").trim())) {
			//System.out.println("Running Firefox in incognito mode");
			Log.info("Running Firefox in incognito mode");
			fo.addArguments("--incognito");
		}
		return fo;
	}

}
