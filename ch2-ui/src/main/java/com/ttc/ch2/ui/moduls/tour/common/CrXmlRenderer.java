package com.ttc.ch2.ui.moduls.tour.common;

import java.util.Iterator;
import java.util.List;

import org.apache.ecs.Element;
import org.apache.ecs.xml.XML;


import org.apache.ecs.xml.XMLDocument;

import com.ttc.ch2.common.DateHelper;
import com.ttc.ch2.domain.tour.ContentRepository;

public class CrXmlRenderer  implements Renderer{
   
	private List<ContentRepository> rows;
	private TypeFile requestType;	
	private String userName;
	
	
	private XMLDocument xml;
	private XML page=null;
		
	public CrXmlRenderer(List<ContentRepository> rows,	TypeFile requestType,String userName) {
		super();
		this.rows = rows;
		this.requestType = requestType;
		this.userName=userName;
	}
	
	
	public String generate(){
		
		preparePage();
		
		generateContent();
		
		return xml.toString();
	}
	
	private void generateContent(){
		
		XML header=new XML("header",false);
		setupDefaultParams(header, 1);
		header.addAttribute("user", userName);
		switch (requestType) {
			case TOUR_INFO_OLD:header.addAttribute("name", "Central Repository - Tour Info Version 1"); break;
			case TOUR_INFO_NEW:header.addAttribute("name", "Central Repository - Tour Info Version 3"); break;
			case TOUR_DEPARTURE_OLD:header.addAttribute("name", "Central Repository - Tour Departure Version 1"); break;
			case TOUR_DEPARTURE_NEW:header.addAttribute("name", "Central Repository - Tour Departure Version 3"); break;			
			default:
		break;
		}
		
		page.addElement(header);
		page.addElement(getContent(rows));
	}
	
	
	
	private  XML getContent(List<ContentRepository> rows)
	{		
		XML content = new XML("content");	
		setupDefaultParams(content, 1);
		int count=1;		
		for (Iterator<ContentRepository> iterator = rows.iterator(); iterator.hasNext();) {
			ContentRepository rowData =  iterator.next();
			
			XML element=new XML("element");
			setupDefaultParams(element, 2);
			content.addElement(element);
			
			XML value1=new XML("value");
			setupDefaultParams(value1, 3);
			element.addElement(value1);
			value1.addAttribute("name", "index");
			value1.setTagText(String.valueOf(count++));
			
			XML value2=new XML("value");
			setupDefaultParams(value2, 3);
			element.addElement(value2);
			value2.addAttribute("name", "tourCode");
			value2.setTagText(rowData.getTourCode());
			
			XML value3=new XML("value");
			setupDefaultParams(value3, 3);
			element.addElement(value3);
			value3.addAttribute("name", "size");
			value3.setTagText(getSize(rowData));
			
			XML value4=new XML("value");
			setupDefaultParams(value4, 3);
			element.addElement(value4);
			value4.addAttribute("name", "lastModified");
			value4.setTagText(getLastModified(rowData));			
		}
		return content;
	}
	
	
	
	
	private String getLastModified(ContentRepository rowData) {
		switch(requestType){
			case TOUR_DEPARTURE_NEW: return rowData.getTourDepartureModified() !=null ? DateHelper.dateToString(rowData.getTourDepartureModified(),DateHelper.DEFAULT_DATE_FORMAT_CR_AND_MSGS) : "";
			case TOUR_DEPARTURE_OLD: return rowData.getTourDepartureModified() !=null ? DateHelper.dateToString(rowData.getTourDepartureModified(),DateHelper.DEFAULT_DATE_FORMAT_CR_AND_MSGS) : "";
			case TOUR_INFO_NEW: return rowData.getTourInfoModified() !=null ? DateHelper.dateToString(rowData.getTourInfoModified(),DateHelper.DEFAULT_DATE_FORMAT_CR_AND_MSGS) : "";
			case TOUR_INFO_OLD: return rowData.getTourInfoModified()  !=null ? DateHelper.dateToString(rowData.getTourInfoModified(),DateHelper.DEFAULT_DATE_FORMAT_CR_AND_MSGS) : "";
			default: return "";
		}
	}


	private String getSize(ContentRepository rowData) {
		SizeConvert converter=new SizeConvert();
		switch(requestType){
		case TOUR_DEPARTURE_NEW: return rowData.getTourDepartureXMLSize() !=null ? converter.coerceToUi(rowData.getTourDepartureXMLSize(), null, null): "";
		case TOUR_DEPARTURE_OLD: return rowData.getOldTourDepartureXMLSize() !=null ? converter.coerceToUi(rowData.getOldTourDepartureXMLSize(), null, null): "";
		case TOUR_INFO_NEW: return rowData.getTourInfoXMLSize() !=null ? converter.coerceToUi(rowData.getTourInfoXMLSize(), null, null) : "";
		case TOUR_INFO_OLD: return rowData.getOldTourInfoXMLSize()  !=null ?converter.coerceToUi(rowData.getOldTourInfoXMLSize(), null, null)  : "";
		default: return "";
	}
	}
	
	
	

	private  void preparePage(){
		this.xml=new XMLDocument();
		this.page=new XML("Response",true);		
		setupDefaultParams(page, 0);
		xml.addElement(this.page);
	}
	
	
	private void setupDefaultParams(Element e,int tabLevel){
		e.setPrettyPrint(true);
		e.setTabLevel(tabLevel);
	}		
}