package com.qa.hubspot.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.pages.ContactsPage;
import com.qa.hubspot.pages.HomePage;
import com.qa.hubspot.pages.LoginPage;
import com.qa.hubspot.util.TestUtil;

public class ContactsPageTest {
	
	public BasePage basePage;
	//public WebDriver driver;
	public Properties prop;
	public LoginPage loginPage;
	public HomePage homePage;
	public ContactsPage contactsPage;
	
	
	@BeforeMethod
	public void setUp(){
		basePage = new BasePage();
		prop = basePage.init_properties();
		String browser = prop.getProperty("browser");
		basePage.init_driver(browser);
		BasePage.getDriver().get(prop.getProperty("url"));
		loginPage = new LoginPage();
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		contactsPage = homePage.gotoContactsPage();
	}
	
	@DataProvider(name = "getContactsData")
	public Object[][] getContactsTestData(){
		Object contactsData[][] = TestUtil.getTestData("contacts");
		return contactsData;
	}
	
	@Test(dataProvider = "getContactsData")
	public void createContactsTest(String email, String firstName, String lastName, String jobTitle){
		contactsPage.createNewContact(email, firstName, lastName, jobTitle);
	}
	
	
	@AfterMethod(alwaysRun=true)
	public void tearDown(){
		BasePage.getDriver().quit();
	}
	
	
	
	

}
