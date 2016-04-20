package com.sz.servlets;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StyleServlet extends HttpServlet {
	protected static final String TEXT_RESULT = "Інформація успішно збережена";
	protected static final String ERROR_RESULT = "Помилка збереження данних";
	
	protected static final Logger log = LogManager.getRootLogger();
	
	protected void loadEncoding(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			log.error(e);
		}
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType ("text/html; charset=UTF-8");
	}
	
	protected java.sql.Date parseDate(String textDate){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		java.util.Date date = null;
		try {
			date = simpleDateFormat.parse(textDate);
		} catch (ParseException e) {
			log.error(e);
			return null;
		}
		return new java.sql.Date(date.getTime());
	}
	
	protected String parseDate(String textDate, String startPattern, String finishPattern){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(startPattern);
		java.util.Date date = null;
		try {
			date = simpleDateFormat.parse(textDate);
			simpleDateFormat.applyPattern(finishPattern);
			return simpleDateFormat.format(date);
		} catch (ParseException e) {
			log.error(e);
			return textDate;
		}
	}
	
	protected String dateToForm(java.sql.Date sqlDate) {
		if(sqlDate == null){
			return "";
		} else {
			java.util.Date newDate = new java.util.Date(sqlDate.getTime());
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
			return simpleDateFormat.format(newDate);
		}
	}
}
