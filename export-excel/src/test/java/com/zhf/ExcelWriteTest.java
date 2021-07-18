package com.zhf;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.joda.time.DateTime;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelWriteTest {

    String PATH = "D:\\zhfGitRepository\\export-excel\\";

    @Test
    public void testWrite03() throws IOException {
        // 1. 创建一个工作簿
        Workbook workbook = new HSSFWorkbook();
        // 2. 创建一个工作表
        Sheet sheet = workbook.createSheet("狂神观众统计表");
        // 3. 创建一个行
        Row row1 = sheet.createRow(0);
        // 4. 创建一个单元格
        Cell cell = row1.createCell(0);
        cell.setCellValue("今日新增观众");

        Cell cell2 = row1.createCell(1);
        cell2.setCellValue(666);

        // 第二行
        Row row2 = sheet.createRow(1);
        Cell cell21 = row2.createCell(0);
        cell21.setCellValue("统计时间");
        Cell cell22 = row2.createCell(1);
        String time = new DateTime().toString("yyyy-MM-dd HH:mm:ss");
        cell22.setCellValue(time);

        // 生成一张表（IO 流)
        FileOutputStream fileOutputStream = new FileOutputStream(PATH + "狂神观众统计表03.xls");

        workbook.write(fileOutputStream);

        // 关闭流
        fileOutputStream.close();

        System.out.println("Excel 生成完毕！");
    }

    @Test
    public void testWrite08() throws IOException {
        // 1. 创建一个工作簿
        Workbook workbook = new XSSFWorkbook();
        // 2. 创建一个工作表
        Sheet sheet = workbook.createSheet("狂神观众统计表");
        // 3. 创建一个行
        Row row1 = sheet.createRow(0);
        // 4. 创建一个单元格
        Cell cell = row1.createCell(0);
        cell.setCellValue("今日新增观众");

        Cell cell2 = row1.createCell(1);
        cell2.setCellValue(666);

        // 第二行
        Row row2 = sheet.createRow(1);
        Cell cell21 = row2.createCell(0);
        cell21.setCellValue("统计时间");
        Cell cell22 = row2.createCell(1);
        String time = new DateTime().toString("yyyy-MM-dd HH:mm:ss");
        cell22.setCellValue(time);

        // 生成一张表（IO 流)
        FileOutputStream fileOutputStream = new FileOutputStream(PATH + "狂神观众统计表07.xlsx");

        workbook.write(fileOutputStream);

        // 关闭流
        fileOutputStream.close();

        System.out.println("Excel 生成完毕！");
    }

    @Test
    public void testWrite03BigData() throws IOException {
        // 时间
        long begin = System.currentTimeMillis();

        // 创建一个工作簿
        Workbook workbook = new HSSFWorkbook();
        // 创建表
        Sheet sheet = workbook.createSheet();
        // 写入数据
        for(int rowNum = 0; rowNum < 65536; rowNum++){
            Row row = sheet.createRow(rowNum);
            for(int cellNum = 0; cellNum < 10; cellNum++){
                Cell cell = row.createCell(cellNum);
                cell.setCellValue(cellNum);
            }
        }
        System.out.println("over");
        FileOutputStream outputStream = new FileOutputStream(PATH + "testWrite03BigData.xls");
        workbook.write(outputStream);
        outputStream.close();
        long end = System.currentTimeMillis();
        System.out.println((double) (end - begin) / 1000);
    }

    // 耗时较长
    @Test
    public void testWrite07BigData() throws IOException {
        // 时间
        long begin = System.currentTimeMillis();

        // 创建一个工作簿
        Workbook workbook = new XSSFWorkbook();
        // 创建表
        Sheet sheet = workbook.createSheet();
        // 写入数据
        for(int rowNum = 0; rowNum < 65538; rowNum++){
            Row row = sheet.createRow(rowNum);
            for(int cellNum = 0; cellNum < 10; cellNum++){
                Cell cell = row.createCell(cellNum);
                cell.setCellValue(cellNum);
            }
        }
        System.out.println("over");
        FileOutputStream outputStream = new FileOutputStream(PATH + "testWrite07BigData.xlsx");
        workbook.write(outputStream);
        outputStream.close();
        long end = System.currentTimeMillis();
        System.out.println((double) (end - begin) / 1000);
    }

    @Test
    public void testWrite07BigDataS() throws IOException {
        // 时间
        long begin = System.currentTimeMillis();

        // 创建一个工作簿
        Workbook workbook = new SXSSFWorkbook();
        // 创建表
        Sheet sheet = workbook.createSheet();
        // 写入数据
        for(int rowNum = 0; rowNum < 65539; rowNum++){
            Row row = sheet.createRow(rowNum);
            for(int cellNum = 0; cellNum < 10; cellNum++){
                Cell cell = row.createCell(cellNum);
                cell.setCellValue(cellNum);
            }
        }
        System.out.println("over");
        FileOutputStream outputStream = new FileOutputStream(PATH + "testWrite07BigDataS.xlsx");
        workbook.write(outputStream);
        outputStream.close();
        // 清除临时文件
        ((SXSSFWorkbook) workbook).dispose();
        long end = System.currentTimeMillis();
        System.out.println((double) (end - begin) / 1000);
    }
}
