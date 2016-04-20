package com.sz;

import java.sql.Date;
import java.util.Calendar;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.sz.db.interfaces.GenericDao;
import com.sz.db.models.Crime;
import com.sz.db.models.Departament;
import com.sz.db.models.Employe;
import com.sz.db.models.Resolution;
import com.sz.db.services.CrimeService;
import com.sz.db.services.DepartamentService;
import com.sz.db.services.EmployeService;
import com.sz.db.services.PersistException;
import com.sz.db.services.ResolutionService;

public class ResolutionTest extends Assert{
	private static final Logger log = LogManager.getRootLogger();
	
	private Resolution resolution;
	private GenericDao<Resolution> dao;
	private GenericDao<Crime> crimeService;
	private GenericDao<Departament> departamentService;
	private GenericDao<Employe> employeService;
	
	@Before
	public void init() {
		resolution = new Resolution();
		dao = new ResolutionService();
		crimeService = new CrimeService();
		departamentService = new DepartamentService();
		employeService = new EmployeService();
	}
	
	@Test
	public void test() {
		Crime crime = getCrime();
		Departament departament = getDepartament();
		Employe employe = getEmploye();
		
		createResolution(crime, departament, employe);
		
		try {
			resolution = dao.save(resolution);
		} catch (Exception e) {
			log.error(e);
		}
		assertTrue(resolution.getId() != 0);
		
		Resolution resolution2 = null;
		try {
			resolution2 = dao.get(resolution.getId());
		} catch (PersistException e) {
			log.error(e);
		}
		assertNotNull(resolution2);
		assertTrue(resolution2.getId() != 0);
		
		dao.delete(resolution2);
		crimeService.delete(crime);
		departamentService.delete(departament);
		employeService.delete(employe);
	}
	
	private void createResolution(Crime crime, Departament departament, Employe employe) {
		resolution.setIdCrime(crime.getId());
		resolution.setIdDepartament(departament.getId());
		resolution.setIdEmploye(employe.getId());
		
		resolution.setDateAdd(new Date(Calendar.getInstance().getTimeInMillis()));
		
		resolution.setNumberResolution("65198/564");
		resolution.setDateResolution(new Date(Calendar.getInstance().getTimeInMillis()));
		resolution.setDateSubmission(new Date(Calendar.getInstance().getTimeInMillis()));
		resolution.setDateValid(new Date(Calendar.getInstance().getTimeInMillis()));
		
		resolution.setNote("note");
	}
	
	private Employe getEmploye() {
		Employe employe = new Employe();
		employe.setName("Ivanov");
		
		try {
			employe = employeService.save(employe);
		} catch (Exception e) {
			log.error(e);
		}
		return employe;
	}

	private Departament getDepartament() {
		Departament departament = new Departament();
		departament.setName("example departament");
		
		try {
			departament = departamentService.save(departament);
		} catch (Exception e) {
			log.error(e);
		}
		return departament; 
	}

	private Crime getCrime(){
		Crime crime = new Crime();
		crime.setCrimeDate(new Date(Calendar.getInstance().getTimeInMillis()));
		crime.setNumber("1201524659468414");
		crime.setRegistrationDate(new Date(Calendar.getInstance().getTimeInMillis()));
		crime.setArticle("Злочин");
		crime.setType("ЄРДР");
		crime.setStory("sdfsdafsav asdgagbds фывпуп фывавыав");
		
		try {
			crime = crimeService.save(crime);
		} catch (Exception e) {
			log.error(e);
		}
		return crime;
	}
}
