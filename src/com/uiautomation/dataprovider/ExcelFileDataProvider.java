package com.uiautomation.dataprovider;

import java.io.File;
import java.lang.reflect.Method;
import java.util.Map;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import org.testng.annotations.DataProvider;
import org.testng.log4testng.Logger;
import com.uiautomation.utility.Tools;

public class ExcelFileDataProvider{
	
	private static final Logger log = Logger.getLogger(ExcelFileDataProvider.class);
	@DataProvider(name = "getDataFromExcelFile")
	public static Object[][] fileDataProvider(Method testMethod)
			throws Exception {

		Map<String, String> arguments = DataProviderCommonUtils
				.resolveDataProviderArguments(testMethod);
		
		String fileName = arguments.get("FileName");
		String sSheet = arguments.get("Sheet");
		String sTableName= arguments.get("TableName");
		File xlPath = new File(Tools.readPropertyFile("TestDataPath"));
		String fullFilePath = "";
		fullFilePath = xlPath+"\\"+fileName;
		String[][] tabArray = null;
		Workbook workbk = Workbook.getWorkbook(new File(fullFilePath));
		Sheet sht = workbk.getSheet(sSheet);
		
		int sRow, sCol, eRow, eCol, ci, cj;
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
		
		
	     // System.out.println("startRow="+sRow+", endRow="+eRow+", " + "startCol="+sCol+", endCol="+eCol);
	        tabArray=new String[eRow-sRow-1][eCol-sCol-1];
	        
	        ci=0;
	        for (int m=sRow+1;m<eRow;m++,ci++){
	        	
	            cj=0;
	            for (int n=sCol+1;n<eCol;n++,cj++){
	                tabArray[ci][cj]=sht.getCell(n,m).getContents().trim() ;
	            }
	        }      
	        return(tabArray);
	}

		
}
