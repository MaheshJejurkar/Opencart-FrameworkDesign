package utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtilityClass {
	String path;
	public FileInputStream fis;
	public FileOutputStream fos;
	public XSSFWorkbook workbook;
	public XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;
	public XSSFCellStyle cell_style;

	public ExcelUtilityClass(String path) {
		this.path = path;
	}
	
	public int getRowCount(String sheetname) throws Exception {
		fis = new FileInputStream(path);
		workbook = new XSSFWorkbook(fis);
		sheet = workbook.getSheet(sheetname);
		int rowcount = sheet.getPhysicalNumberOfRows();
		workbook.close();
		fis.close();
		return rowcount;
	}
	
	public int getCellCount(String sheetname, int rownum) throws Exception {
		fis = new FileInputStream(path);
		workbook = new XSSFWorkbook(fis);
		sheet = workbook.getSheet(sheetname);
		int cellcount = sheet.getRow(rownum).getPhysicalNumberOfCells();
		workbook.close();
		fis.close();
		return cellcount;
	}
	
	public String getCellData(String sheetname, int rownum, int cellnum) throws Exception {
		fis = new FileInputStream(path);
		workbook = new XSSFWorkbook(fis);
		sheet = workbook.getSheet(sheetname);
		String data	= sheet.getRow(rownum).getCell(cellnum).getStringCellValue();
		workbook.close();
		fis.close();
		return data;
	}
}
