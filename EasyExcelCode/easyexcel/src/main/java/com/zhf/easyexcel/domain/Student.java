package com.zhf.easyexcel.domain;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 学生实体类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ContentRowHeight()     // 内容行高
@HeadRowHeight()        // 表头行高
public class Student {
    /**
     * 学生姓名
     */
    @ExcelProperty(value = "学生姓名",index = 0)
    @ColumnWidth(20)
    private String name;

    /**
     * 学生出生日期
     */
    @ExcelProperty(value = "出生日期",index = 1)
    @ColumnWidth(20)
    private Date birthday;

    /**
     * 学生性别
     */
    @ExcelProperty(value = "学生性别",index = 2)
    @ColumnWidth(20)
    private String gender;


    /**
     * id
     */
    @ExcelProperty(value = "学生编号",index = 3)
    @ColumnWidth(20)
    @ExcelIgnore
    private String id;
}
