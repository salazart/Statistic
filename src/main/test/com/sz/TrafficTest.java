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
import com.sz.db.models.Traffic;
import com.sz.db.services.CrimeService;
import com.sz.db.services.DepartamentService;
import com.sz.db.services.PersistException;
import com.sz.db.services.TrafficService;

public class TrafficTest {
	private static final Logger log = LogManager.getRootLogger();
	
	private Traffic traffic;
	private GenericDao<Traffic> dao;
	private GenericDao<Crime> crimeService;
	private GenericDao<Departament> departamentService;
	
	@Before
	public void init() {
		traffic = new Traffic();
		dao = new TrafficService();
		crimeService = new CrimeService();
		departamentService = new DepartamentService();
	}
	
	@Test
	public void test() {
		Crime crime = getCrime();
		Departament departament = getDepartament();
		
		createTraffic(crime, departament);
		
		try {
			traffic = dao.save(traffic);
		} catch (Exception e) {
			log.error(e);
		}
		assertTrue(traffic.getId() != 0);
		
		Traffic traffic2 = null;
		try {
			traffic2 = dao.get(traffic.getId());
		} catch (PersistException e) {
			log.error(e);
		}
		assertNotNull(traffic2);
		assertTrue(traffic2.getId() != 0);
		
		dao.delete(traffic2);
		crimeService.delete(crime);
		departamentService.delete(departament);
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
	
	private void createTraffic(Crime crime, Departament departament) {
		traffic.setIdCrime(crime.getId());
		traffic.setIdDepartament(departament.getId());
		traffic.setDateAdd(new Date(Calendar.getInstance().getTimeInMillis()));
	}
}
