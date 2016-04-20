package com.sz.services;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.sz.db.services.ReportService;

public class ReportHTMLService {
	private static final String FOLDER = "/sql/";
	private static final String LIST_OF_REPORTS = "namesOfReports.html";
	
	private static final String INPUT_DATA = "/inputData.html";
	
	private static final String HEADER_REPORT = "/header.html";
	
	protected static final Logger log = LogManager.getRootLogger();
	
	private String reportType;
	
	public ReportHTMLService() {
	}
	
	public String getReportHTML(ServletContext servletContext, List<String> params) {
		
		List<List<String>> report = new ArrayList<>();
		report = getReport(servletContext, report, params, reportType);
		
		List<String> headers = getFileToList(servletContext, FOLDER + reportType + HEADER_REPORT);
		String reportHTML = createHeader(headers);
		for (int i = 0; i < report.size(); i++) {
			List<String> row = report.get(i);
			reportHTML += createValue(row);
		}
		return reportHTML;
	}
	
	private String createValue(List<String> row){
		String clearHtml = "<tr>";
		for (String cell : row) {
			clearHtml += "<td>" + cell + "</td>";
		}
		clearHtml += "</tr>";
		return clearHtml;
	}
	
	private String createHeader(List<String> headers){
		String clearHtml = "";
		for (String header : headers) {
			clearHtml += "<th>" + header + "</th>";
		}
		return clearHtml;
	}
	
	public List<List<String>> getReport(ServletContext servletContext, 
			List<List<String>> report, 
			List<String> params, 
			String reportType){
		
		ReportService reportService = new ReportService();

		for(int i = 1; i < 5; i++){
			String fileName = FOLDER + reportType + "/sql" + i + ".sql";
			report = reportService.getReport(getFileContent(servletContext, fileName), params, report);
		}
		return report;
	}
	
	public List<List<String>> getReportXlsx(ServletContext servletContext, List<String> params, String reportType){
		List<List<String>> report = new ArrayList<>();
		report.add(getFileToList(servletContext, FOLDER + reportType + HEADER_REPORT));
		return getReport(servletContext, report, params, reportType);
	}
	
	public List<String> getFileToList(ServletContext servletContext, String fileName){
		log.debug("Try read file: " + fileName);
		try (InputStream is = servletContext.getResourceAsStream(fileName)){
			return is != null ? IOUtils.readLines(is, "UTF-8") : new ArrayList<String>();
		} catch (IOException e) {
			log.error(e);
			return new ArrayList<String>();
		} 
	}
	
	private String getFileContent(ServletContext servletContext, String fileName){
		log.debug("Try read file: " + fileName);
		try (InputStream is = servletContext.getResourceAsStream(fileName)){
			return is != null ? IOUtils.toString(is, "UTF-8") : "";
		} catch (IOException e) {
			log.error(e);
			return "";
		} 
	}

	public String getListOfReports(ServletContext servletContext) {
		return getFileContent(servletContext, FOLDER + LIST_OF_REPORTS);
	}

	public String getInputDataHTML(ServletContext servletContext, String reportType) {
		this.reportType = reportType;
		return getFileContent(servletContext, FOLDER + reportType + INPUT_DATA);
	}
}