package com.zhf.easyexcel.service.impl;

import com.zhf.easyexcel.domain.Student;
import com.zhf.easyexcel.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    public void readExcel(List<Student> studentList) {
        for (Student student : studentList) {
            System.out.println("student:" + student);

        }
    }
}
