package com.uiautomation.testscripts;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.BeforeTest;
import com.uiautomation.base.LoginBase;
import com.uiautomation.setup.SetUpBrowser;



public class LoginTest extends SetUpBrowser{
	WebDriver driver;
	LoginBase  LoginBase=new LoginBase(driver);
  @Test(description = "Login to dummy site ")
  public void loginToDummyTest() throws Exception {
	  LoginBase=new LoginBase(driver);
	  System.out.println("Login To Hotel Reservation System ");
	  LoginBase.loginToDummySite("narottamgla","chhonkar1989");
	  Assert.assertEquals("billu", "barawar");
	  System.out.println("Login To Hotel Reservation System Done for me ");	
  }
  @BeforeTest
	 public void Setup() throws MalformedURLException {
	  driver=super.setUpWebDriver("FF");
	 
  }

@AfterTest
public void tearDown(){
	//driver.close();
	//driver.quit();
}
}
