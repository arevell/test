package com.ttc.ch2.ui.moduls.tour.common;

import java.util.Iterator;
import java.util.List;

import org.apache.ecs.Element;
import org.apache.ecs.xml.XML;
import org.apache.ecs.xml.XMLDocument;

import com.ttc.ch2.bl.filecollect.FileCollectService.FileCollectVersion;
import com.ttc.ch2.bl.filecollect.FileCollectVO;
import com.ttc.ch2.domain.SellingCompany;

public class FcXmlRenderer  implements Renderer{
   
	private List<FileCollectVO> rows;
	private FileCollectVersion requestType;	
	private String userName;
	
	private XMLDocument xml;
	private XML page=null;
		
	public FcXmlRenderer(List<FileCollectVO> rows,	FileCollectVersion requestType,String userName) {
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
			case V1:header.addAttribute("name", "File collect - Version 1"); break;
			case V3:header.addAttribute("name", "File collect - Version 3"); break;			
			default:
		break;
		}
		
		page.addElement(header);
		page.addElement(getContent(rows));
	}
	
	
	
	private  XML getContent(List<FileCollectVO> rows)
	{		
		XML content = new XML("content");	
		setupDefaultParams(content, 1);
				
		for (Iterator<FileCollectVO> iterator = rows.iterator(); iterator.hasNext();) {
			FileCollectVO rowData =  iterator.next();
			int count=1;
			
			XML brand=new XML("brand");
			setupDefaultParams(brand, 2);
			brand.addAttribute("brandCode", rowData.getBrand().getCode());
			content.addElement(brand);			
			for (SellingCompany sc : rowData.getSellingCompanies()) {
		
				XML element=new XML("element");			
				setupDefaultParams(element, 3);
				brand.addElement(element);
				
				XML value1=new XML("value");
				setupDefaultParams(value1, 4);
				element.addElement(value1);
				value1.addAttribute("name", "index");
				value1.setTagText(String.valueOf(count++));
				
				XML value2=new XML("value");
				setupDefaultParams(value2, 4);
				element.addElement(value2);
				value2.addAttribute("name", "sellingCompanyCode");
				value2.setTagText(sc.getCode());
			}			
		}
		return content;
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