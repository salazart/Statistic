package com.sz.servlets;

import java.io.IOException;
import java.text.ParseException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sz.db.interfaces.GenericDao;
import com.sz.db.models.Crime;
import com.sz.db.services.CrimeService;

/**
 * Servlet implementation class CrimeServlet
 */
@WebServlet("/crime")
public class CrimeServlet extends StyleServlet {
	private static final long serialVersionUID = 1L;

	private Crime getCrime(HttpServletRequest request) throws ParseException{
		Crime crime = new Crime();
		crime.setType(request.getParameter("kind_crime"));
		crime.setNumber(request.getParameter("number"));
		crime.setStory(request.getParameter("story"));
		crime.setRegistrationDate(parseDate(request.getParameter("date_registration")));
		crime.setArticle(request.getParameter("article"));
		crime.setCrimeDate(parseDate(request.getParameter("date_crime")));
		return crime;
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response){
		
		loadEncoding(request, response);
		
		writeResult(request, response);
	}
	
	private void writeResult(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("block", getBlockForm());
		try {
			request.getRequestDispatcher(getBlockForm()).forward(request, response);
		} catch (ServletException | IOException e) {
			log.error(e);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		loadEncoding(request, response);
		
		if(request.getParameter("save") != null){
			try {
				GenericDao<Crime> crimeService = new CrimeService();
				crimeService.save(getCrime(request));
				
				request.setAttribute("textResult", TEXT_RESULT);
			} catch (Exception e) {
				log.error(e);
				request.setAttribute("textResult", ERROR_RESULT);
			}
		}
		writeResult(request, response);
	}
       
	protected String getBlockForm() {
		return "crime.jsp";
	}
}
