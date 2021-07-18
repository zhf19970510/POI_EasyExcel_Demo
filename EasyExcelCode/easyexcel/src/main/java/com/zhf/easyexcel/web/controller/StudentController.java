package com.zhf.easyexcel.web.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import com.zhf.easyexcel.domain.Student;
import com.zhf.easyexcel.web.listener.WebStudentListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("student")
public class StudentController {

    @Autowired
    WebStudentListener webStudentListener;

    @RequestMapping("read")
    @ResponseBody
    public String readExcel(MultipartFile uploadExcel){

        try {
            // 工作簿
            ExcelReaderBuilder readWorkBook = EasyExcel.read(uploadExcel.getInputStream(), Student.class, webStudentListener);
            // 工作表
            readWorkBook.sheet().doRead();
            // 读

            return "success";
        } catch (IOException e) {
            e.printStackTrace();
            return "fail";
        }

    }

    // 上传
    @RequestMapping("download")
    public void download(HttpServletResponse response) throws IOException {

        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        // 防止中文乱码
        String fileName = URLEncoder.encode("测试下载", "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename*=UTF-8''" + fileName + ".xlsx");

        //响应的输入流
        ServletOutputStream outputStream = response.getOutputStream();

        // workbook
        ExcelWriterBuilder writeWorkBook = EasyExcel.write(outputStream, Student.class);

        // sheet
        writeWorkBook.sheet().doWrite(initData());
    }

    /**
     * 初始好数据
     *
     * @return
     */
    private static List<Student> initData() {
        ArrayList<Student> students = new ArrayList<Student>();
        Student data = new Student();
        for (int i = 0; i < 10; i++) {
            data.setName("杭州黑马学号0" + i);
            data.setBirthday(new Date());
            data.setGender("男");
            students.add(data);
        }
        return students;
    }

}
