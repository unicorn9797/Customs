package util;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;

public class HsCodeXlsImporter {

    public static void main(String[] args) {
        String xlsFilePath = "src/main/resources/hs_files/海關進口稅則資料2025.xls"; // XLS 路徑

        int successCount = 0;
        int failCount = 0;

        try (Connection conn = DataBaseConnectionPool.getDataBaseConnection();
             FileInputStream fis = new FileInputStream(new File(xlsFilePath));
             HSSFWorkbook workbook = new HSSFWorkbook(fis)) {

            HSSFSheet sheet = workbook.getSheetAt(0);

            for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) { // 跳過標題列
                HSSFRow row = sheet.getRow(rowIndex);
                if (row == null) continue;

                try {
                    String hsCode = getCellString(row.getCell(0));
                    String descZh = getCellString(row.getCell(1));
                    String descEn = getCellString(row.getCell(2));
                    double dutyRate = parsePercent(getCellString(row.getCell(3)));
                    double vatRate = parsePercent(getCellString(row.getCell(5)));

                    String sql = "INSERT INTO customs.hs_code " +
                                 "(hs_code, description_zh, description_en, duty_rate, vat_rate) " +
                                 "VALUES (?, ?, ?, ?, ?) " +
                                 "ON DUPLICATE KEY UPDATE description_zh=?, description_en=?, duty_rate=?, vat_rate=?";
                    try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                        stmt.setString(1, hsCode);
                        stmt.setString(2, descZh);
                        stmt.setString(3, descEn);
                        stmt.setDouble(4, dutyRate);
                        stmt.setDouble(5, vatRate);

                        stmt.setString(6, descZh);
                        stmt.setString(7, descEn);
                        stmt.setDouble(8, dutyRate);
                        stmt.setDouble(9, vatRate);

                        stmt.executeUpdate();
                    }

                    successCount++;
                } catch (Exception e) {
                    System.out.println("[警告] 第 " + (rowIndex + 1) + " 行匯入失敗: " + e.getMessage());
                    failCount++;
                }
            }

            System.out.println("匯入完成！");
            System.out.println("成功筆數: " + successCount + "，失敗筆數: " + failCount);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 讀取 Cell 字串，兼容新版 POI
    private static String getCellString(Cell cell) {
        if (cell == null) return "";
        CellType type = cell.getCellType();

        switch (type) {
            case STRING:
                return cell.getStringCellValue().trim();
            case NUMERIC:
                double num = cell.getNumericCellValue();
                // 如果是整數，去掉小數點
                if (num == Math.floor(num)) {
                    return String.valueOf((long) num);
                } else {
                    return String.valueOf(num);
                }
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            default:
                return "";
        }
    }

    // 解析百分比字串 "2.5%" → 2.5
    private static double parsePercent(String str) {
        if (str == null || str.isEmpty()) return 0.0;
        str = str.replace("%", "").trim();
        try {
            return Double.parseDouble(str);
        } catch (Exception e) {
            return 0.0;
        }
    }
}
