package com.zhf;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.joda.time.DateTime;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;

public class ExcelReadTest {


    String PATH = "D:\\zhfGitRepository\\export-excel\\";

    @Test
    public void testRead03() throws Exception{
        // 获取文件流
        FileInputStream inputStream = new FileInputStream(PATH + "狂神观众统计表03.xls");
        // 1. 创建一个工作簿， 使用Excel 能操作的 这边都可以进行操作！
        Workbook workbook = new HSSFWorkbook(inputStream);
        // 2. 得到表
        Sheet sheet = workbook.getSheetAt(0);
        // 3. 得到行
        Row row = sheet.getRow(0);
        // 4. 得到列
        Cell cell = row.getCell(0);

        // 读取值得时候，一定要注意类型！
        // getStringCellValue 获取字符串类型
        String stringCellValue = cell.getStringCellValue();

        Cell cell1 = row.getCell(1);
        System.out.println(cell.getStringCellValue());
        System.out.println(stringCellValue);
        inputStream.close();
    }

    @Test
    public void testRead07() throws Exception{
        // 获取文件流
        FileInputStream inputStream = new FileInputStream(PATH + "狂神观众统计表07.xlsx");
        // 1. 创建一个工作簿， 使用Excel 能操作的 这边都可以进行操作！
        Workbook workbook = new XSSFWorkbook(inputStream);
        // 2. 得到表
        Sheet sheet = workbook.getSheetAt(0);
        // 3. 得到行
        Row row = sheet.getRow(0);
        // 4. 得到列
        Cell cell = row.getCell(0);

        // 读取值得时候，一定要注意类型！
        // getStringCellValue 获取字符串类型
        String stringCellValue = cell.getStringCellValue();

        Cell cell1 = row.getCell(1);
        System.out.println(cell.getStringCellValue());
        System.out.println(stringCellValue);
        inputStream.close();
    }

    @Test
    public void testCellType() throws IOException {
        // 获取文件流
        FileInputStream inputStream = new FileInputStream(PATH + "明细表.xls");
        // 1. 创建一个工作簿， 使用Excel 能操作的 这边都可以进行操作！
        Workbook workbook = new HSSFWorkbook(inputStream);

        // 获取标题内容
        Sheet sheet = workbook.getSheetAt(0);
        // 获取标题内容
        Row rowTitle = sheet.getRow(0);
        if(rowTitle != null){
            // 一定要掌握
            int cellsCount = rowTitle.getPhysicalNumberOfCells();
            for (int cellNum = 0; cellNum < cellsCount; cellNum++){
                Cell cell = rowTitle.getCell(cellNum);
                if(cell != null){
                    int cellType = cell.getCellType();
                    String cellValue = cell.getStringCellValue();
                    System.out.print(cellValue + " | ");
                }
            }
            System.out.println();
        }

        // 获取表中的内容
        int rowCount = sheet.getPhysicalNumberOfRows();

        for(int rowNumber = 0; rowNumber < rowCount; rowNumber++){
            Row rowData = sheet.getRow(rowNumber);
            if (rowData != null) {
                // 读取列
                int cellCount = rowTitle.getPhysicalNumberOfCells();
                for(int cellNumber = 0; cellNumber < cellCount; cellNumber++){
                    System.out.print("[" + (rowNumber+1) +"-" + (cellNumber+1) + "]");
                    Cell cell = rowData.getCell(cellNumber);
                    // 匹配列的数据类型
                    if (cell != null) {
                        int cellType = cell.getCellType();
                        String cellValue = "";
                        switch (cellType){
                            case HSSFCell.CELL_TYPE_STRING:     // 字符串
                                System.out.println("【String】");
                                cellValue = cell.getStringCellValue();
                                break;
                            case HSSFCell.CELL_TYPE_BOOLEAN:    // 布尔
                                System.out.println("【BOOLEAN】");
                                cellValue = String.valueOf(cell.getBooleanCellValue());
                                break;
                            case HSSFCell.CELL_TYPE_BLANK:      // 空
                                System.out.println("【BLANK】");
                                break;
                            case HSSFCell.CELL_TYPE_NUMERIC:    // 数值（日期、普通数字）
                                System.out.println("【NUMERIC】");
                                if(HSSFDateUtil.isCellDateFormatted(cell)){     // 日期
                                    System.out.println("【日期】");
                                    Date date = cell.getDateCellValue();
                                    cellValue = new DateTime(date).toString("yyyy-MM-dd");
                                }else{
                                    // 不是日期格式，防止数字过长！
                                    System.out.println("【转换为字符串输出】");
                                    cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                                    cellValue = cell.toString();
                                }

                                break;
                            case HSSFCell.CELL_TYPE_ERROR:
                                System.out.println("【数据类型错误】");
                                break;
                        }

                        System.out.println(cellValue);
                    }
                }
            }
        }
        inputStream.close();
    }

    /**
     * 获取EXcel中的公式
     * @throws Exception
     */
    @Test
    public void testFormula() throws Exception{
        FileInputStream inputStream = new FileInputStream(PATH + "公式.xls");
        Workbook workbook = new HSSFWorkbook(inputStream);
        Sheet sheet = workbook.getSheetAt(0);

        Row row = sheet.getRow(4);
        Cell cell = row.getCell(0);

        // 拿到计算公式 eval
        FormulaEvaluator formulaEvaluator = new HSSFFormulaEvaluator((HSSFWorkbook) workbook);

        // 输出单元格内容
        int cellType = cell.getCellType();
        switch (cellType){
            case Cell.CELL_TYPE_FORMULA:    // 公式
                String formula = cell.getCellFormula();
                System.out.println(formula);

                // 计算
                CellValue evaluate = formulaEvaluator.evaluate(cell);
                System.out.println(evaluate.formatAsString());
                break;
        }
    }
}
