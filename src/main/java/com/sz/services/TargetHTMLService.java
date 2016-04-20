package com.sz.services;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.sz.db.models.Target;

public class TargetHTMLService {
	
	public String getTargetHTML(HttpServletRequest request, List<Target> targets, String htmlText){
		String targetHTML = "";
		
		for(int i = 0; i < targets.size(); i++){
			
			String newRowHtml = htmlText;
			
			newRowHtml = createTargetName(newRowHtml, i);
			
			newRowHtml = createTargetValue(targets.get(i), newRowHtml);
			
			targetHTML += newRowHtml;
		}
		
		if(targets.isEmpty()){
			targetHTML = htmlText;
			targetHTML = createTargetName(targetHTML, 0);
			
			Target target = new Target();
			target.setTarget("Моніторинг");
			targetHTML = createTargetValue(target, targetHTML);
		}
		
		return targetHTML;
	}
	
	private String createTargetValue(Target target, String newRowHtml) {
		newRowHtml = newRowHtml.replace("objectValue", target.getTarget());
		if(target.isOperatorVodafone())
			newRowHtml = newRowHtml.replace("vodafoneChecked", "checked");
		
		if(target.isOperatorKyivstar())
			newRowHtml = newRowHtml.replace("kyivstarChecked", "checked");
		
		if(target.isOperatorLifecell())
			newRowHtml = newRowHtml.replace("lifecellChecked", "checked");
		
		if(target.isSendTarget())
			newRowHtml = newRowHtml.replace("sendChecked", "checked");
		
		return newRowHtml;
	}
	
	private String createTargetName(String targetHTML, int index){
		targetHTML = targetHTML.replace("objectName", "objectName_" + index);
		targetHTML = targetHTML.replace("operatorVodafone", "operatorVodafone_" + index);
		targetHTML = targetHTML.replace("operatorKyivstar", "operatorKyivstar_" + index);
		targetHTML = targetHTML.replace("operatorLifecell", "operatorLifecell_" + index);
		targetHTML = targetHTML.replace("sendObject", "sendObject_" + index);
		return targetHTML;
	}
}
