package com.bms.testng.tests;
/*
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import com.monitorjbl.xlsx.StreamingReader;

public class ReadExcel {
	private static Workbook wb;
	public static void main(String[] args) throws IOException, InvalidFormatException {
		
		
	InputStream is = new FileInputStream(new File("C:/OBI_Test_Tools_AutoLoadData.xlsx"));
	Workbook workbook = StreamingReader.builder()
						.rowCacheSize(100)    // number of rows to keep in memory (defaults to 10)
				        .bufferSize(4096)     // buffer size to use when reading InputStream to file (defaults to 1024)
				        .open(is);
	Sheet sheet = workbook.getSheet("Projects");
	for (Row r : sheet) {
						  
		  System.out.println("RowNumber "+r.getRowNum());
		  Cell c = r.getCell(1);

		  //System.out.println("ColumnNumber "+c.getColumnIndex());
//		  for (Cell c : r) {
		  //System.out.println(c.getStringCellValue());
	      //System.out.println(c.getStringCellValue());
//					    }
	}


	}

}
*/