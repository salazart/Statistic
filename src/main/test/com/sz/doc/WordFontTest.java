package com.sz.doc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.sz.db.models.Place;
import com.sz.docx.models.ReferenceData;
import com.sz.docx.services.DocxService;

public class WordFontTest {
	
	private static final String SECTION = "Славутського ВП";
	private static final String RANG = "полковнику";
	private static final String NAME_OF_BOSS = "Шуляренку В.П.";
	private static final String NUMBER_CRIME = "12015240210000804";
	private static final String DATE_CRIME = "15.12.2015";
	private static final String NUMBER_RAPORT = "1255";
	private static final String DATE_RAPORT = "11.12.2015";
	private static final String NUMBER_REFERENCE = "1256";
	private static final String DATE_REFERENCE = "12.12.2015";
	private static final String ARTICLE = "ст.185 ч.3";

	public static void main(String[] args) throws IOException {
		ReferenceData referenceData = createReferanceData();
		
		String fileName = referenceData.getNumberCrime() 
				+ "_" 
				+ referenceData.getNumberRaport() 
				+ ".docx";
		
		DocxService referenceDocx = new DocxService();
		referenceDocx.saveDocument(referenceData, fileName);
	}	
	
	private static ReferenceData createReferanceData() {
		ReferenceData referenceData = new ReferenceData();
		referenceData.setSection(SECTION);
		referenceData.setRang(RANG);
		referenceData.setNameOfBoss(NAME_OF_BOSS);
		referenceData.setNumberCrime(NUMBER_CRIME);
		referenceData.setDateCrime(DATE_CRIME);
		referenceData.setNumberRaport(NUMBER_RAPORT);
		referenceData.setDateRaport(DATE_RAPORT);
		referenceData.setNumberReference(NUMBER_REFERENCE);
		referenceData.setDateReference(DATE_REFERENCE);
		referenceData.setArticle(ARTICLE);
		
		List<Place> places = new ArrayList<Place>();
		places.add(new Place("Місце скоєння злочину", "20300", "8091, 3021", false, true, false));
		places.add(new Place("Місце скоєння злочину", "58081", "15684", true, false, false));
		places.add(new Place("Місце скоєння злочину", "11002", "135, 156, 4866", false, false, true));
		referenceData.setPlaces(places);

		return referenceData;
	}
}
