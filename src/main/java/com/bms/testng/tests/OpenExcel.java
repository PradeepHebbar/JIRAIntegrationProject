package com.bms.testng.tests;
/*import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.monitorjbl.xlsx.StreamingReader;

public class OpenExcel {
	public static AuroraCon con = new AuroraCon();

	public static void deleteData(Sheet sheet, int colNumToDelete) {

		System.out.println(sheet.getLastRowNum());
		for (int r = 2; r < sheet.getLastRowNum() + 1; r++) {
			Row row = sheet.getRow(r);

			// if no row exists here; then nothing to do; next!
			if (row == null)
				continue;

			// if the row doesn't have this many columns then we are good; next!

			for (int c = colNumToDelete; c < row.getLastCellNum() + 1; c++) {
				Cell cell = sheet.getRow(r).getCell(c);
				if (cell != null) {
					String value = cell.getRichStringCellValue().toString();
					if (!value.isEmpty()) {
						cell.setCellValue("");
					}
				}

			}

		}

	}

	public static void setData(Sheet sheet, int colNumToInsert, int rownum, int colnum, ResultSet rs)
			throws SQLException {
		int row = 0;
		rs.beforeFirst();
		while (rs.next()) {
			row++;
			for (int j = 1; j < colnum + 1; j++) {
				String columnValue = rs.getString(j);
				sheet.getRow(row).createCell(j + colNumToInsert).setCellValue(columnValue);
			}
		}

	}

	public static void main(String[] args) throws IOException, SQLException {
		// TODO Auto-generated method stub
		try {
			ResultSet rs = null;
			int row = 0;
			int col = 0;
			// Read the spreadsheet that needs to be updated
			FileInputStream fileIn = new FileInputStream(new File("C:/OBI_Test_Tools_AutoLoadData.xlsx"));  //Activity_Log.xls
			// Access the workbook
			//HSSFWorkbook wb = new HSSFWorkbook(fileIn);
			XSSFWorkbook xssfwb = new XSSFWorkbook(fileIn);
			Workbook  wb = new SXSSFWorkbook(xssfwb,100);
			// Access the worksheet, so that we can update / modify it.
			//HSSFSheet worksheet = wb.getSheetAt(0);
			//HSSFSheet worksheet = wb.getSheet("Projects"); //Activity_Log
			Sheet worksheet =  wb.getSheet("Projects"); //Activity_Log
			// deleteData( worksheet, 6 );
			//String sqlQuery = "select * from bms_obi_reporting.v_LoadDates;";
			//rs = con.getResultsetFromSql(sqlQuery);
			
			InputStream fileIn = new FileInputStream(new File("C:/OBI_Test_Tools_AutoLoadData.xlsx"));
			Workbook workbook = StreamingReader.builder()
								.rowCacheSize(100)    // number of rows to keep in memory (defaults to 10)
						        .bufferSize(4096)     // buffer size to use when reading InputStream to file (defaults to 1024)
						        .open(fileIn);
			Sheet worksheet = workbook.getSheet("Projects");
			//deleteData( worksheet, 6 );
			
			rs = con.getResultsetFromSql();
			col = con.getColumnCount();
			row = con.getRowCount();

			try {
				setData(worksheet, 6, row, col, rs);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			FileOutputStream fileOut = new FileOutputStream(new File("C:\\OBI_Test_Tools_AutoLoadData.xlsx"));
			wb.write(fileOut);
			fileOut.flush();
			fileIn.close();
			fileOut.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
*/