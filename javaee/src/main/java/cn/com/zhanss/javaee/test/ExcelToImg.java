package cn.com.zhanss.javaee.test;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * excel转图片
 *
 * @author zhanshuchan
 * @date 2024/3/21
 **/
public class ExcelToImg {
    public static void main(String[] args) throws IOException {
        FileInputStream excelFile = new FileInputStream(new File("path_to_your_excel_file.xlsx"));
        Workbook workbook = new XSSFWorkbook(excelFile);
        Sheet datatypeSheet = workbook.getSheetAt(0);

        int totalRows = datatypeSheet.getLastRowNum() - datatypeSheet.getFirstRowNum();
        int totalColumns = datatypeSheet.getRow(0).getLastCellNum();

        // 假设每个单元格的高度和宽度都是固定的
        int cellHeight = 20;
        int cellWidth = 50;
        BufferedImage image = new BufferedImage(totalColumns * cellWidth, totalRows * cellHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = image.createGraphics();

        for (int rowNum = datatypeSheet.getFirstRowNum(); rowNum <= datatypeSheet.getLastRowNum(); rowNum++) {
            Row row = datatypeSheet.getRow(rowNum);
            for (int colNum = row.getFirstCellNum(); colNum < row.getLastCellNum(); colNum++) {
                Cell cell = row.getCell(colNum);
                String cellValue = "";
                switch (cell.getCellType()) {
                    case STRING:
                        cellValue = cell.getStringCellValue();
                        break;
                    case NUMERIC:
                        cellValue = String.valueOf(cell.getNumericCellValue());
                        break;
                    // 处理其他类型的单元格...
                }

                graphics.drawString(cellValue, colNum * cellWidth, rowNum * cellHeight);
            }
        }

        graphics.dispose();
        ImageIO.write(image, "png", new File("output_image.png"));
        workbook.close();
        excelFile.close();
    }
}

