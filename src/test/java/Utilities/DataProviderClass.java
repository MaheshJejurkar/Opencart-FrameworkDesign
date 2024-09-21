package Utilities;

import org.testng.annotations.DataProvider;

public class DataProviderClass {
	
	@DataProvider(name = "getLoginData")
	public String[][] getLoginData() throws Exception {
		String path = ".\\testData\\Opencart_LoginData.xlsx";

		ExcelUtility excelutility = new ExcelUtility(path);

		int rowcount = excelutility.getRowCount("Sheet1");
		int cellcount = excelutility.getCellCount("Sheet1", 1);

		String logindata[][] = new String[rowcount - 1][cellcount];

		for (int r = 1; r < rowcount; r++) {
			for (int c = 0; c < cellcount; c++) {
				logindata[r-1][c] = excelutility.getCellData("Sheet1", r, c);
			}
		}
		return logindata;
	}
}
