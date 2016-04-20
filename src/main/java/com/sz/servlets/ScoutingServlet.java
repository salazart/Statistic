package com.sz.servlets;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import com.sz.db.interfaces.GenericDao;
import com.sz.db.models.Crime;
import com.sz.db.models.Departament;
import com.sz.db.models.Place;
import com.sz.db.models.Scouting;
import com.sz.db.services.CrimeService;
import com.sz.db.services.DepartamentService;
import com.sz.db.services.PersistException;
import com.sz.db.services.PlaceService;
import com.sz.db.services.ScoutingService;
import com.sz.docx.models.ReferenceData;
import com.sz.docx.services.DocxService;
import com.sz.services.PlaceHTMLService;

@WebServlet("/scouting")
public class ScoutingServlet  extends GenericServlet {
	private static final String PLACE_ROW = "/placeRow.html";
	
	private Scouting scouting = new Scouting();
	private List<Place> places = new ArrayList<Place>();
	
	private GenericDao<Scouting> scoutingService = new ScoutingService();
	private GenericDao<Place> placeService = new PlaceService();
	
	private PlaceHTMLService placeHTMLService = new PlaceHTMLService();
	
	private String saveButtonName = "";
	
	private void getScoutingOfPage(HttpServletRequest request) throws ParseException{
		scouting.setIdDepartament(Integer.valueOf(request.getParameter("id_department")));
		scouting.setIdCrime(Integer.valueOf(request.getParameter("id_crime")));
		scouting.setIdEmploye(Integer.valueOf(request.getParameter("id_employe")));
		scouting.setDateAdd(new java.sql.Date(new Date().getTime()));
		scouting.setNumberRaport(request.getParameter("number_raport"));
		scouting.setDateRaport(parseDate(request.getParameter("date_raport")));
		scouting.setNumberReference(request.getParameter("number_dovidka"));
		scouting.setDateReference(parseDate(request.getParameter("date_dovidka")));
		scouting.setNote(request.getParameter("note"));
	}
	
	private void getPlacesOfPage(int idScouting, HttpServletRequest request){
		for(int j = 0; j < places.size(); j++){
			placeService.delete(places.get(j));
		}
		places.clear();
		
		for(int i = 0; i < 50; i++){
			Place place = new Place();
			place.setIdScoution(idScouting);
			place.setPlace(request.getParameter("placeName_" + i));
			place.setLac(request.getParameter("placeLac_" + i));
			place.setCid(request.getParameter("placeCid_" + i));
			
			if(request.getParameter("operatorVodafone_" + i) != null)
				place.setOperatorVodafone(true);
			
			if(request.getParameter("operatorKyivstar_" + i) != null)
				place.setOperatorKyivstar(true);
			
			if(request.getParameter("operatorLifecell_" + i) != null)
				place.setOperatorLifecell(true);
			
			if(!place.isEmpty()){
				places.add(place);
			}
		}
	}
	
	@Override
	protected void get(HttpServletRequest request, HttpServletResponse response) {
		scouting = new Scouting();
		places.clear();
		loadFormData(request);
	}

	protected void post(HttpServletRequest request, HttpServletResponse response){
		try {
			if(request.getParameter("save") != null){
				save(request);
			} else if(request.getParameter("printReference") != null){
				save(request);
				printReference(response);
			}
			request.setAttribute("textResult", TEXT_RESULT);
		} catch (Exception e) {
			log.error(e);
			request.setAttribute("textResult", ERROR_RESULT);
		}
		
		loadFormData(request);
		
		if(request.getParameter("printReference") == null){
			writeResult(request, response);
		}
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response){
		loadEncoding(request, response);
		
		post(request,response);
	}
	
	private void printReference(HttpServletResponse response) throws PersistException{
		DepartamentService departamentService = new DepartamentService();
		Departament departament = departamentService.get(scouting.getIdDepartament());
		
		CrimeService crimeService = new CrimeService();
		Crime crime = crimeService.get(scouting.getIdCrime());
		
		ReferenceData referenceData = new ReferenceData(departament, crime, scouting, places);
		
		String fileName = getFileName(referenceData);
		
		DocxService referenceDocx = new DocxService();
		XWPFDocument document = referenceDocx.getDocument(referenceData);
		
		response.setHeader("Content-disposition","attachment; filename=" + fileName);
		
		try (ServletOutputStream out = response.getOutputStream()) {
			log.debug("Try save file into user folder. File:" + fileName);	
				document.write(out);
				out.flush();
			} catch (IOException e) {
				log.error(e);
			}
	}
	
	private String getFileName(ReferenceData referenceData){
		return referenceData.getNumberCrime() 
				+ "_" 
				+ referenceData.getNumberRaport() 
				+ ".docx";
	}
	
	private void save(HttpServletRequest request) throws ParseException, Exception {
		getScoutingOfPage(request);
		scouting = scoutingService.save(scouting);
		
		getPlacesOfPage(scouting.getId(), request);
		for(int i = 0; i < places.size(); i++){
			Place place = placeService.save(places.get(i));
			places.set(i, place);
		}
	}
	
	private void loadObject(HttpServletRequest request){
		request.setAttribute("id_department", scouting.getIdDepartament());
		request.setAttribute("id_crime", scouting.getIdCrime());
		request.setAttribute("id_employe", scouting.getIdEmploye());
		request.setAttribute("number_raport", scouting.getNumberRaport());
		request.setAttribute("date_raport", dateToForm(scouting.getDateRaport()));
		request.setAttribute("number_dovidka", scouting.getNumberReference());
		request.setAttribute("date_dovidka", dateToForm(scouting.getDateReference()));
		request.setAttribute("note", scouting.getNote());
	}
	
	
	private void loadFormData(HttpServletRequest request) {
		if(scouting.getId() != 0){
			loadObject(request);
			saveButtonName = "Оновити";
		} else {
			saveButtonName = "Зберегти";
		}
		
		request.setAttribute("departament", getDepartamentHTML(scouting.getIdDepartament()));
		
		request.setAttribute("crime", getCrimeHTML(scouting.getIdCrime()));
		
		request.setAttribute("employe", getEmployeHTML(scouting.getIdEmploye()));
		
		request.setAttribute("placeRow", placeHTMLService.getPlaceHTML(request, places, createPlaceRow()));
		
		request.setAttribute("saveButton", saveButtonName);
	}
	
	private String createPlaceRow(){
		InputStream is = getServletContext().getResourceAsStream(PLACE_ROW);
		try {
			return IOUtils.toString(is, "UTF-8");
		} catch (IOException e) {
			log.error(e);
			return "";
		} 
	}

	@Override
	protected String getBlockForm() {
		return "scouting.jsp";
	}
}
