package com.qa.hubspot.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.hubspot.base.BasePage;
import com.qa.hubspot.constants.Constants;
import com.qa.hubspot.util.TestUtil;

public class HomePage extends BasePage{
	
	@FindBy(xpath = "//h1[text()='Sales Dashboard']")
	WebElement homePageHeader;
	
	@FindBy(id = "nav-primary-contacts-branch")
	WebElement contactsTab;
	
	@FindBy(id = "nav-secondary-contacts")
	WebElement contactsLink;
	
	public HomePage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public String getHomePageTitle(){
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.titleContains(Constants.HOME_PAGE_HEADER));
		return driver.getTitle();
	}
	
	public boolean verifyHomePageHeader(){
		return homePageHeader.isDisplayed();
	}
	
	public ContactsPage gotoContactsPage(){
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(contactsTab));
		contactsTab.click();
		TestUtil.shortWait();
		contactsLink.click();
		return new ContactsPage(driver);
	}
	
	

}
