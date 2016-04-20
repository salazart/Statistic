package com.sz.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.sz.docx.services.XlsxService;
import com.sz.services.ReportHTMLService;

@WebServlet("/report")
public class ReportServlet extends GenericServlet{
	private ReportHTMLService reportHtmlService = new ReportHTMLService();
	private String reportType;
	@Override
	protected String getBlockForm() {
		return "report.jsp";
	}

	@Override
	protected void get(HttpServletRequest request, HttpServletResponse response) {
		reportType = request.getParameter("reportType");
		if(reportType != null && !reportType.isEmpty()){
			request.setAttribute("inputData", reportHtmlService.getInputDataHTML(getServletContext(), reportType));
		} else {
			request.setAttribute("inputData", reportHtmlService.getListOfReports(getServletContext()));
		}
	}

	@Override
	protected void post(HttpServletRequest request, HttpServletResponse response) {
		if(request.getParameter("create") != null){
			List<String> params = getParams(request);
			loadFormData(request, params);
			writeResult(request, response);
		} else if (request.getParameter("create_xls") != null){
			List<String> params = getParams(request);
			List<List<String>> report = reportHtmlService.getReportXlsx(getServletContext(), params, reportType);
			createXls(response, report);
		} else {
			writeResult(request, response);
		}
	}
	
	private void createXls(HttpServletResponse response, List<List<String>> report){
		response.setHeader("Content-disposition","attachment; filename=report.xlsx");
		
		XlsxService xlsxService = new XlsxService();
		XSSFWorkbook workBook = xlsxService.getDocument(report);
		
		try (ServletOutputStream out = response.getOutputStream()) {
			log.debug("Try save file into user folder. File: report.xlsx");	
			workBook.write(out);
			out.flush();
		} catch (IOException e) {
			log.error(e);
		}
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response){
		loadEncoding(request, response);
		
		post(request,response);
	}

	private List<String> getParams(HttpServletRequest request) {
		List<String> params = new ArrayList<>();
		for(int i = 0; i < 5; i++){
			String param = request.getParameter("param" + i);
			if(param != null){
				param = parseDate(param, "dd/MM/yyyy", "dd.MM.yyyy");
				params.add(param);
			}
		}
		return params;
	}
	
	private void loadFormData(HttpServletRequest request, List<String> params) {
		request.setAttribute("reportRow", reportHtmlService.getReportHTML(getServletContext(), params));
	}
	
}
