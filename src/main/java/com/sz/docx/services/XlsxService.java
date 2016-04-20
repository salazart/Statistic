package com.sz.docx.services;

import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XlsxService {
	private static final String SHEET_NAME = "report";
	
	public void createContentXlsx(XSSFWorkbook workBook, List<List<String>> report){
		XSSFSheet sheet = workBook.createSheet(SHEET_NAME);
		
		for (int i = 0; i < report.size(); i++) {
			Row row = sheet.createRow(i);
			
			List<String> reportRow = report.get(i);
			for (int j = 0; j < reportRow.size(); j++) {
				Cell cell = row.createCell(j);
				cell.setCellValue(reportRow.get(j));
			}
		}
	}
		
	public  XSSFWorkbook getDocument(List<List<String>> report){
		XSSFWorkbook workBook = new XSSFWorkbook();
		createContentXlsx(workBook, report);
		return workBook;
	}
}
