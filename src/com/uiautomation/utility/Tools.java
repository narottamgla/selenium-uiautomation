package com.uiautomation.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.uiautomation.setup.SetUpBrowser;

public class Tools extends SetUpBrowser {
	
	/**
	 * This method use to capture screenshots & store on defined location and also add screenshot to reportNg report.
	 * 
	 * @author snarottam
	 * @param  ITestResult
	 */
	
	public static void CaptureScreenShotAndPutInResult(ITestResult tr) {
		try {
			String parent = Constants.REPORT_SOURCE_PATH;
			String imgfile = Constants.SCREENCAPTURE_FOLDER_NAME +tr.getMethod().getMethodName()+"_"+ new SimpleDateFormat("dd-MM-yyyy_HH-ss-SSS").format(new Date().getTime())+ ".png";
			
			System.out.println("Img File path"+imgfile);
			File scrFile = null;
			if(tr.getInstance().toString().contains("com.uiautomation.testscripts.")){
			 scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File(parent, imgfile));
			}			
			System.setProperty("org.uncommons.reportng.escape-output", "false");
			Reporter.log("<a href=" + imgfile + ">" + "<img src=" + imgfile
					+ " alt=\"\"" + "height='100' width='100'/> " + "<br />");
			Reporter.setCurrentTestResult(null);
		}catch(Exception a) {
			a.printStackTrace();
		}
	}
	
	public static void readExcelData(){
		//to-do
		
	}
	
	public static void writeExcelData(String PropertyName){
		//to-do
		
	}
	
	public static String readPropertyFile(String PropertyName){
		Properties prop = new Properties();
		InputStream input = null;
		String Config_Path=Constants.CONFIG_FILE_PATH;
	 
		try {
			input = new FileInputStream(Config_Path);
			// load a properties file
			prop.load(input);
	 
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}	
		return prop.getProperty(PropertyName);
		
	}
	
	public static void writePropertyFile(String PropertyName,String PropertyValue){
		Properties prop = new Properties();
		OutputStream output = null;
		String Config_Path=Constants.CONFIG_FILE_PATH;
		try {
			output = new FileOutputStream(Config_Path);
			// set the properties value
			prop.setProperty(PropertyName, PropertyValue);
			prop.store(output, null);
	 
		} catch (IOException io) {
			io.printStackTrace();
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}	
	}
}

