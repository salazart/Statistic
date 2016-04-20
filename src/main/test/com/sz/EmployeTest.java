package com.sz;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.sz.db.interfaces.GenericDao;
import com.sz.db.models.Departament;
import com.sz.db.models.Employe;
import com.sz.db.services.EmployeService;
import com.sz.db.services.PersistException;

public class EmployeTest extends Assert{
	private static final Logger log = LogManager.getRootLogger();
	
	private Employe employe;
	private GenericDao<Employe> dao;
	
	@Before
	public void init() {
		employe = new Employe();
		dao = new EmployeService();
	}
	
	@Test
	public void test() {
		employe.setName("Ivanov");
		
		try {
			employe = dao.save(employe);
		} catch (Exception e) {
			log.error(e);
		}
		assertTrue(employe.getId() != 0);
		
		Employe employe2 = null;
		try {
			employe2 = dao.get(employe.getId());
		} catch (PersistException e) {
			log.error(e);
		}
		assertNotNull(employe2);
		assertTrue(employe2.getId() != 0);
		
		dao.delete(employe);
	}
}
