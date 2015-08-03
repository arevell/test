package com.ttc.ch2.ui.moduls.tour.common;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.ecs.html.A;
import org.apache.ecs.html.Div;
import org.apache.ecs.html.Span;
import org.apache.ecs.html.TD;
import org.apache.ecs.html.TH;
import org.apache.ecs.html.THead;
import org.apache.ecs.html.TR;
import org.apache.ecs.html.Table;
import org.apache.ecs.wml.Img;

import com.ttc.ch2.common.DateHelper;
import com.ttc.ch2.common.SpringContext;
import com.ttc.ch2.domain.common.SortCondition.Direction;
import com.ttc.ch2.domain.tour.ContentRepository;
import com.ttc.ch2.ui.common.Ch2Properties;
import com.ttc.ch2.ui.common.config.Ch2URIs;

public class TourMatchingHtmlRenderer extends BasePageRenderer {
	
	private String pathApp;
	public TourMatchingHtmlRenderer()
	{
		Ch2Properties properties=SpringContext.getApplicationContext().getBean(Ch2Properties.class);
		pathApp=StringUtils.isNotBlank(properties.getAppName()) ? "/"+properties.getAppName(): "" ;
	}
	
	public String render(Ch2URIs uri,List<ContentRepository> rows,Direction direction)
	{		
		StringBuilder sb=new StringBuilder();
		sb.append(getTableStyle(pathApp).toString());
		
		Div div=new Div();
		div.addAttribute("class", "datagrid");
		div.addAttribute("style", "width:99,5%;");
		div.addElement(getTable(uri,rows,direction==Direction.ASC));		
		sb.append(div.toString());		
		return sb.toString();
	}
	
	
	
	
	private  Table getTable(Ch2URIs uri,List<ContentRepository> rows,boolean ascSortDirection)
	{
		Table tab=new Table();
		tab.setPrettyPrint(true);
		tab.setTabLevel(0);		
		tab.addAttribute("align", "center");

		TR rowHeader=new TR();
		THead h=new THead();
		h.addElement(rowHeader);
		tab.addElement(h);
		
		TH noHeader=getHeader(null,"100px");
		noHeader.setAlign("left");
		noHeader.addAttribute("class", "thHtmlTabFont");		
		addSortEmptyLink(noHeader, "No.", "thHtmlTabFont");
		noHeader.addAttribute("align","left");	

		rowHeader.addElement(noHeader);
		rowHeader.addElement(getHeader("Tour Code",uri,"TC",ascSortDirection));
		rowHeader.addElement(getHeader("Tour Info","120px",uri,"TI",ascSortDirection));
		rowHeader.addElement(getHeader("Tour Info Date","150px",uri,"TI_date",ascSortDirection));
		rowHeader.addElement(getHeader("Tour Departure","120px",uri,"TD",ascSortDirection));
		rowHeader.addElement(getHeader("Tour Departure Date","150px",uri,"TD_date",ascSortDirection));
		
		TH hBrochure=getHeader(null);
		hBrochure.addAttribute("class", "thHtmlTabFont");
		addSortEmptyLink(hBrochure, "eBrochure", "thHtmlTabFont");
		rowHeader.addElement(hBrochure);
		
		renderRows(tab,rows);
		
		return tab;
	}
	
	private  void renderRows(Table tab,List<ContentRepository> rows)
	{				
		int count=1;
		boolean alt=false;
		for (Iterator<ContentRepository> iterator = rows.iterator(); iterator.hasNext();) {
			ContentRepository rowData =  iterator.next();
			TR rowHtmlData=new TR();
			if(alt)
				rowHtmlData.addAttribute("class", "alt");
			
			
			tab.addElement(rowHtmlData);
			
			TD td0=getTD();
			td0.setTagText(String.valueOf(count++));
			rowHtmlData.addElement(td0);
			
			TD td1=getTD();
//			A link=new A();
//			link.addAttribute("onclick", "return openTourDataWindow('CH', '14SWAA09.xml')");
//			link.addAttribute("href", "javascript:void(0)");
//			link.setTagText(rowData.getMvCode());
			td1.addElement(createLink(rowData.getTourCode(),rowData.getTourCode(),"openTourDataWindow"));
			rowHtmlData.addElement(td1);
			
			TD td2=getTD();
			td2.addElement(getImg(pathApp,calculateTourInfoCross(rowData)));
			td2.addAttribute("align","center");
			rowHtmlData.addElement(td2);
			
			String vTourInfoModified="";
			if(rowData.getTourInfoModified()!=null)
				vTourInfoModified=DateHelper.dateToString(rowData.getTourInfoModified());
			
			TD td2_date=getTD();
			td2_date.setTagText(vTourInfoModified);
			td2_date.addAttribute("align","center");
			rowHtmlData.addElement(td2_date);
			
			TD td3=getTD();
			td3.addElement(getImg(pathApp,calculateTourDepartureCross(rowData)));
			td3.addAttribute("align","center");
			rowHtmlData.addElement(td3);
			
			String vTourDepartureModified="";
			if(rowData.getTourDepartureModified()!=null)
				vTourDepartureModified=DateHelper.dateToString(rowData.getTourDepartureModified());
								
			TD td3_date=getTD();
			td3_date.setTagText(vTourDepartureModified);
			td3_date.addAttribute("align","center");
			rowHtmlData.addElement(td3_date);
			
//			TD tdStatus=getTD();
//			tdStatus.setTagText(rowData.getStatus().toString());
//			tdStatus.addAttribute("align","center");
//			rowHtmlData.addElement(tdStatus);

			TD td4=getTD();
			if (rowData.getIsTourInfoAvailable() && rowData.getIsTourDepartureAvailable()) {
				td4.addElement(createLink("eBrochure example ", rowData.getTourCode(), "openTourDataWindowPdf"));
			}
//			td4.addElement(createLink("Get version 1 ",rowData.getTourCode(),"openTourDataWindow"));
//			td4.addElement(createLink("Get version 3 ",rowData.getTourCode(),"openTourDataWindow"));
			td4.addAttribute("align","center");
			rowHtmlData.addElement(td4);
			alt=!alt;
		}
	}		
	
	private  Span createLink(String linkText,String id,String functionName)
	{
		Span s=new Span();
		s.addAttribute("style", "margin-right:5px");
		A link=new A();
		link.addAttribute("onclick", "return "+functionName+"('"+id+"')");
		link.addAttribute("href", "javascript:void(0)");
		link.setTagText(linkText);
		s.addElement(link);
		return s;
	}
	
	private  Img getImg(String appName,boolean cross)
	{
		Img img=new Img();
		img.addAttribute("src", cross ? appName+"/img/cross.png": appName+"/img/tick.png");
		img.addAttribute("width","30px");
		img.addAttribute("height","30px");
		return img;
	}
	
	
	
	private  boolean calculateTourDepartureCross(ContentRepository cr)
	{		
//	     !(cr.getRepositoryStatus()==RepositoryStatus.TourDepartureOnly || cr.getRepositoryStatus()==RepositoryStatus.TIandTD);
		return    cr.getIsTourDepartureAvailable()==false;
	}
	
	private  boolean calculateTourInfoCross(ContentRepository cr)
	{		
//		return !(cr.getRepositoryStatus()==RepositoryStatus.TourInfoOnly || cr.getRepositoryStatus()==RepositoryStatus.TIandTD);
		return    cr.getIsTourInfoAvailable()==false;
	}
}
