package com.bms.testng.tests;

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

	import com.automation.framework.exceptions.DriverScriptException;
	import com.monitorjbl.xlsx.StreamingReader;

	public class Xls_bigFileRead {
		private static Workbook wb;
		public static void main(String[] args) throws IOException, InvalidFormatException, DriverScriptException {
			
//			InputStream inputStream = new BufferedInputStream(new FileInputStream(
//					new File("C:/OBIAutoLoadData.xlsx")),1024*1024);
//			wb = WorkbookFactory.create(inputStream);
//			System.out.println(wb.getSheet("KPIs").getRow(1).getCell(1));
			
					  InputStream is = new FileInputStream(new File("C:/OBIAutoLoadData.xlsx"));
					Workbook workbook = StreamingReader.builder()
					        .rowCacheSize(100)    // number of rows to keep in memory (defaults to 10)
					        .bufferSize(4096)     // buffer size to use when reading InputStream to file (defaults to 1024)
					        .open(is);
					Sheet sheet = workbook.getSheet("KPIs");
//						  System.out.println(.getSheet("KPIs").getRow(1).getCell(1));
						  for (Row r : sheet) {
							  
							  System.out.println("RowNumber "+r.getRowNum());
							  Cell c = r.getCell(1);
//						    for (Cell c : r) {
						    	//System.out.println(c.getStringCellValue());
						      System.out.println(c.getStringCellValue());
//						    }
						}
	/*		File refFile = new File("C:\\Users\\Konkalar\\Desktop\\RWR_Automation\\Automation\\Brillio_Cucumber_D2_Framework\\config\\OBI-Test-Tools-AutoLoadData.xlsx");
		
			FileInputStream fis = new FileInputStream("C:/OBI-Test-Tools-AutoLoadData.xlsx");
			Workbook wb = WorkbookFactory.create(fis);
			Sheet mySheet = wb.getSheet("KPIs");	
			Row row = mySheet.getRow(2);
			Cell cell = row.getCell(2);
			
			double value = cell.getNumericCellValue();
			System.out.println(value);*/
			
		//	File refFile = new File("C:\\Users\\Konkalar\\Desktop\\RWR_Automation\\Automation\\Brillio_Cucumber_D2_Framework\\config\\OBI-Test-Tools-AutoLoadData.xlsx");
//			TestDriver driver = new TestDriver();
//			XLUtil xlUtil = new XLUtil();
//			xlUtil.openWorkBook(driver, new File("C:/OBIAutoLoadData.xlsx"));
//			System.out.println(xlUtil.getCellValue(driver, "KPIs", 1 ,1));
//			FileInputStream fis = new FileInputStream("C:/OBIAutoLoadData.xlsx");
//			Workbook wb = WorkbookFactory.create(fis);
//			Sheet mySheet = wb.getSheet("KPIs");	
//			Row row = mySheet.getRow(1);
//			Cell cell = row.getCell(1);
//			
//			//int value = (int)cell.getNumericCellValue();
//			String value = cell.getStringCellValue();
//			System.out.println(value);

		}

	}

