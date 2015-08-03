package com.ttc.ch2.ui.moduls.tour.common;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.List;

import javax.crypto.NoSuchPaddingException;
import javax.servlet.http.HttpServletRequest;

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

import com.ttc.ch2.common.DateHelper;
import com.ttc.ch2.common.SecurityHelper;
import com.ttc.ch2.common.SpringContext;
import com.ttc.ch2.domain.common.SortCondition.Direction;
import com.ttc.ch2.domain.tour.ContentRepository;
import com.ttc.ch2.ui.common.Ch2Properties;
import com.ttc.ch2.ui.common.security.PathType;
import com.ttc.common.params.ParamsUtils;

public class CrHtmlRenderer extends BasePageRenderer implements  Renderer{

	

	private List<ContentRepository> rows;
	private TypeFile requestType;	
	private HttpServletRequest request;
	private String brandCode;
	private Direction direction;
	private String userName;
	
	
	private Html page=null;
	private Head head=null;
	private Body body=null;
	private String pathApp;


	
	public CrHtmlRenderer(List<ContentRepository> rows,	TypeFile requestType, HttpServletRequest request,String brandCode,Direction direction,String userName) {
		super();
		this.rows = rows;
		this.requestType = requestType;
		this.request = request;
		this.brandCode=brandCode;
		this.direction=direction;
		this.userName=userName;
		
		Ch2Properties properties=SpringContext.getApplicationContext().getBean(Ch2Properties.class);
		pathApp=StringUtils.isNotBlank(properties.getAppName()) ? "/"+properties.getAppName(): "" ;
	}
	
	
	public String generate() throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException{
		
		preparePage();
		
		generateContent();
		
		return page.toString();
	}
	
	private void generateContent() throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException{
		
		Div headerDiv=new Div();
		headerDiv.addAttribute("style", "background: transparent;");
		Span title=new Span();
		title.addAttribute("class", "z-caption-l titleHtmlPageFont");
		switch (requestType) {
			case TOUR_INFO_OLD:title.setTagText("Central Repository - Tour Info Version 1"); break;
			case TOUR_INFO_NEW:title.setTagText("Central Repository - Tour Info Version 3"); break;
			case TOUR_DEPARTURE_OLD:title.setTagText("Central Repository - Tour Departure Version 1"); break;
			case TOUR_DEPARTURE_NEW:title.setTagText("Central Repository - Tour Departure Version 3"); break;			
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
				
		Div div=new Div();
		div.addAttribute("class", "datagrid");
		div.addAttribute("style", "width:99,5%;");						
		div.addElement(getTable(rows));
		body.addElement(div);
	}
	
	
	
	private  Table getTable(List<ContentRepository> rows) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException
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
		noHeader.addAttribute("class", thTextClass);		
		addSortEmptyLink(noHeader, "No.", thTextClass);
		noHeader.addAttribute("align","left");	
		
		rowHeader.addElement(noHeader);
		rowHeader.addElement(getSortHeader("Tour Code","TC"));
		rowHeader.addElement(getSortHeader("Size","S"));
		rowHeader.addElement(getSortHeader("Last Modified","LM"));
		
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
			td1.addElement(createLink(rowData.getTourCode(),generateLink(rowData, brandCode)));
			rowHtmlData.addElement(td1);
			
			TD td2=getTD();
			td2.addElement(String.valueOf(getSize(rowData)));
			td2.addAttribute("align","center");
			rowHtmlData.addElement(td2);
						
			TD td2_date=getTD();
			td2_date.setTagText(getLastModified(rowData));
			td2_date.addAttribute("align","center");
			rowHtmlData.addElement(td2_date);
			
			alt=!alt;
		}
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
	
	
	protected  TH getSortHeader(String text,String colSort) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException
	{
		TH header=getHeader(text);		
		addSortLink(header, text,  colSort);
		return header;
	}
	
	
	protected  void addSortLink(TH header,String text,String colSort) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException
	{		
		header.addAttribute("align","center");		
		String path=request.getRequestURL().toString();
		String token=request.getParameter("token");
//		if(StringUtils.isNotBlank(properties.getAppName())){
//			path="/"+url;
//		}
//		else{
			
//		}
		String paramsStr=ParamsUtils.getInstance(request).addParam("sort", colSort).addParam("direction", direction.toString()).encodeAllParams();
		A link=new A();
		link.addAttribute("href", path+"?param="+paramsStr+"&token="+token+"&format="+Format.UI_PLAIN.getParamName());
		link.addAttribute("style", thTextClass);			
		link.setTagText(text);
		header.addElement(link);
		header.setTagText(null);
	}
	
	public String generateLink(ContentRepository selected,String brandCode){		
		//http://localhost:7070/ch2-ui/tour_info/CH/?token=ch		
		switch (requestType) {
		case TOUR_INFO_NEW: return pathApp+"/"+PathType.TOUR_INFO.getPartPath()+"/"+brandCode+"/V3/"+selected.getTourCode()+".xml?token="+SecurityHelper.getToken();	
		case TOUR_INFO_OLD: return pathApp+"/"+PathType.TOUR_INFO.getPartPath()+"/"+brandCode+"/V1/"+selected.getTourCode()+".xml?token="+SecurityHelper.getToken();	
		case TOUR_DEPARTURE_OLD: return pathApp+"/"+PathType.TOUR_DEPARTURE.getPartPath()+"/"+brandCode+"/V1/"+selected.getTourCode()+".xml?token="+SecurityHelper.getToken();	
		case TOUR_DEPARTURE_NEW: return pathApp+"/"+PathType.TOUR_DEPARTURE.getPartPath()+"/"+brandCode+"/V3/"+selected.getTourCode()+".xml?token="+SecurityHelper.getToken();	
		default: return null;
		}
	}
	
	private String getHelpLink(){
		return pathApp+"/help/CH2_API_User_Guide_Brands.pdf";			
	}
}
