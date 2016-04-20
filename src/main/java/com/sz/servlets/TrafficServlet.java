package com.sz.servlets;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

import com.sz.db.interfaces.GenericDao;
import com.sz.db.models.Target;
import com.sz.db.models.Traffic;
import com.sz.db.services.TargetService;
import com.sz.db.services.TrafficService;

@WebServlet("/traffic")
public class TrafficServlet extends GenericServlet{
	private static final String TARGET_ROW = "/trafficRow.html";
	private Traffic traffic = new Traffic();
	private List<Target> targets = new ArrayList<Target>();
	
	private GenericDao<Traffic> trafficService = new TrafficService();
	private GenericDao<Target> targetService = new TargetService();
	
	private String saveButtonName = "";
	
	@Override
	protected String getBlockForm() {
		return "traffic.jsp";
	}
	
	private void getTraffic(HttpServletRequest request){
		traffic.setIdCrime(Integer.valueOf(request.getParameter("id_crime")));
		traffic.setIdDepartament(Integer.valueOf(request.getParameter("id_department")));
		traffic.setDateAdd(new java.sql.Date(new Date().getTime()));
	}

	@Override
	protected void get(HttpServletRequest request, HttpServletResponse response) {
		traffic = new Traffic();
		targets.clear();
		loadFormData(request);
	}
	
	private void loadObject(HttpServletRequest request){
		request.setAttribute("id_department", traffic.getIdDepartament());
		request.setAttribute("id_crime", traffic.getIdCrime());
	}
	
	private void loadFormData(HttpServletRequest request) {
		if(traffic.getId() != 0){
			loadObject(request);
			saveButtonName = "Оновити";
		} else {
			saveButtonName = "Зберегти";
		}
		
		request.setAttribute("departament", getDepartamentHTML(traffic.getIdDepartament()));
		
		request.setAttribute("crime", getCrimeHTML(traffic.getIdCrime()));
		
		request.setAttribute("trafficRow", getTargetHTML());
		
		request.setAttribute("saveButton", saveButtonName);
	}
	
	private String getTargetHTML(){
		String targetHTML = "";
		
		for(int i = 0; i < targets.size(); i++){
			
			String newRowHtml = createTargetRow();
			
			newRowHtml = createTargetName(newRowHtml, i);
			
			newRowHtml = createTargetValue(targets.get(i), newRowHtml);
			
			targetHTML += newRowHtml;
		}
		
		if(targets.isEmpty()){
			targetHTML = createTargetRow();
			targetHTML = createTargetName(targetHTML, 0);
			
			Target target = new Target();
			target.setTarget("Моніторинг");
			targetHTML = createTargetValue(target, targetHTML);
		}
		
		return targetHTML;
	}
	
	private String createTargetValue(Target target, String newRowHtml) {
		newRowHtml = newRowHtml.replace("objectValue", target.getTarget());
		if(target.isOperatorVodafone())
			newRowHtml = newRowHtml.replace("vodafoneChecked", "checked");
		
		if(target.isOperatorKyivstar())
			newRowHtml = newRowHtml.replace("kyivstarChecked", "checked");
		
		if(target.isOperatorLifecell())
			newRowHtml = newRowHtml.replace("lifecellChecked", "checked");
		
		return newRowHtml;
	}
	
	private String createTargetName(String targetHTML, int index){
		targetHTML = targetHTML.replace("objectName", "objectName_" + index);
		targetHTML = targetHTML.replace("operatorVodafone", "operatorVodafone_" + index);
		targetHTML = targetHTML.replace("operatorKyivstar", "operatorKyivstar_" + index);
		targetHTML = targetHTML.replace("operatorLifecell", "operatorLifecell_" + index);
		return targetHTML;
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
		request.setAttribute("saveButton", "Оновити");
		
		if(request.getParameter("save") != null){
			try {
				getTraffic(request);
				traffic = trafficService.save(traffic);
				
				getTargetsOfPage(traffic.getId(), request);
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
	
	private void getTargetsOfPage(int idTraffic, HttpServletRequest request){
		for(int j = 0; j < targets.size(); j++){
			targetService.delete(targets.get(j));
		}
		targets.clear();
		
		for(int i = 0; i < 50; i++){
			Target target = new Target();
			target.setIdTraffic(idTraffic);
			target.setTarget(request.getParameter("objectName_" + i));
			
			if(request.getParameter("operatorVodafone_" + i) != null)
				target.setOperatorVodafone(true);
			
			if(request.getParameter("operatorKyivstar_" + i) != null)
				target.setOperatorKyivstar(true);
			
			if(request.getParameter("operatorLifecell_" + i) != null)
				target.setOperatorLifecell(true);
			
			if(!target.isEmpty()){
				targets.add(target);
			}
		}
	}

}
