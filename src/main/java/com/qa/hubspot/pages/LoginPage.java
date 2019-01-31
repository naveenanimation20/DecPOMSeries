package com.qa.hubspot.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.hubspot.base.BasePage;

public class LoginPage extends BasePage{
	
	//1. Page objects with the help of: PageFactory
	@FindBy(id = "username")
	WebElement loginName;
	
	@FindBy(id = "password")
	WebElement password;
	
	@FindBy(id = "loginBtn")
	WebElement loginBtn;
	
	@FindBy(xpath = "//a/i18n-string[contains(text(),'Forgot my password')]")
	WebElement forgotPwdLink;
	
	
	//2. create a constructor of page class and initialize page elements
	public LoginPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//3. Methods/Actions of page:
	public String getLoginPageTitle(){
		return driver.getTitle();
	}
	
	public boolean verifyForgotPassowrdLink(){
		return forgotPwdLink.isDisplayed();
	}
	
	public HomePage login(String un, String pwd){
		loginName.sendKeys(un);
		password.sendKeys(pwd);
		loginBtn.click();
		
		return new HomePage(driver);
	}
	
}
