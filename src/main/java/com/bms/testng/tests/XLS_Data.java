package com.bms.testng.tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.automation.framework.exceptions.DriverScriptException;
import com.automation.framework.pojs.TestDriver;
import com.automation.framework.utilities.ObjectRead;
import com.bms.rwr.utilities.CucumberDriver;
import com.monitorjbl.xlsx.StreamingReader;

public class XLS_Data {

	private static Workbook wb;
	public static String value;
	
	TestDriver testDriver;
	public XLS_Data() throws DriverScriptException {
		testDriver = CucumberDriver.testDriver;
		new ObjectRead(CucumberDriver.testDriver,this.getClass().getSimpleName());
	}	
	
	public String getExcelStringData(int rowNum, int columnNum,String SheetName ) throws FileNotFoundException{
		
		
		String excelFilePath = testDriver.getGlobalParamMap().get("excelRefSpotFire");
		
		//InputStream is = new FileInputStream(new File("config\\OBI-Test-Tools-AutoLoadData.xlsx"));
		InputStream is = new FileInputStream(new File(excelFilePath));
		Workbook workbook = StreamingReader.builder()
							.rowCacheSize(100)    // number of rows to keep in memory (defaults to 10)
					        .bufferSize(4096)     // buffer size to use when reading InputStream to file (defaults to 1024)
					        .open(is);
		//Sheet sheet = workbook.getSheet("KPIs");
		Sheet sheet = workbook.getSheet(SheetName);
		for (Row r : sheet) {							  
			  System.out.println("RowNumber "+r.getRowNum());
			  int RowNumber = r.getRowNum();
			  Cell c = r.getCell(columnNum);
			  System.out.println("ColumnNumber "+c.getColumnIndex());
			  int ColumnNumber = c.getColumnIndex();
			  value = c.getStringCellValue();
		      System.out.println(c.getStringCellValue());
		      if(RowNumber == rowNum ){		    	  	    	  
		    	  break;		    	  
		      }
		}		
		return value;
	}
	
/*	public static void main(String[] args) throws FileNotFoundException{
		XLS_Data_old xd = new XLS_Data_old();
		String recevicedVal = xd.getExcelStringData(1, 1, "KPIs");
		System.out.println("--------------------");
		System.out.println(recevicedVal);
		System.out.println("--------------------");
	}*/
}
