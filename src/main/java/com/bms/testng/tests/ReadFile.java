package com.bms.testng.tests;
/*import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
 	
import com.monitorjbl.xlsx.StreamingReader;

public class ReadFile {

	private static Workbook wb;
	public static String value;
	
	public String getExcelStringData(int rowNum, int columnNum,String SheetName ) throws FileNotFoundException{
		InputStream is = new FileInputStream(new File("C:/OBI_Test_Tools_AutoLoadData.xlsx"));
		Workbook workbook = StreamingReader.builder()
							.rowCacheSize(100)    // number of rows to keep in memory (defaults to 10)
					        .bufferSize(4096)     // buffer size to use when reading InputStream to file (defaults to 1024)
					        .open(is);
		Sheet sheet = workbook.getSheet("Projects");
		boolean endOfSheet = false;
		for (Row r : sheet) {	
		
			if(endOfSheet)
				break;
			  System.out.println("RowNumber "+r.getRowNum());
			  //int RowNumber = r.getRowNum();
			  //Cell c = r.getCell(columnNum);
			  //System.out.println("ColumnNumber "+c.getColumnIndex());
			  //int ColumnNumber = c.getColumnIndex();
			  for (Cell c : r){
				  
				  if(r.getRowNum()> 1 && c.getColumnIndex() == 0 && c.getStringCellValue().isEmpty())
				  {
					  endOfSheet = true;
					  break;
				  }
					  value = c.getStringCellValue();
					  System.out.println("");
					if(r.getRowNum()> 2  && c.getColumnIndex() > columnNum){
						c.set
					}
}
		      
		      if(RowNumber == rowNum ){		    	  	    	  
		    	  break;		    	  
		      }
		}		
		return value;
	}
	
	public static void main(String[] args) throws FileNotFoundException{
		ReadFile xd = new ReadFile();
		String recevicedVal = xd.getExcelStringData(1, 4, "Projects");
		System.out.println("--------------------");
		System.out.println(recevicedVal);
		System.out.println("--------------------");
	}
}
*/