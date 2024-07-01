package api.utilities;

import java.io.FileInputStream;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLUtility {
	FileInputStream fi;
	Workbook wb;
	Sheet sheet;
	Row row;
	Cell cell;
	String path;

	public XLUtility(String path) {
		this.path = path;
	}

	public int getRowCount(String sheetName) {
		try {
			fi = new FileInputStream(path);
			wb = new XSSFWorkbook(fi);
			sheet = wb.getSheet(sheetName);
			int rowCount = sheet.getLastRowNum();
			wb.close();
			fi.close();
			return rowCount;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	public int getCellCount(String sheetName, int rownum) {
		try {
			fi = new FileInputStream(path);
			wb = new XSSFWorkbook(fi);
			sheet = wb.getSheet(sheetName);
			row = sheet.getRow(rownum);
			int cellCount = row.getLastCellNum();
			wb.close();
			fi.close();
			return cellCount;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	public String getCellData(String sheetName, int rownum, int colnum) {
		try {
			fi = new FileInputStream(path);
			wb = new XSSFWorkbook(fi);
			sheet = wb.getSheet(sheetName);
			row = sheet.getRow(rownum);
			cell = row.getCell(colnum);
			String data;
			try {
				DataFormatter formatter = new DataFormatter();
				data = formatter.formatCellValue(cell);
			} catch (Exception e) {
				data = "";
			}
			wb.close();
			fi.close();
			return data;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
}
