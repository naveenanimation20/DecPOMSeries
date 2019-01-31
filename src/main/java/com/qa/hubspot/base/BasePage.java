package com.qa.hubspot.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class BasePage {

	public WebDriver driver;
	public Properties prop;

	public WebDriver init_driver(String browserName) {

		if (browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", 
					System.getProperty("user.dir")+"/src/main/resources/drivers/chromedriver");
			if (prop.getProperty("headless").equals("yes")) {
				ChromeOptions co = new ChromeOptions();
				co.addArguments("--headless");
				driver = new ChromeDriver(co);
			} else {
				driver = new ChromeDriver();
			}
		} else if (browserName.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", 
					System.getProperty("user.dir")+"/src/main/resources/drivers/geckodriver");
			if (prop.getProperty("headless").equals("yes")) {
				FirefoxBinary fb = new FirefoxBinary();
				fb.addCommandLineOptions("--headless");
				FirefoxOptions fo = new FirefoxOptions();
				fo.setBinary(fb);
				driver = new FirefoxDriver(fo);
			} else {
				driver = new FirefoxDriver();
			}
		} else {
			System.out.println(browserName + ": is not correct or blank");
			try {
				throw new Exception("NO BROWSERFOUND EXCEPTION");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		driver.manage().window().fullscreen();
		driver.manage().timeouts().pageLoadTimeout(Integer.parseInt(prop.getProperty("pageloadtimeout")),
				TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
		return driver;

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

}
