package com.zhf.controller;

import com.zhf.service.ExportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/exportExcel/")
public class ExportController {

    @Autowired
    private ExportService exportService;

    // 导出Excel
    @RequestMapping("exportExcel")
    public ResponseEntity<byte[]> exportExcel(HttpServletRequest request, HttpServletResponse response){
        return exportService.exportExcel(request, response);
    }
}
