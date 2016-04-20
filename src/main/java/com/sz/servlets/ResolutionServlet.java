package com.sz.servlets;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

import com.sz.db.interfaces.GenericDao;
import com.sz.db.models.Resolution;
import com.sz.db.models.Target;
import com.sz.db.services.ResolutionService;
import com.sz.db.services.TargetService;
import com.sz.services.TargetHTMLService;

@WebServlet("/award")
public class ResolutionServlet extends GenericServlet{
	private static final String TARGET_ROW = "/targetRow.html";
	private Resolution resolution = new Resolution();
	private List<Target> targets = new ArrayList<Target>();
	
	private GenericDao<Resolution> resolutionService = new ResolutionService();
	private GenericDao<Target> targetService = new TargetService();
	
	private TargetHTMLService targetHTMLService = new TargetHTMLService();
	
	private String saveButtonName = "";
	
	private void getResolutionOfPage(HttpServletRequest request) throws ParseException{
		resolution.setIdCrime(Integer.valueOf(request.getParameter("id_crime")));
		resolution.setDateAdd(new java.sql.Date(new Date().getTime()));
		resolution.setIdDepartament(Integer.valueOf(request.getParameter("id_department")));
		resolution.setNumberResolution(request.getParameter("number_award"));
		resolution.setDateResolution(parseDate(request.getParameter("date_award")));
		resolution.setIdEmploye(Integer.valueOf(request.getParameter("id_employe")));
		resolution.setDateValid(parseDate(request.getParameter("date_validity_award")));
		resolution.setDateSubmission(parseDate(request.getParameter("date_submission")));
		resolution.setNote(request.getParameter("note"));
	}
	
	private void getTargetsOfPage(int idResolution, HttpServletRequest request){
		for(int j = 0; j < targets.size(); j++){
			targetService.delete(targets.get(j));
		}
		targets.clear();
		
		for(int i = 0; i < 50; i++){
			Target target = new Target();
			target.setIdResolution(idResolution);
			target.setTarget(request.getParameter("objectName_" + i));
			
			if(request.getParameter("operatorVodafone_" + i) != null)
				target.setOperatorVodafone(true);
			
			if(request.getParameter("operatorKyivstar_" + i) != null)
				target.setOperatorKyivstar(true);
			
			if(request.getParameter("operatorLifecell_" + i) != null)
				target.setOperatorLifecell(true);
			
			if(request.getParameter("sendObject_" + i) != null)
				target.setSendTarget(true);
			
			if(!target.isEmpty()){
				targets.add(target);
			}
		}
	}
	
	@Override
	protected void get(HttpServletRequest request, HttpServletResponse response) {
		resolution = new Resolution();
		targets.clear();
		loadFormData(request);
	}
	
	private void loadObject(HttpServletRequest request){
		request.setAttribute("id_department", resolution.getIdDepartament());
		request.setAttribute("id_crime", resolution.getIdCrime());
		request.setAttribute("id_employe", resolution.getIdEmploye());
		request.setAttribute("number_award", resolution.getNumberResolution());
		request.setAttribute("date_award", dateToForm(resolution.getDateResolution()));
		request.setAttribute("date_validity_award", dateToForm(resolution.getDateValid()));
		request.setAttribute("date_submission", dateToForm(resolution.getDateSubmission()));
		request.setAttribute("note", resolution.getNote());
	}
	
	private void loadFormData(HttpServletRequest request) {
		if(resolution.getId() != 0){
			loadObject(request);
			saveButtonName = "Оновити";
		} else {
			saveButtonName = "Зберегти";
		}
		
		request.setAttribute("departament", getDepartamentHTML(resolution.getIdDepartament()));
		
		request.setAttribute("crime", getCrimeHTML(resolution.getIdCrime()));
		
		request.setAttribute("employe", getEmployeHTML(resolution.getIdEmploye()));
		
		request.setAttribute("objectRow", targetHTMLService.getTargetHTML(request, targets, createTargetRow()));
		
		request.setAttribute("saveButton", saveButtonName);
	}
	
	private String createTargetRow(){
		InputStream is = getServletContext().getResourceAsStream(TARGET_ROW);
		try {
			return IOUtils.toString(is, "UTF-8");
		} catch (IOException e) {
			log.error(e);
			return "";
		} 
	}
	
	@Override
	protected void post(HttpServletRequest request, HttpServletResponse response) {
		if(request.getParameter("save") != null){
			try {
				getResolutionOfPage(request);
				resolution = resolutionService.save(resolution);
				
				getTargetsOfPage(resolution.getId(), request);
				for(int i = 0; i < targets.size(); i++){
					Target targer = targetService.save(targets.get(i));
					targets.set(i, targer);
				}
				request.setAttribute("textResult", TEXT_RESULT);
			} catch (Exception e) {
				log.error(e);
				request.setAttribute("textResult", ERROR_RESULT);
			}
		}
		loadFormData(request);
	}

	@Override
	protected String getBlockForm() {
		return "award.jsp";
	}

}
