package com.zenex.helpdesk.service;

import com.zenex.helpdesk.model.Ticket;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;

@Service
public class ReportExcelService {

    public byte[] generateExcel(List<Ticket> tickets) throws Exception {

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Tickets");

        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("ID");
        header.createCell(1).setCellValue("Employee Code");
        header.createCell(2).setCellValue("Name");
        header.createCell(3).setCellValue("Email");
        header.createCell(4).setCellValue("Issue Type");
        header.createCell(5).setCellValue("Status");
        header.createCell(6).setCellValue("Created Time");

        int rowNum = 1;
        for (Ticket t : tickets) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(t.getId());
            row.createCell(1).setCellValue(t.getEmployeeCode());
            row.createCell(2).setCellValue(t.getName());
            row.createCell(3).setCellValue(t.getEmail());
            row.createCell(4).setCellValue(t.getIssueType());
            row.createCell(5).setCellValue(t.getStatus());
            row.createCell(6).setCellValue(
                    t.getCreatedTime() != null
                            ? t.getCreatedTime().toString()
                            : ""
            );
        }

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        workbook.write(out);
        workbook.close();

        return out.toByteArray();
    }
}
