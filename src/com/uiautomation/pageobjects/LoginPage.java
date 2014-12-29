package com.uiautomation.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	@FindBy(id = "username")
	private static WebElement userNameTextbox;
	@FindBy(id = "password")
	private static WebElement passwordTextbox;
	@FindBy(id = "login")
	private static WebElement loginButton;
	
	public LoginPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
//		if (!"Adactln.com -Hotel Reservation".equals(driver.getTitle())) {
//			throw new IllegalStateException(
//					"This isn’t IndraCare LIS Login Page!");
//		}
	}

	public  void enterUserName(String username){
		userNameTextbox.clear();
		userNameTextbox.sendKeys(username);	
	}
	
	public  void enterPassword(String password){
		passwordTextbox.clear();
		passwordTextbox.sendKeys(password);	
	}
	
	public  void clickLoginButton(){
		loginButton.click();
		
	}
	
}
