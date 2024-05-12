package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.safari.SafariDriver;
import com.qa.opencart.errors.AppError;
import com.qa.opencart.exceptions.BrowserException;
import com.qa.opencart.exceptions.FrameworkException;
import com.qa.opencart.logs.MyLog;


public class DriverFactory {

	WebDriver driver;
	Properties prop;
	OptionsManager optionsManager;
	public static String highlight;
	private static ThreadLocal<WebDriver> tlDriver=new ThreadLocal<WebDriver>();
	

	public WebDriver initDriver(Properties prop) {
		String browserName = prop.getProperty("browser");
		//String browserName=System.getProperty("browser");
		//System.out.println("Browser Name is " + browserName);
		MyLog.info("Browser Name is " + browserName);
		highlight = prop.getProperty("highlight");
		optionsManager = new OptionsManager(prop);
		switch (browserName.toLowerCase().trim()) {
		case "chrome":
			//driver = new ChromeDriver(optionsManager.getChromeOptions());
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
			break;
		case "firefox":
			//driver = new FirefoxDriver(optionsManager.getFirefoxOptions());
			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
			break;
		case "edge":
			//driver = new EdgeDriver(optionsManager.getEdgeOptions());
			tlDriver.set(new EdgeDriver(optionsManager.getEdgeOptions()));
			break;
		case "safari":
			//driver = new SafariDriver();
			tlDriver.set(new ChromeDriver());
			break;

		default:
			//System.out.println("Please pass the right Browser....." + browserName);
			MyLog.error("Please pass the right Browser....." + browserName);
			throw new BrowserException("No Browser Found.." + browserName);
		}

		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));

		return getDriver();

	}
	
	public static WebDriver  getDriver() {
		return tlDriver.get();//TL webDriver
	}

	public Properties initProp() {
		FileInputStream ip = null;
		prop = new Properties();
		// envName=qa,dev,stage,uat
		// mvn clean install -Denv='qa'
		String envName = System.getProperty("env");
		System.out.println("Running the testcase on env :" + envName);
		
		try {
		if (envName == null) {
			System.out.println("NO Env is given..hence running on QA");
			ip = new FileInputStream("./src/test/resources/Config/config.qa.properties");
		} else {

			switch (envName.toLowerCase().trim()) {
			case "qa":
				ip = new FileInputStream("./src/test/resources/Config/config.qa.properties");
				break;
			case "stage":
				ip = new FileInputStream("./src/test/resources/Config/config.stage.properties");
				break;
			case "dev":
				ip = new FileInputStream("./src/test/resources/Config/config.dev.properties");
				break;
			case "uat":
				ip = new FileInputStream("./src/test/resources/Config/config.uat.properties");
				break;
			case "prod":
				ip = new FileInputStream("./src/test/resources/Config/config.properties");
				break;

			default:
				System.out.println("Please pass the right browser :" + envName);
				throw new FrameworkException(AppError.ENV_NAME_NOT_FOUND + " : " + envName);

			}
		}
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
			try {
				prop.load(ip);
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			return prop;
	}
	/*
	 * Taking screenshot
	 */
	public static String getScreenshot(String methodName) {
		File srcFile=((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
		String path=System.getProperty("user.dir")+"/screenshot/"+methodName+"_"+System.currentTimeMillis()+".png";
		
		File destination=new File(path);
		
		try {
			FileHandler.copy(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
	}
}
