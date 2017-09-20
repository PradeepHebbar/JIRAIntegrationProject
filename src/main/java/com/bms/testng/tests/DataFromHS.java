package com.bms.testng.tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class DataFromHS {
	public static void main(String[] args) throws IOException {

    String excelFilePath = "C:/OBI-Test-Tools-AutoLoadData.xlsx";
     
    try {
        FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
        Workbook workbook = WorkbookFactory.create(inputStream);

        Sheet sheet = workbook.getSheet("KPIs");

          int rowCount = sheet.getLastRowNum();

            Row row = sheet.createRow(++rowCount);

            int columnCount = 6;
             
            Cell cell = row.createCell(columnCount);
            cell.setCellValue(rowCount);
            String field = "hello";
                cell = row.createCell(++columnCount);
                    cell.setCellValue((String) field);


        inputStream.close();

        FileOutputStream outputStream = new FileOutputStream("C:/OBI-Test-Tools-AutoLoadData.xlsx");
        workbook.write(outputStream);

        outputStream.close();
         
    } catch (IOException | EncryptedDocumentException
            | InvalidFormatException ex) {
        ex.printStackTrace();
    }
}
//		
//		
//	InputStream is = new FileInputStream(new File("C:/OBI-Test-Tools-AutoLoadData.xlsx"));
//	
//	Workbook workbook = StreamingReader.builder()
//						.rowCacheSize(100)    // number of rows to keep in memory (defaults to 10)
//				        .bufferSize(4096)     // buffer size to use when reading InputStream to file (defaults to 1024)
//				        .open(is);
//	//Sheet sheet = workbook.getSheet("KPIs");
//	Sheet sheet =( org.apache.poi.ss.usermodel.Sheet) workbook.getSheet("KPIs");
//	Row r =  sheet.getRow(5);
//		  System.out.println("RowNumber "+r.getRowNum());
//		  int RowNumber = r.getRowNum();
//		  Cell c = r.createCell((4));
////		  System.out.println("ColumnNumber "+c.getColumnIndex());
////		  int ColumnNumber = c.getColumnIndex();
////		  value = c.getStringCellValue();
//	      c.setCellValue("tet");
////	      if(RowNumber == rowNum ){		    	  	    	  
////	    	  break;		    	  
////	      }
//	OutputStream out = new FileOutputStream(new File("C:/OBI-Test-Tools-AutoLoadData.xlsx"));
//	workbook.write(out);
//	out.flush();
//	out.close();
//}
}