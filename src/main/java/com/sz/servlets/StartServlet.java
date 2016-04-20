package com.sz.servlets;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Extend HttpServlet class
@WebServlet("/start")
public class StartServlet extends GenericServlet {

	@Override
	protected void get(HttpServletRequest request, HttpServletResponse response) {
	}

	@Override
	protected void post(HttpServletRequest request, HttpServletResponse response) {
	}

	@Override
	protected String getBlockForm() {
		return "empty.jsp";
	}
}
