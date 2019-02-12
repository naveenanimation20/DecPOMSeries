package com.qa.hubspot.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class BasePage {

	//public WebDriver driver;
	public Properties prop;
	//WebDriver driver;
	public static ThreadLocal<WebDriver> tldriver = new ThreadLocal<WebDriver>();


	public WebDriver init_driver(String browserName) {

		if (browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", 
					System.getProperty("user.dir")+"/src/main/resources/drivers/chromedriver");
			if (prop.getProperty("headless").equals("yes")) {
				ChromeOptions co = new ChromeOptions();
				co.addArguments("--headless");
				tldriver.set(new ChromeDriver(co));
			} else {
				tldriver.set(new ChromeDriver());
			}
		} 
			else if (browserName.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", 
					System.getProperty("user.dir")+"/src/main/resources/drivers/geckodriver");
			if (prop.getProperty("headless").equals("yes")) {
				FirefoxBinary fb = new FirefoxBinary();
				fb.addCommandLineOptions("--headless");
				FirefoxOptions fo = new FirefoxOptions();
				fo.setBinary(fb);
				tldriver.set(new FirefoxDriver(fo));
			} else {
				tldriver.set(new FirefoxDriver());
			}
		} 
			else {
			System.out.println(browserName + ": is not correct or blank");
			try {
				throw new Exception("NO BROWSERFOUND EXCEPTION");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		getDriver().manage().window().fullscreen();
		getDriver().manage().timeouts().pageLoadTimeout(Integer.parseInt(prop.getProperty("pageloadtimeout")),
				TimeUnit.SECONDS);
		getDriver().manage().deleteAllCookies();
		return getDriver();

	}
	
	public static synchronized WebDriver getDriver() {
		return tldriver.get();
	}
	

	public Properties init_properties() {
		prop = new Properties();
		try {
			FileInputStream ip = new FileInputStream("/Users/NaveenKhunteta/Documents/workspace/DecPOMSeries/"
					+ "src/main/java/com/qa/hubspot/config/config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}
	
	public String getScreenshot() {
		File src = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/screenshots/" + System.currentTimeMillis() + ".png";
		File destination = new File(path);
		try {
			FileUtils.copyFile(src, destination);
		} catch (IOException e) {
			System.out.println("Capture Failed " + e.getMessage());
		}
		return path;
	}

}
