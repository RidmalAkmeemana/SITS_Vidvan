package Infra;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class LoadEnv {

    private static String getCellValue(XSSFSheet sheet, int rowIndex, int columnIndex) {
        Cell cell = sheet.getRow(rowIndex).getCell(columnIndex);
        return (cell != null) ? cell.getStringCellValue() : "";
    }

    private static XSSFSheet getSheet(String fileName, int sheetIndex) throws IOException {
        FileInputStream fis = new FileInputStream("../SITS_Vidvan/src/test/resources/" + fileName);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        return workbook.getSheetAt(sheetIndex);
    }

    public static String getEnv() {
        String env = "";
        try {
            XSSFSheet sheet = getSheet("TestData.xlsm", 0);
            env = getCellValue(sheet, 1, 0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return env;
    }

    public static String getUrl() {
        String url = "";
        try {
            XSSFSheet sheet = getSheet("TestData.xlsm", 0);
            url = getCellValue(sheet, 1, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return url;
    }

    public static String getTitle() {
        String title = "";
        try {
            XSSFSheet sheet = getSheet("TestData.xlsm", 0);
            title = getCellValue(sheet, 1, 2);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return title;
    }
}