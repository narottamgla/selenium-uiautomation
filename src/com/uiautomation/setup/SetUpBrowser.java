package com.uiautomation.setup;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterSuite;

public class SetUpBrowser {
	protected static String Project_URL;
	protected static WebDriver driver;
	public WebDriver setUpWebDriver(String BROWSER){
		Project_URL="http://www.adactin.com/HotelApp/";
		if (BROWSER.equals("FF")) {
			driver = new FirefoxDriver();
			
		} else if (BROWSER.equals("CH")) {
			String path = "lib/chromedriver.exe";
			System.setProperty("webdriver.chrome.driver", path);
			driver = new ChromeDriver();
		} else if (BROWSER.equals("IE")) {

			String path = "lib/IEDriverServer.exe";
			System.setProperty("webdriver.ie.driver", path);
			driver = new InternetExplorerDriver();

		} else {
			throw new RuntimeException("Browser type unsupported");
		}
		driver.get("http://www.adactin.com/HotelApp/");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		return driver;
	}

	@AfterSuite(alwaysRun = true)
	public void tearDown() throws Exception {
		try {

			driver.quit();

		} catch (Throwable e) {
			e.printStackTrace();
		}
		
	}
		
	}


