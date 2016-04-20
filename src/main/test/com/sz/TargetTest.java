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
import com.sz.db.models.Target;
import com.sz.db.services.CrimeService;
import com.sz.db.services.DepartamentService;
import com.sz.db.services.EmployeService;
import com.sz.db.services.PersistException;
import com.sz.db.services.ResolutionService;
import com.sz.db.services.TargetService;

public class TargetTest extends Assert{
private static final Logger log = LogManager.getRootLogger();
	
	private Target target;
	private GenericDao<Target> dao;
	
	private Crime crime;
	private Departament departament;
	private Employe employe;
	private Resolution resolution;
	private GenericDao<Resolution> resolutionService;
	private GenericDao<Crime> crimeService;
	private GenericDao<Departament> departamentService;
	private GenericDao<Employe> employeService;
	
	@Before
	public void init() {
		target = new Target();
		dao = new TargetService();
		
		resolution = new Resolution();
		resolutionService = new ResolutionService();
		crimeService = new CrimeService();
		departamentService = new DepartamentService();
		employeService = new EmployeService();
	}
	
	@Test
	public void test() {
		int resolutionId = getResolutionId();
		target.setIdResolution(resolutionId);
		target.setOperatorKyivstar(true);
		target.setTarget("684351684");
		target.setSendTarget(true);
		
		try {
			target = dao.save(target);
		} catch (Exception e) {
			log.error(e);
		}
		assertTrue(target.getId() != 0);
		
		Target target2 = null;
		try {
			target2 = dao.get(target.getId());
		} catch (PersistException e) {
			log.error(e);
		}
		assertNotNull(target2);
		assertTrue(target2.getId() != 0);
		
		dao.delete(target);
		resolutionService.delete(resolution);
		crimeService.delete(crime);
		departamentService.delete(departament);
		employeService.delete(employe);
	}

	private int getResolutionId() {
		crime = getCrime();
		departament = getDepartament();
		employe = getEmploye();
		
		createResolution(crime, departament, employe);
		
		try {
			resolution = resolutionService.save(resolution);
		} catch (Exception e) {
			log.error(e);
		}
		return resolution.getId();
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
