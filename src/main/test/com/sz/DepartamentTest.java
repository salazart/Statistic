package com.sz;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.sz.db.interfaces.GenericDao;
import com.sz.db.models.Departament;
import com.sz.db.services.DepartamentService;
import com.sz.db.services.PersistException;

public class DepartamentTest extends Assert{
	private static final Logger log = LogManager.getRootLogger();
	
	private Departament departament;
	private GenericDao<Departament> dao;
	
	@Before
	public void init() {
		departament = new Departament();
		dao = new DepartamentService();
	}
	
	@Test
	public void test() {
		departament.setName("NLO");
		
		try {
			departament = dao.save(departament);
		} catch (Exception e) {
			log.error(e);
		}
		assertTrue(departament.getId() != 0);
		
		Departament departament2 = null;
		try {
			departament2 = dao.get(departament.getId());
		} catch (PersistException e) {
			log.error(e);
		}
		assertNotNull(departament2);
		assertTrue(departament2.getId() != 0);
		
		dao.delete(departament);
	}
}
