package com.sz.xlsx;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.sz.docx.services.XlsxService;

public class XlsxCreatingTest {

	public static void main(String[] args) {
		List<String> list = new ArrayList<>();
		list.add("hello");
		list.add("help");
		
		List<List<String>> report = new ArrayList<>();
		report.add(list);
		report.add(list);
		report.add(list);
		
		XlsxService xlsxService = new XlsxService();
		XSSFWorkbook workBook = xlsxService.getDocument(report);
		
		try (FileOutputStream os = new FileOutputStream(new File("report.xlsx"))){
			workBook.write(os);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
