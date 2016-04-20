package com.sz.docx.services;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import com.sz.db.models.Place;
import com.sz.docx.models.AboutParagraph;
import com.sz.docx.models.BSParagraph;
import com.sz.docx.models.BodyParagraph;
import com.sz.docx.models.HeaderParagraph;
import com.sz.docx.models.ReferenceData;
import com.sz.docx.models.SignParagraph;
import com.sz.docx.models.TitleParagraph;

public class DocxService {	
	private static final Logger log = LogManager.getRootLogger();
	
	private void createContentDocx(XWPFDocument document, ReferenceData referenceData){
		
		//XWPFRun upHeaderParagraph = new UpHeaderParagraph(document).getParagraph();
		   
		createHeader(document, referenceData);
		
		XWPFRun titleParagraph = new TitleParagraph(document).getParagraph();
		titleParagraph.setText("Довідка");
		titleParagraph.setBold(true);
		
		XWPFRun aboutParagraph = new AboutParagraph(document).getParagraph();
		aboutParagraph.setText("Про результати радіотехнічної розвідки:");
		
		createBody(document, referenceData);
		
		createPlaces(document, referenceData);
		
		createSign(document);
		
		//XWPFRun labelParagraph = new AboutParagraph(document).getParagraph();
		//labelParagraph.setText("вих. " + referenceData.getNumberReference() + " від " + referenceData.getDateReference());
		
	}

	private void createPlaces(XWPFDocument document, ReferenceData referenceData) {
		List<Place> places = referenceData.getPlaces();
		String textPlace = "";
		for (Place place : places) {
			if(textPlace.equals(place.getPlace())){
				createOperator(document, place);
			} else {
				textPlace = place.getPlace();
				createPlace(document, place);
				createOperator(document, place);
			}
		}
	}

	private void createHeader(XWPFDocument document, ReferenceData referenceData) {
		XWPFRun headerParagraph = new HeaderParagraph(document).getParagraph();
		headerParagraph.setText("Начальникові " + referenceData.getSection());
		headerParagraph.addCarriageReturn();
		headerParagraph.setText("ГУНП в Хмельницькій області");
		headerParagraph.addCarriageReturn();
		headerParagraph.setText(referenceData.getRang() + " поліції");
		
		XWPFRun headerParagraph2 = new HeaderParagraph(document).getParagraph();
		headerParagraph2.setBold(true);
		headerParagraph2.setText(referenceData.getNameOfBoss());
	}

	private void createPlace(XWPFDocument document, Place place) {
		XWPFRun placeParagraph = new TitleParagraph(document).getParagraph();
		placeParagraph.setText(place.getPlace());
		placeParagraph.setBold(true);
	}

	private void createBody(XWPFDocument document, ReferenceData referenceData) {
		XWPFRun bodyParagraph1 = new BodyParagraph(document).getParagraph();
		bodyParagraph1.setText("На підставі рапорту начальника " 
				+ referenceData.getSection() 
				+ " ГУНП України в Хмельницькій області (вих. №" 
				+ referenceData.getNumberRaport() 
				+ " від " 
				+ referenceData.getDateRaport()
				+ ") за фактом злочину передбаченого статтею " 
				+ referenceData.getArticle()
				+ " Кримінального кодексу України.");
		
		XWPFRun bodyParagraph2 = new BodyParagraph(document).getParagraph();
		bodyParagraph2.setText("Зареєстровано в ЄРДР №" 
		+ referenceData.getNumberCrime()
		+ " від " 
		+ referenceData.getDateCrime()
		+ " року працівниками кримінальної поліції ГУНП в Хмельницькій області було проведено радіотехнічну розвідку на місці скоєння злочину.");
		
		XWPFRun bodyParagraph3 = new BodyParagraph(document).getParagraph();
		bodyParagraph3.setText("Під час проведення радіотехнічної розвідки встановлено, що на місці вчинення злочину працюють наступні базові станції операторів рухомого (мобільного) зв’язку:");
	}

	private void createOperator(XWPFDocument document, Place place) {
		XWPFRun ksParagraph = new BSParagraph(document).getParagraph();
		ksParagraph.setBold(true);
		ksParagraph.setText(place.getOperator());
		
		XWPFRun ksvParagraph = new BSParagraph(document).getParagraph();
		ksvParagraph.setText("LAC: ");
		ksvParagraph.setText(place.getLac());
		ksvParagraph.setText("; CID: ");
		ksvParagraph.setText(place.getCid());
	}
	
	private void createSign(XWPFDocument document) {
		XWPFRun signParagraph = new SignParagraph(document).getParagraph();
		signParagraph.setText("Начальник управління");
		signParagraph.addCarriageReturn();
		signParagraph.setText("кримінальної поліції ГУНП");
		signParagraph.addCarriageReturn();
		signParagraph.setText("в Хмельницькій області");
		signParagraph.addCarriageReturn();
		String text = "полковник поліції";
		for(int i = 0; i < 83; i++){
			text += " ";
		}
		text += "В.І. Дуда";
		signParagraph.setText(text);
		signParagraph.addCarriageReturn();
	}
	
	public XWPFDocument getDocument(ReferenceData referenceData){
		XWPFDocument document = new XWPFDocument();
		createContentDocx(document, referenceData);
		return document;
	}
	
	public void saveDocument(ReferenceData referenceData, String fileName){
		XWPFDocument document = new XWPFDocument();
		createContentDocx(document, referenceData);
		
		try(FileOutputStream out = new FileOutputStream(new File(fileName))){
			document.write(out);
			System.out.println("fontstyle.docx written successully");
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
