package com.sz.docx.models;

import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

public class BSParagraph {
	private static final int INDENTATION_FIRST_LINE = 700; 
	private static final String FONT_TYPE = "Times New Roman";
	private static final int FONT_SIZE = 14; 
	
	private XWPFRun xwpfRun;
	
	public BSParagraph(XWPFDocument document) {
		XWPFParagraph paragraph = document.createParagraph();
		paragraph.setIndentationFirstLine(INDENTATION_FIRST_LINE);
		paragraph.setAlignment(ParagraphAlignment.BOTH);
		paragraph.setSpacingAfter(0);
		
		xwpfRun = paragraph.createRun();
		xwpfRun.setFontFamily(FONT_TYPE);
		xwpfRun.getCTR().getRPr().getRFonts().setHAnsi(FONT_TYPE);
		xwpfRun.setFontSize(FONT_SIZE);
	}
	
	public XWPFRun getParagraph(){
		return xwpfRun;
	}
}
