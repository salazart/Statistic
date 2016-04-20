package com.sz.docx.models;

import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

public class UpHeaderParagraph {
	private static final String FONT_TYPE = "Times New Roman";
	private static final int FONT_SIZE = 10; 
	
	private XWPFRun xwpfRun;
	
	public UpHeaderParagraph(XWPFDocument document) {
		XWPFParagraph paragraph = document.createParagraph();
		paragraph.setAlignment(ParagraphAlignment.LEFT);
		paragraph.setIndentationLeft(6000);
		
		xwpfRun = paragraph.createRun();
		xwpfRun.setFontFamily(FONT_TYPE);
		xwpfRun.getCTR().getRPr().getRFonts().setHAnsi(FONT_TYPE);
		xwpfRun.setFontSize(FONT_SIZE);
	}
	
	public XWPFRun getParagraph(){
		return xwpfRun;
	}
}
