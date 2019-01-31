package com.qa.hubspot.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.constants.Constants;
import com.qa.hubspot.pages.LoginPage;

public class LoginTest {
	
	public BasePage basePage;
	public WebDriver driver;
	public Properties prop;
	public LoginPage loginPage;
	
	
	@BeforeMethod
	public void setUp(){
		basePage = new BasePage();
		prop = basePage.init_properties();
		String browser = prop.getProperty("browser");
		driver = basePage.init_driver(browser);
		driver.get(prop.getProperty("url"));
		loginPage = new LoginPage(driver);
	}
	
	@Test(priority=1)
	public void loginPageTitleTest(){
		String title = loginPage.getLoginPageTitle();
		System.out.println("login page title is: "+ title);
		Assert.assertEquals(title, Constants.LOGIN_PAGE_TITLE, "login page title is not matched");
	}
	
	@Test(priority=2)
	public void forgotPwdLinkTest(){
	Assert.assertTrue(loginPage.verifyForgotPassowrdLink(), "forgor pwd link is not displayed");
	}
	
	@Test(priority=3)
	public void login_CorrectData_Test(){
		loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	
	
	@AfterMethod
	public void tearDown(){
		driver.quit();
	}
	
	
	
	
	

}
