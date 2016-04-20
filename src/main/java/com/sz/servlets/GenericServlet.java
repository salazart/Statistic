package com.sz.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sz.db.models.Crime;
import com.sz.db.models.Departament;
import com.sz.db.models.Employe;
import com.sz.db.services.CrimeService;
import com.sz.db.services.DepartamentService;
import com.sz.db.services.EmployeService;

public abstract class GenericServlet extends StyleServlet{
	protected static final String index = "general.jsp";
	
	protected abstract String getBlockForm();
	
	protected abstract void get(HttpServletRequest request, HttpServletResponse response);
	public void doGet(HttpServletRequest request, HttpServletResponse response){
		
		loadEncoding(request, response);
		
		get(request, response);

		writeResult(request, response);
	}

	protected abstract void post(HttpServletRequest request, HttpServletResponse response);
	public void doPost(HttpServletRequest request, HttpServletResponse response){
		
		loadEncoding(request, response);
		
		post(request,response);
		
		writeResult(request, response);
	}
	
	protected void writeResult(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("block", getBlockForm());
		try {
			request.getRequestDispatcher(index).forward(request, response);
		} catch (ServletException | IOException e) {
			log.error(e);
		}
	}
	
	protected String getEmployeHTML(int idEmploye) {
		String employeHTML = "";
		try {
			EmployeService employeService = new EmployeService();
			Map<Integer, String> employes = createEmployeMap(employeService.getAll());
			for (Map.Entry<Integer, String> entry : employes.entrySet()) {
				if(idEmploye == entry.getKey()){
					employeHTML += "<option value=\"" + entry.getKey() + "\" selected>" + entry.getValue() + "</option>";
				} else {
					employeHTML += "<option value=\"" + entry.getKey() + "\">" + entry.getValue() + "</option>";
				}
			}
		} catch (Exception e) {
			log.error(e);
		}
		return employeHTML;
	}
	
	protected String getCrimeHTML(int idCrime) {
		String crimeHTML = createEmplyField(idCrime);
		try {
			CrimeService crimeService = new CrimeService();
			Map<Integer, String> crimes = createCrimeMap(crimeService.getAll());
			List<Integer> ids = new ArrayList<Integer>(crimes.keySet());
			Collections.sort(ids);
			Collections.reverse(ids);
			for (Integer key : ids) {
				String value = crimes.get(key);
				if(value != null){
					if(idCrime == key){
						crimeHTML += "<option value=\"" + key + "\" selected>" + value + "</option>";
					} else {
						crimeHTML += "<option value=\"" + key + "\">" + value + "</option>";
					}
				}
			}
		} catch (Exception e) {
			log.error(e);
		}
		return crimeHTML;
	}
	
	private String createEmplyField(int id) {
		return id == 0 ? "<option selected></option>" : "";
	}

	protected String getDepartamentHTML(int idDepartament) {
		String departamentHTML = createEmplyField(idDepartament);
		try {
			DepartamentService departamentService = new DepartamentService();
			Map<Integer, String> departaments = createDepartamentMap(departamentService.getAll());
			for (Map.Entry<Integer, String> entry : departaments.entrySet()) {
				if(idDepartament == entry.getKey()){
					departamentHTML += "<option value=\"" + entry.getKey() + "\" selected>" + entry.getValue() + "</option>";
				} else {
					departamentHTML += "<option value=\"" + entry.getKey() + "\">" + entry.getValue() + "</option>";
				}
			}
		} catch (Exception e) {
			log.error(e);
		}
		return departamentHTML;
	}

	private Map<Integer, String> createEmployeMap(List<Employe> objects) {
		Map<Integer, String> objectMap = new HashMap<Integer, String>();
		for (Employe object : objects) {
			objectMap.put(object.getId(), object.getName());
		}
		return objectMap;
	}
	
	private Map<Integer, String> createCrimeMap(List<Crime> objects) {
		Map<Integer, String> objectMap = new HashMap<Integer, String>();
		for (Crime object : objects) {
			objectMap.put(object.getId(), object.getNumber());
		}
		return objectMap;
	}
	
	private Map<Integer, String> createDepartamentMap(List<Departament> objects) {
		Map<Integer, String> objectMap = new HashMap<Integer, String>();
		for (Departament object : objects) {
			objectMap.put(object.getId(), object.getName());
		}
		return objectMap;
	}
}
