package com.uiautomation.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.uiautomation.setup.SetUpBrowser;

public class Tools extends SetUpBrowser {
	
	public static void main (String args[]) throws Exception{
		//writeExcelData("G://Automation2//WorkSpace//selenium-uiautomation//InputTestData", "test.xls", "Sheet1", "LoginData","Password","UserName","narottam","yogita billo");	
	}
	
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
	
	/**
	 * This method use to write to Excel table.
	 * 
	 * @author snarottam
	 * @param  xlPath
	 * @param fileName
	 * @param sSheet 
	 * @param sTableName
	 * @param columnToUpdate 
	 * @param uniqueValueColumn
	 * @param uniqueValueRow
	 * @param updatedCellValue 
	 * 
	 */
	public static void writeExcelData(String xlPath,String fileName,String sSheet,String sTableName,String columnToUpdate,String uniqueValueColumn,String uniqueValueRow,String updatedCellValue) throws Exception{
		String fullFilePath = "";
		fullFilePath = xlPath+"\\"+fileName;   
		Workbook workbk = Workbook.getWorkbook(new File(fullFilePath));
		Sheet sht = workbk.getSheet(sSheet);
		int sRow, sCol, eRow, eCol;
		Cell tableStart = sht.findCell(sTableName);
		sRow = tableStart.getRow();
		sCol = tableStart.getColumn();
		int i = sCol;
		while (sht.getCell(i, sRow).getContents().length() > 0) {
			i++;
			if (i >= sht.getColumns()) {
				break;
			}
		}
		int final_value_of_top_right_coulmn = i;
		int j = sRow;
		while (sht.getCell(sCol + 1, j).getContents().length() > 0) {	
			j++;
			if (j >= sht.getRows()) {
				break;
			}
		}
		int final_top_down_row = j;
		boolean again_null_check = false;
		do {
			boolean null_row_check = false;
			for (int ctr = sCol + 1; ctr <= final_value_of_top_right_coulmn; ctr++) {
				if (ctr >= sht.getColumns() || final_top_down_row >= sht.getRows()) {
					break;
				}
				if (sht.getCell(ctr, final_top_down_row).getContents().length() > 0) {	
					null_row_check = true;
				}
			}
			if (null_row_check) {
				final_top_down_row = final_top_down_row + 1;
				again_null_check = true;
			} else {
				again_null_check = false;
			}

		} while (again_null_check == true);
		  eRow=final_top_down_row;
	      eCol=final_value_of_top_right_coulmn;
	          //column to be updated 
	    	  Cell tou=sht.findCell(columnToUpdate, sCol, sRow, eCol, eRow, true);
	    	  //unique value column 
	    	  Cell rtou=sht.findCell(uniqueValueColumn, sCol, sRow, eCol, eRow, true);
	    	  //unique value row
	    	  Cell uRow=sht.findCell(uniqueValueRow, rtou.getColumn(), sRow, rtou.getColumn(), eRow, true);
	    	  FileInputStream file = new FileInputStream(new File(fullFilePath));
				HSSFWorkbook workbook = new HSSFWorkbook(file);
				HSSFSheet sheet = workbook.getSheet(sSheet);
			org.apache.poi.ss.usermodel.Cell cell = null;
			int	colvalue=tou.getColumn();
			int rowvalue=uRow.getRow();					
				Row row = sheet.getRow(rowvalue);
				if (row == null) {
					row = sheet.createRow(rowvalue);
				}
				// Set updatedCellValue here
				if (updatedCellValue.length() > 0) {
					cell = row.getCell(colvalue, Row.CREATE_NULL_AS_BLANK);
					cell.setCellValue(updatedCellValue);
				}
				file.close();
				FileOutputStream outFile = new FileOutputStream(new File(fullFilePath));
				workbook.write(outFile);
				outFile.close();
	}	
	
	
	/**
	 * This method use to read value from property file.
	 * 
	 * @author snarottam
	 * @param  PropertyName
	 */
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
	
	/**
	 * This method use to Write value to property file.
	 * 
	 * @author snarottam
	 * @param  PropertyName
	 * @param  PropertyValue
	 */
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

