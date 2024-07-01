package api.utilities;

import org.testng.annotations.DataProvider;

public class DataProviders {

	@DataProvider(name = "Data")
	public Object[][] getAllData() {
		String path = System.getProperty("user.dir") + "//TestData//Rest_Assured_Test_Data.xlsx";
		XLUtility xl = new XLUtility(path);

		int rowCount = xl.getRowCount("Sheet1");
		int colCount = xl.getCellCount("Sheet1", 1);

		Object[][] data = new Object[rowCount][colCount];

		for (int i = 1; i <= rowCount; i++) {
			for (int j = 0; j < colCount; j++) {
				data[i - 1][j] = xl.getCellData("Sheet1", i, j);
			}
		}
		return data;
	}

	@DataProvider(name = "UserNames")
	public Object[] getUserNames() {
		String path = System.getProperty("user.dir") + "//TestData//Rest_Assured_Test_Data.xlsx";
		XLUtility xl = new XLUtility(path);

		int rowCount = xl.getRowCount("Sheet1");
		Object[] userNames = new Object[rowCount];

		for (int i = 1; i <= rowCount; i++) {
			userNames[i - 1] = xl.getCellData("Sheet1", i, 1); // Assuming usernames are in the second column
		}
		return userNames;
	}
}
