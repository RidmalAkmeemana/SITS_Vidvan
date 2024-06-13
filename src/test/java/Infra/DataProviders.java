package Infra;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class DataProviders {

    private Map<String, Object[]> getDataFromSheetAsMap(String fileName, int sheetIndex, int keyColumnIndex) throws IOException {
        String filePath = "../SITS_Vidvan/src/test/resources/" + fileName;
        FileInputStream fis = new FileInputStream(filePath);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheetAt(sheetIndex);

        int rows = sheet.getLastRowNum();
        int cols = sheet.getRow(0).getLastCellNum();

        Map<String, Object[]> dataMap = new HashMap<>();
        for (int i = 1; i <= rows; i++) {
            Object[] rowData = new Object[cols];
            for (int j = 0; j < cols; j++) {
                XSSFCell cell = sheet.getRow(i).getCell(j);
                rowData[j] = (cell == null) ? "" : cell.toString();
            }
            String key = rowData[keyColumnIndex].toString();
            dataMap.put(key, rowData);
        }

        workbook.close();
        fis.close();

        return dataMap;
    }

    @DataProvider(name = "loginCredentials")
    public Object[][] getLoginData() throws IOException {
        return getDataFromSheetAsMap("TestData.xlsm", 1, 0).values().toArray(new Object[0][0]);
    }

    @DataProvider(name = "getCourseID")
    public Object[][] getCourseID() throws IOException {
        return getDataFromSheetAsMap("TestData.xlsm", 2, 0).values().toArray(new Object[0][0]);
    }

    @DataProvider(name = "getEmails")
    public Object[][] getEmails() throws IOException {
        return getDataFromSheetAsMap("TestData.xlsm", 3, 0).values().toArray(new Object[0][0]);
    }
}