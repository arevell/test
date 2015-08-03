package com.ttc.ch2.ui.common.config;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.zkoss.util.resource.Labels;
import org.zkoss.xel.XelException;

import com.google.common.collect.Maps;
import com.ttc.ch2.common.SecurityHelper;
import com.ttc.ch2.common.SpringContext;
import com.ttc.ch2.domain.auth.Role;
import com.ttc.ch2.ui.common.Ch2Properties;


public class ViewConfig implements org.zkoss.xel.VariableResolver{
	public static final String APP_NAME="app_name";
	public static final String APP_PATH="app_path";
	public static final String PANEL_WIDTH_VAL="global_panel_width";
	public static final String PANEL_HEIGHT_VAL="global_panel_height";
	public static final String WINDOW_HEIGHT_WITHOUT_MENU_VAL="global_window_without_menu_height";
	public static final String WINDOW_HEIGHT_WITH_MENU_VAL="global_window_with_menu_height";
	public static final String DEFAULT_DATE_TIME_FORMAT="default_date_time_format";
	public static final String DEFAULT_LABEL_WIDTH="default_label_width";
	public static final String DEFAULT_FIELD_WIDTH="default_field_width";

	
	private static Map<String,ConfigValue> config=Maps.newHashMap();	
	static 
	{
		Ch2Properties properties=SpringContext.getApplicationContext().getBean(Ch2Properties.class);
		String pathApp=StringUtils.isNotBlank(properties.getAppName()) ? "/"+properties.getAppName(): "" ;
		config.put(APP_NAME,new SimpleValue(properties.getAppName()));
		config.put(APP_PATH,new SimpleValue(pathApp));
				
		config.put(WINDOW_HEIGHT_WITHOUT_MENU_VAL, new BrowserValue("622px","695px","583px","583px"));
		config.put(WINDOW_HEIGHT_WITH_MENU_VAL, new BrowserValue("595px","666px","555px","555px"));
		config.put(PANEL_WIDTH_VAL, new SimpleValue("100%"));		
		config.put(PANEL_HEIGHT_VAL,new SimpleValue("90vh"));
		config.put(DEFAULT_DATE_TIME_FORMAT,new SimpleValue(Labels.getLabel("format.datatime")));	
		config.put(DEFAULT_LABEL_WIDTH,new SimpleValue(170));	
		config.put(DEFAULT_FIELD_WIDTH,new SimpleValue("200px"));	
//		config.put(PANEL_HEIGHT_VAL,  new BrowserValue("565px","626px","520px","575px"));
							
//		URIs
		for (int i = 0; i < Ch2URIs.values().length; i++) {
			Ch2URIs uri=Ch2URIs.values()[i];					
			config.put(uri.toString(), new SimpleValue(uri.getPath()));
		}
		for (int i = 0; i < JspCh2URIs.values().length; i++) {
			JspCh2URIs uri=JspCh2URIs.values()[i];					
			config.put(uri.toString(), new SimpleValue(uri.getPath()));
		} 
	}
	
	
	@Override
	public Object resolveVariable(String name) throws XelException 
	{		
		if("MAIN_PAGE".equals(name) && config.containsKey(name)==false){
			if(SecurityHelper.isAuthenticatedSilent()){
				if(SecurityHelper.hasAuthority(Role.ADMINISTRATOR))	        	 
					   config.put("MAIN_PAGE", new SimpleValue(Ch2URIs.AUDIT.getPath()));
			       else if(SecurityHelper.hasAuthority(Role.BRAND))
			           config.put("MAIN_PAGE", new SimpleValue(Ch2URIs.UPDATE_TOUR_INFO.getPath()));
			}
		}
		

		ConfigValue cfgValue=config.get(name);	
		return cfgValue != null ? cfgValue.getValue() : null;			
	}
	
	
	
}
