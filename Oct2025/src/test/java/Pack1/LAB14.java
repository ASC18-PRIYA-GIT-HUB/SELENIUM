package Pack1;

import java.io.File;
import java.io.FileInputStream;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class LAB14 {

    public static Object[][] getExcelData(String filePath) {
        Object[][] data = null;

        try {
            File file = new File(filePath);
            if (!file.exists()) {
                System.out.println("Excel file not found: " + filePath);
                return new Object[0][0];
            }

            System.out.println("File exists: " + file.exists());
            System.out.println("File path: " + file.getAbsolutePath());
            System.out.println("File extension: " + (file.getName().endsWith(".xlsx") ? "xlsx" : "NOT XLSX"));

            FileInputStream fis = new FileInputStream(file);
            Workbook workbook = new XSSFWorkbook(fis);
            Sheet sheet = workbook.getSheetAt(0);

            int totalRows = sheet.getPhysicalNumberOfRows();
            Row headerRow = sheet.getRow(0);
            int colCount = headerRow.getLastCellNum();

            data = new Object[totalRows - 1][colCount];
            int validRowCount = 0;

            for (int i = 1; i < totalRows; i++) { // skip header
                Row row = sheet.getRow(i);
                if (row == null) continue;

                boolean isEmptyRow = true;
                Object[] rowData = new Object[colCount];

                for (int j = 0; j < colCount; j++) {
                    Cell cell = row.getCell(j, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    String value = getCellValueAsString(cell);
                    rowData[j] = value;
                    if (!value.isEmpty()) isEmptyRow = false;
                }

                if (!isEmptyRow) {
                    data[validRowCount++] = rowData;
                }
            }

            workbook.close();
            fis.close();

            // Trim data to valid rows
            Object[][] finalData = new Object[validRowCount][colCount];
            System.arraycopy(data, 0, finalData, 0, validRowCount);

            System.out.println("Total valid data rows read: " + validRowCount);
            return finalData;

        } catch (Exception e) {
            e.printStackTrace();
            return new Object[0][0];
        }
    }

    private static String getCellValueAsString(Cell cell) {
        if (cell == null) return "";

        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue().trim();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue().toString();
                } else {
                    double val = cell.getNumericCellValue();
                    if (val == (long) val) {
                        return String.valueOf((long) val);
                    } else {
                        return String.valueOf(val);
                    }
                }
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            case BLANK:
            default:
                return "";
        }
    }
}
