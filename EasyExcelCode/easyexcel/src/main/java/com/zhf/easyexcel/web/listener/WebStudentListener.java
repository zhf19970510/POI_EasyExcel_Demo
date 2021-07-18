package com.zhf.easyexcel.web.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.sun.org.apache.bcel.internal.generic.NEW;
import com.zhf.easyexcel.domain.Student;
import com.zhf.easyexcel.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.util.ArrayList;

@Component
@Scope("prototype")
public class WebStudentListener extends AnalysisEventListener<Student> {

    @Autowired
    StudentService studentService;

    ArrayList<Student> students = new ArrayList<Student>();

    public void invoke(Student student, AnalysisContext context) {
        students.add(student);
        if(students.size() % 5 == 0){
            studentService.readExcel(students);
            students.clear();
        }
    }

    public void doAfterAllAnalysed(AnalysisContext context) {

    }
}
