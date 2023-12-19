package webAutomation.webUtilities;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

public class XLUtility implements Closeable {



    private FileInputStream fileInputStream;
    private FileOutputStream fileOutputStream;
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private XSSFRow row;
    private XSSFCell cell;
    private CellStyle style;
    private String path;

    public XLUtility(String path) {
        this.path = path;
    }

    public int getRowCount(String sheetName) throws IOException {
        try (FileInputStream fileInputStream = new FileInputStream(path);
             XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream)) {

            sheet = workbook.getSheet(sheetName);

            if (sheet == null) {
                // Handle the case where the sheet doesn't exist
                throw new IllegalArgumentException("Sheet '" + sheetName + "' not found");
            }

            int rowCount = sheet.getLastRowNum();

            workbook.close();
            return rowCount;

        }
    }




    public int getCellCount(String sheetName, int rowNum) throws IOException {
        try (FileInputStream fileInputStream = new FileInputStream(path);
             XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream)) {

            sheet = workbook.getSheet(sheetName);
            row = sheet.getRow(rowNum);
            return row.getLastCellNum();
        }
    }

    public String getCellData(String sheetName, int rowNum, int colNum) throws IOException {
        try (FileInputStream fileInputStream = new FileInputStream(path);
             XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream)) {

            sheet = workbook.getSheet(sheetName);
            row = sheet.getRow(rowNum);
            cell = row.getCell(colNum);

            DataFormatter formatter = new DataFormatter();
            return formatter.formatCellValue(cell);
        }
    }

    public void setCellData(String sheetName, int rowNum, int colNum, String data) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(path);
        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);

        try {
            sheet = workbook.getSheet(sheetName);

            if (sheet.getRow(rowNum) == null)
                sheet.createRow(rowNum);

            row = sheet.getRow(rowNum);
            cell = row.createCell(colNum);
            cell.setCellValue(data);
        } finally {
            workbook.write(new FileOutputStream(path));
            workbook.close();
            fileInputStream.close();
        }
    }


    @Override
    public void close() throws IOException {
        // Ensure resources are closed
        if (fileInputStream != null) {
            fileInputStream.close();
        }
        if (fileOutputStream != null) {
            fileOutputStream.close();
        }
    }

}
