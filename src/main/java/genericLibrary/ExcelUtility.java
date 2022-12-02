package genericLibrary;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtility {
	private Workbook workbook;

	public void excelFileinitialisation(String excelFilePath) {
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(excelFilePath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			workbook = WorkbookFactory.create(fis);
		} catch (EncryptedDocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Map<String,String> fetchDataFromExcel(String sheetName, String expectedTestCaseName) {
		Sheet sheet = workbook.getSheet(sheetName);
		DataFormatter df =new DataFormatter();
		Map<String,String> map = new HashMap<>();

		for (int i = 0; i <= sheet.getLastRowNum(); i++) {
			if (df.formatCellValue(sheet.getRow(i).getCell(1)).contains(expectedTestCaseName)) {

				for (int j = i; j <= sheet.getLastRowNum(); j++) {
					map.put(df.formatCellValue( sheet.getRow(j).getCell(2)),
							df.formatCellValue( sheet.getRow(j).getCell(3)));

					if (df.formatCellValue(sheet.getRow(j).getCell(2)).equals("####"))
						break;
				
				}
				break;
		}
		}
		return map;
	}
	
	public void writeCellData(String expectedTestCaseName,String sheetName, String status, String excelFilePath) {
		Sheet sheet = workbook.getSheet(sheetName);
		DataFormatter df = new DataFormatter();
		for (int i = 0; i <= sheet.getLastRowNum(); i++) {
			if (df.formatCellValue(sheet.getRow(i).getCell(1)).contains(expectedTestCaseName)) {
				Cell cell = sheet.getRow(i).createCell(4);
				cell.setCellValue(status);
				break;
			}
		}
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(excelFilePath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			workbook.write(fos);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void closeWorkBook() {
		try {
			workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}