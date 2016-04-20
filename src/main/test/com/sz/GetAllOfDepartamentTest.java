package com.sz;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sz.db.models.Departament;
import com.sz.db.services.DepartamentService;

public class GetAllOfDepartamentTest {

	public static void main(String[] args) {
		DepartamentService departamentService = new DepartamentService();
		try {
			
			Map<Integer, String> departaments = createDepartamentMap(departamentService.getAll());
			System.out.println(departaments.size());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static Map<Integer, String> createDepartamentMap(List<Departament> objects) {
		Map<Integer, String> objectMap = new HashMap<Integer, String>();
		for (Departament object : objects) {
			objectMap.put(object.getId(), object.getName());
		}
		return objectMap;
	}
}
