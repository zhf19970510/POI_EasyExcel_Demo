package com.zhf.easyexcel.service;

import com.zhf.easyexcel.domain.Student;

import java.util.List;

public interface StudentService {
    void readExcel(List<Student> studentList);
}
