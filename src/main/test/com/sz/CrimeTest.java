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
import com.sz.db.services.CrimeService;
import com.sz.db.services.PersistException;

public class CrimeTest extends Assert{
	private static final Logger log = LogManager.getRootLogger();
	
	private Crime crime;
	private GenericDao<Crime> dao;
	
	@Before
	public void init() {
		crime = new Crime();
		dao = new CrimeService();
	}
	
	@Test
	public void test() {
		crime.setCrimeDate(new Date(Calendar.getInstance().getTimeInMillis()));
		crime.setNumber("1201524659468414");
		crime.setRegistrationDate(new Date(Calendar.getInstance().getTimeInMillis()));
		crime.setArticle("Злочин");
		crime.setType("ЄРДР");
		crime.setStory("sdfsdafsav asdgagbds фывпуп фывавыав");
		
		try {
			crime = dao.save(crime);
		} catch (Exception e) {
			log.error(e);
		}
		assertTrue(crime.getId() != 0);
		
		Crime crime2 = null;
		try {
			crime2 = dao.get(crime.getId());
		} catch (PersistException e) {
			log.error(e);
		}
		assertNotNull(crime2);
		assertTrue(crime2.getId() != 0);
		
		dao.delete(crime);
	}
}
