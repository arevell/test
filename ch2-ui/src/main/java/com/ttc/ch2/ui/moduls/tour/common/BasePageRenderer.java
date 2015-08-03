package com.ttc.ch2.ui.moduls.tour.common;

import org.apache.commons.lang.StringUtils;
import org.apache.ecs.Element;
import org.apache.ecs.html.A;
import org.apache.ecs.html.Link;
import org.apache.ecs.html.Span;
import org.apache.ecs.html.TD;
import org.apache.ecs.html.TH;
import org.apache.ecs.html.TR;
import org.apache.ecs.html.Table;

import com.ttc.ch2.common.SpringContext;
import com.ttc.ch2.domain.common.SortCondition.Direction;
import com.ttc.ch2.ui.common.Ch2Properties;
import com.ttc.ch2.ui.common.config.Ch2URIs;
import com.ttc.common.params.ParamsUtils;

public class BasePageRenderer {

	
	protected  TH getHeader(String text)
	{
		TH header=new TH();
		header.setTagText(text);
		return header;
	}
	
	protected  TH getHeader(String text,String width)
	{
		TH th=getHeader(text);
		th.addAttribute("width", width);
		return th;
	}
	
	protected  Link getMainStyle(String pathApp)
	{
		Link style =new Link();
		style.addAttribute("rel", "Stylesheet");
		style.addAttribute("type", "text/css");
		style.addAttribute("href", pathApp+"/css/Ch2Main.css");
		style.setTabLevel(1);
		return style;
	}
	
	protected  Link getTableStyle(String pathApp)
	{
		Link tableStyle =new Link();
		tableStyle.addAttribute("rel", "Stylesheet");
		tableStyle.addAttribute("type", "text/css");
		tableStyle.addAttribute("href", pathApp+"/css/Ch2Table.css");
		tableStyle.setTabLevel(1);
		return tableStyle;
	}
	
	protected  TH getHeader(String text,String width,Ch2URIs uri,String colSort,boolean ascSortDirection)
	{
		TH header=getHeader(text,width);
		addSortLink(header, text, uri, colSort,ascSortDirection);
		return header;
	}
	
	protected  TH getHeader(String text,Ch2URIs uri,String colSort,boolean ascSortDirection)
	{
		TH header=getHeader(text);		
		addSortLink(header, text, uri, colSort, ascSortDirection);
		return header;
	}
	
	
	protected  void addSortLink(TH header,String text,Ch2URIs uri,String colSort,boolean ascSortDirection)
	{		
		Ch2Properties properties=SpringContext.getApplicationContext().getBean(Ch2Properties.class);
		header.addAttribute("align","center");
		A link=new A();
		
		String path="";
		if(StringUtils.isNotBlank(properties.getAppName())){
			path="/"+uri.getPathWithAppName(properties.getAppName());
		}
		else{
			path=uri.getPathWithAppName(properties.getAppName());
		}
		String paramsStr=ParamsUtils.getInstance().addParam("sort", colSort).addParam("direction", ascSortDirection ? Direction.ASC.toString() : Direction.DESC.toString()).encodeAllParams();
		link.addAttribute("href", path+"?param="+paramsStr);
		link.addAttribute("style", "color:white");
		link.setTagText(text);
		header.addElement(link);
		header.setTagText(null);
	}
	
	
	protected  void addSortEmptyLink(TH header,String text,String thTextClass)
	{		
		header.addAttribute("align","center");				
		A link=new A();
		link.addAttribute("class", thTextClass);			
		link.setTagText(text);
		header.addElement(link);
		header.setTagText(null);
	}
	
	
	protected TD getTD()
	{
		TD td0=new TD();
//		td0.addAttribute("style", "border: 1px solid #520A87;padding: 3px;vertical-align: top;");
		return td0;
	}
	

	protected void setupDefaultParams(Element e,int tabLevel){
		e.setPrettyPrint(true);
		e.setTabLevel(tabLevel);
	}
	
	protected  Span createLink(String linkText,String url)
	{
		Span s=new Span();
		s.addAttribute("style", "margin-right:5px");
		A link=new A();
		link.addAttribute("href", url);
		link.setTagText(linkText);
		s.addElement(link);
		return s;
	}
	
	
	protected Table wrapVerticalToTable(int level,Element ...elements){
		
		Table tab=new Table();
		tab.addAttribute("width", "100%");
		setupDefaultParams(tab, level);				
		for (int i = 0; i < elements.length; i++) {
			TR row =new TR();
			setupDefaultParams(tab, level+1);
			tab.addElement(row);
			TD td=new TD();
			setupDefaultParams(tab, level+2);
			row.addElement(td);
			td.addElement(elements[i]);
		}		
		return tab;
	
	}
	protected Table wrapHorizontalToTable(int level,String [] aligns,Element ...elements){
		
		Table tab=new Table();
		tab.addAttribute("width", "100%");
		setupDefaultParams(tab, level);
		TR row =new TR();
		setupDefaultParams(tab, level+1);
		tab.addElement(row);
		
		for (int i = 0; i < elements.length; i++) {			
			TD td=new TD();
			td.setAlign(aligns[i]);
			setupDefaultParams(tab, level+2);
			row.addElement(td);
			td.addElement(elements[i]);
		}		
		return tab;
	}
}
