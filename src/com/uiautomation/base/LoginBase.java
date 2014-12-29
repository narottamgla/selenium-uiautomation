package com.uiautomation.base;

import org.openqa.selenium.WebDriver;
import com.uiautomation.pageobjects.*;

public class LoginBase{

	private WebDriver driver;
	public LoginBase(WebDriver driver) {
		this.driver=driver;
	}
	LoginPage LoginPage;
public void loginToDummySite(String username,String password){
	LoginPage=new LoginPage(driver);
	LoginPage.enterUserName(username);
	LoginPage.enterPassword(password);
	LoginPage.clickLoginButton();	
}
}
