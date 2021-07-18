package com.zhf;

import com.alibaba.excel.EasyExcel;
import com.zhf.po.easyExcel.DemoData;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EasyExcelTest {

    String PATH = "D:\\zhfGitRepository\\export-excel\\";

    private List<DemoData> data(){
        List<DemoData> list = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            DemoData data = new DemoData();
            data.setString("字符串：" + i);
            data.setDate(new Date());
            data.setDoubleData(0.56);
            list.add(data);
        }
        return list;
    }

    // 根据list写入 Excel
    @Test
    public void simpleWrite(){
        String fileName = PATH + "EasyTest.xlsx";

        EasyExcel.write(fileName, DemoData.class).sheet("模版").doWrite(data());
    }
}
