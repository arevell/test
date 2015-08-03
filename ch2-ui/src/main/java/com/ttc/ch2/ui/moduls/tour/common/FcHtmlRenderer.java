package com.ttc.ch2.ui.moduls.tour.common;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.ecs.html.A;
import org.apache.ecs.html.BR;
import org.apache.ecs.html.Body;
import org.apache.ecs.html.Div;
import org.apache.ecs.html.Head;
import org.apache.ecs.html.Html;
import org.apache.ecs.html.Span;
import org.apache.ecs.html.TD;
import org.apache.ecs.html.TH;
import org.apache.ecs.html.THead;
import org.apache.ecs.html.TR;
import org.apache.ecs.html.Table;

import com.ttc.ch2.bl.filecollect.FileCollectService.FileCollectVersion;
import com.ttc.ch2.bl.filecollect.FileCollectVO;
import com.ttc.ch2.common.SecurityHelper;
import com.ttc.ch2.common.SpringContext;
import com.ttc.ch2.domain.Brand;
import com.ttc.ch2.domain.SellingCompany;
import com.ttc.ch2.ui.common.Ch2Properties;
import com.ttc.ch2.ui.common.security.PathType;

public class FcHtmlRenderer extends BasePageRenderer implements  Renderer{
	
	private List<FileCollectVO> rows;
	private FileCollectVersion requestType;	
	private String userName;
	
	
	private Html page=null;
	private Head head=null;
	private Body body=null;
	private String pathApp;


	
	public FcHtmlRenderer(List<FileCollectVO> rows,	FileCollectVersion requestType,String userName) {
		super();
		this.rows = rows;
		this.requestType = requestType;	
		this.userName=userName;
		
		Ch2Properties properties=SpringContext.getApplicationContext().getBean(Ch2Properties.class);
		pathApp=StringUtils.isNotBlank(properties.getAppName()) ? "/"+properties.getAppName(): "" ;
	}
	
	
	public String generate(){
		
		preparePage();
		
		generateContent();
		
		return page.toString();
	}
	
	private void generateContent(){
		
		Div headerDiv=new Div();
		headerDiv.addAttribute("style", "background: transparent;");
		setupDefaultParams(headerDiv, 1);
		
		// title
		Span title=new Span();
		setupDefaultParams(title, 2);
		title.addAttribute("class", "z-caption-l titleHtmlPageFont");
		switch (requestType) {
			case V1:title.setTagText("File collect - Version 1"); break;
			case V3:title.setTagText("File collect - Version 3"); break;						
		default:
			break;
		}
		headerDiv.addElement(title);
		headerDiv.addElement(new BR());
		
		
		// user
				Div user=new Div();
				setupDefaultParams(user, 2);
				user.setTagText("User:"+userName);
				user.addAttribute("class", "hightlight z-label titleHtmlPageFont");
		
		// help
				Div help=new Div();
				setupDefaultParams(help, 2);		
				help.addAttribute("width", "100%");
				help.addAttribute("style", "text-align:right;");		
				help.addAttribute("class", "hightlight z-label titleHtmlPageFont");
				
				A helpLink=new A();		
				setupDefaultParams(helpLink, 3);
				help.addElement(helpLink);
				helpLink.setTagText("Help");
				helpLink.setHref(getHelpLink());
				helpLink.setTarget("_blank");
				
		headerDiv.addElement(wrapHorizontalToTable(2, new String []{"left","right"},user,help));
		
		body.addElement(headerDiv);
				
		body.addElement(renderData(rows));
	}
	
	
	
	private  Div renderData(List<FileCollectVO> rows)
	{		
		// content all brands
		Div data=new Div();
		setupDefaultParams(data, 3);
		
		for (FileCollectVO fileCollectVO : rows) {			
			Brand brand=fileCollectVO.getBrand();
			
			// brand header and table conntent			
						
			Span brandSpan=new Span();
			brandSpan.addAttribute("class", "hightlight z-label titleHtmlPageFont");
			setupDefaultParams(brandSpan, 4);
			brandSpan.setTagText("Brand:"+brand.getCode());
						
			// table div
			Div divTab=new Div();
			setupDefaultParams(divTab, 3);
			divTab.addAttribute("class", "datagrid");
			divTab.addAttribute("style", "width:99,5%;");			
			data.addElement(wrapVerticalToTable(4,brandSpan, divTab));
			
			// table
			Table tab=new Table();
			divTab.addElement(tab);		
			setupDefaultParams(tab, 4);		
			tab.addAttribute("align", "center");
			
			TR rowHeader=new TR();
			THead h=new THead();
			h.addElement(rowHeader);
			tab.addElement(h);
			
			
			rowHeader.addElement(getHeader("No.","100px", thTextClass));
			rowHeader.addElement(getHeader("Company Code", thTextClass));
			rowHeader.addElement(getHeader("Operation","120px", thTextClass));
			
			renderRows(tab,fileCollectVO.getSellingCompanies());						
		}		
		return data;
	}
	
	
	protected  TH getHeader(String text,String thTextClass)
	{
		TH th=getHeader(text);
		th.addAttribute("style", thTextClass);
		return th;
	}
	
	protected  TH getHeader(String text,String width,String thTextClass)
	{
		TH th=getHeader(text);
		th.addAttribute("width", width);
		th.addAttribute("class", thTextClass);
		return th;
	}
	
	private  void renderRows(Table tab,List<SellingCompany> rows)
	{				
		int count=1;
		boolean alt=false;
		for (Iterator<SellingCompany> iterator = rows.iterator(); iterator.hasNext();) {
			SellingCompany rowData =  iterator.next();
			TR rowHtmlData=new TR();
			if(alt)
				rowHtmlData.addAttribute("class", "alt");
			
			
			tab.addElement(rowHtmlData);
			
			TD td0=getTD();
			td0.setTagText(String.valueOf(count++));
			rowHtmlData.addElement(td0);
			
			TD td1=getTD();
			td1.addElement(rowData.getCode());
			td1.addAttribute("align","left");
			rowHtmlData.addElement(td1);
			
			TD td2=getTD();
			td2.addElement(createLink("download",generateLink(rowData)));
			rowHtmlData.addElement(td2);
					
			alt=!alt;
		}
	}		


	private  void preparePage(){
		this.page=new Html();
		setupDefaultParams(page,0);
		head= new Head();
		setupDefaultParams(head, 0);
		page.addElement(head);		
		head.addElement(getMainStyle(pathApp));
		head.addElement(getTableStyle(pathApp));
		this.body=new Body();
		setupDefaultParams(body, 0);
		page.addElement(body);	
	}
	
	
	
	public String generateLink(SellingCompany selected){		
		switch (requestType) {
		case V3: return pathApp+"/"+PathType.O_ARCHIVES.getPartPath()+"/V3/"+selected.getCode()+".zip?token="+SecurityHelper.getToken();	
		case V1: return pathApp+"/"+PathType.O_ARCHIVES.getPartPath()+"/V1/"+selected.getCode()+".zip?token="+SecurityHelper.getToken();				
		default: return null;
		}		
	}
	
	
	private String getHelpLink(){
	    switch (requestType) {
    	    case V1:
    	    case V3:                        
    	        return pathApp+"/help/CH2_API_User_Guide.pdf";
	    }
	    return pathApp+"/help/CH2_API_User_Guide_Brands.pdf";		
	}
}
