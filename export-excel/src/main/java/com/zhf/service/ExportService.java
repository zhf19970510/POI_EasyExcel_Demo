package com.zhf.service;

import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ExportService {
    ResponseEntity<byte[]> exportExcel(HttpServletRequest request, HttpServletResponse response);
}
