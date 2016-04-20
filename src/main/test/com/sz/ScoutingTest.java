package com.sz;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.Date;
import java.util.Calendar;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import com.sz.db.interfaces.GenericDao;
import com.sz.db.models.Crime;
import com.sz.db.models.Departament;
import com.sz.db.models.Employe;
import com.sz.db.models.Scouting;
import com.sz.db.services.CrimeService;
import com.sz.db.services.DepartamentService;
import com.sz.db.services.EmployeService;
import com.sz.db.services.PersistException;
import com.sz.db.services.ScoutingService;

public class ScoutingTest {
	private static final Logger log = LogManager.getRootLogger();
	
	private Scouting scouting;
	private GenericDao<Scouting> scoutingService;
	private GenericDao<Crime> crimeService;
	private GenericDao<Departament> departamentService;
	private GenericDao<Employe> employeService;
	
	@Before
	public void init() {
		scouting = new Scouting();
		scoutingService = new ScoutingService();
		crimeService = new CrimeService();
		departamentService = new DepartamentService();
		employeService = new EmployeService();
	}
	
	@Test
	public void test() {
		Crime crime = getCrime();
		Departament departament = getDepartament();
		Employe employe = getEmploye();
		
		createScouting(crime, departament, employe);
		
		try {
			scouting = scoutingService.save(scouting);
		} catch (Exception e) {
			log.error(e);
		}
		assertTrue(scouting.getId() != 0);
		
		Scouting scouting2 = null;
		try {
			scouting2 = scoutingService.get(scouting.getId());
		} catch (PersistException e) {
			log.error(e);
		}
		assertNotNull(scouting2);
		assertTrue(scouting2.getId() != 0);
		
		scoutingService.delete(scouting);
		crimeService.delete(crime);
		departamentService.delete(departament);
		employeService.delete(employe);
	}

	private void createScouting(Crime crime, Departament departament, Employe employe) {
		scouting.setIdCrime(crime.getId());
		scouting.setIdDepartament(departament.getId());
		scouting.setIdEmploye(employe.getId());
		
		scouting.setDateAdd(new Date(Calendar.getInstance().getTimeInMillis()));
		scouting.setNumberRaport("65198/564");
		scouting.setDateRaport(new Date(Calendar.getInstance().getTimeInMillis()));
		scouting.setNumberReference("654/654169/49684-k");
		scouting.setDateReference(new Date(Calendar.getInstance().getTimeInMillis()));
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
