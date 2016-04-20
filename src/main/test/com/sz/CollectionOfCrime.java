package com.sz;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sz.db.models.Crime;
import com.sz.db.services.CrimeService;

public class CollectionOfCrime {

	public static void main(String[] args) {
		CrimeService crimeService = new CrimeService();
		try {
			List<Crime> crimes = crimeService.getAll();
			
			Map<Integer, String> crimesMap = createMap(crimes);
			System.out.println(crimesMap.size());
			Collection<String> numbersOfCrime = crimesMap.values();
			System.out.println(numbersOfCrime.size());

			CharSequence[] cs = numbersOfCrime.toArray(new CharSequence[numbersOfCrime.size()]);
			System.out.println(new String().join("\",\"", cs));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static Map<Integer, String> createMap(List<Crime> crimes) {
		Map<Integer, String> crimesMap = new HashMap<Integer, String>();
		for (Crime crime : crimes) {
			crimesMap.put(crime.getId(), crime.getNumber());
		}
		return crimesMap;
	}

}
