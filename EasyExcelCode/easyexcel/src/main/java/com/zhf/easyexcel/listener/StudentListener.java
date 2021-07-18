package com.zhf.easyexcel.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.zhf.easyexcel.domain.Student;

/**
 * 读取文档的监听器类
 */
public class StudentListener extends AnalysisEventListener<Student> {

    /**
     * 每读取一行内容，都会调用一次invoke方法，在invoke方法可以操作使用读取的数据
     * @param student
     * @param analysisContext
     */
    public void invoke(Student student, AnalysisContext analysisContext) {
        System.out.println("student:" + student);
    }

    /**
     * 读取完整个文档之后调用的方法
     * @param analysisContext
     */
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
