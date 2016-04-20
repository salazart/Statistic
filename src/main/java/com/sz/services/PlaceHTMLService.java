package com.sz.services;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.sz.db.models.Place;

public class PlaceHTMLService {
	
	public String getPlaceHTML(HttpServletRequest request, List<Place> places, String htmlText){

		String placeHTML = "";
		for(int i = 0; i < places.size(); i++){
			
			String newRowHtml = htmlText;
			
			newRowHtml = createPlaceName(newRowHtml, i);
			
			newRowHtml = createPlaceValue(places.get(i), newRowHtml);
			
			placeHTML += newRowHtml;
		}
		
		if(places.isEmpty()){
			placeHTML = htmlText;
			placeHTML = createPlaceName(placeHTML, 0);
			placeHTML = createPlaceValue(new Place(), placeHTML);
		}
		
		return placeHTML;
	}
	
	private String createPlaceValue(Place place, String newRowHtml) {
		newRowHtml = newRowHtml.replace("placeValue", place.getPlace());
		if(place.isOperatorVodafone())
			newRowHtml = newRowHtml.replace("vodafoneChecked", "checked");
		
		if(place.isOperatorKyivstar())
			newRowHtml = newRowHtml.replace("kyivstarChecked", "checked");
		
		if(place.isOperatorLifecell())
			newRowHtml = newRowHtml.replace("lifecellChecked", "checked");
		
		newRowHtml = newRowHtml.replace("lacValue", place.getLac());
		newRowHtml = newRowHtml.replace("cidValue", place.getCid());
		return newRowHtml;
	}
	
	private String createPlaceName(String placeHTML, int index){
		placeHTML = placeHTML.replace("placeName", "placeName_" + index);
		placeHTML = placeHTML.replace("operatorVodafone", "operatorVodafone_" + index);
		placeHTML = placeHTML.replace("operatorKyivstar", "operatorKyivstar_" + index);
		placeHTML = placeHTML.replace("operatorLifecell", "operatorLifecell_" + index);
		placeHTML = placeHTML.replace("placeLac", "placeLac_" + index);
		placeHTML = placeHTML.replace("placeCid", "placeCid_" + index);
		return placeHTML;
	}
}
