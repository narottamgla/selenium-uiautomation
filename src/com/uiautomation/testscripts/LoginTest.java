package com.uiautomation.testscripts;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;
import org.testng.annotations.BeforeTest;
import com.uiautomation.base.LoginBase;
import com.uiautomation.dataprovider.DataProviderParameter;
import com.uiautomation.setup.SetUpBrowser;

public class LoginTest extends SetUpBrowser{
	WebDriver driver;
	LoginBase  LoginBase=new LoginBase(driver);
	
  @Test(description = "Login to dummy site",dataProviderClass = com.uiautomation.dataprovider.ExcelFileDataProvider.class, dataProvider = "getDataFromExcelFile")
  @DataProviderParameter("FileName=test.xls;Sheet=Sheet1;TableName=LoginData")
  public void loginToDummyTest(String userName,String password) throws Exception {
	  System.out.println("userName::"+userName +","+"password::"+password);
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
