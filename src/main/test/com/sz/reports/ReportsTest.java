package com.sz.reports;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import com.sz.db.services.ReportService;

public class ReportsTest {
	private static final String startDate = "01.01.2016";
	private static final String maxDate = "01.04.2016";
	
	public static void main(String[] args) {

		ReportService reportService = new ReportService();
		
		List<String> params = new ArrayList<>();
		params.add(startDate);
		params.add(maxDate);
		
		List<List<String>> report = new ArrayList<>();
		
		report = reportService.getReport(getQuery("WebContent/sql/1/sql1.sql"), params, report);
		report = reportService.getReport(getQuery("WebContent/sql/1/sql2.sql"), params, report);
		
		for (List<String> list : report) {
			System.out.println(list);
		}
	}
	
	private static String getQuery(String fileName){
		try (InputStream is = new FileInputStream(fileName);){
			return IOUtils.toString(is, "UTF-8");
		} catch (IOException e) {
			System.out.println(e.getMessage());
			return "";
		} 
	}
}
