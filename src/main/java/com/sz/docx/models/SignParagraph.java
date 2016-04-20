package com.sz.docx.models;

import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

public class SignParagraph {
	private static final String FONT_TYPE = "Times New Roman";
	private static final int FONT_SIZE = 14; 
	
	protected XWPFRun xwpfRun;
	
	public SignParagraph(XWPFDocument document) {
		XWPFParagraph paragraph = document.createParagraph();
		paragraph.setAlignment(ParagraphAlignment.LEFT);
		paragraph.setSpacingAfter(0);
		paragraph.setSpacingBeforeLines(300);
		
		xwpfRun = paragraph.createRun();
		xwpfRun.setFontFamily(FONT_TYPE);
		xwpfRun.getCTR().getRPr().getRFonts().setHAnsi(FONT_TYPE);
		xwpfRun.setFontSize(FONT_SIZE);
		xwpfRun.setBold(true);
	}
	
	public XWPFRun getParagraph(){
		return xwpfRun;
	}
}
