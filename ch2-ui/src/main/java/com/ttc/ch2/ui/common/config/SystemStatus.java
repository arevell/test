package com.ttc.ch2.ui.common.config;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.UnhandledException;
import org.zkoss.util.resource.Locator;
import org.zkoss.web.util.resource.ServletContextLocator;
import org.zkoss.xel.XelException;
import org.zkoss.zk.ui.Sessions;

import com.google.common.collect.Maps;
import com.ttc.ch2.common.DateHelper;
import com.ttc.ch2.common.SpringContext;
import com.ttc.ch2.ui.common.Ch2Properties;
import com.ttc.ch2.ui.common.SessionHelper;


public class SystemStatus implements org.zkoss.xel.VariableResolver{
	
	private static Map<String,ConfigValue> values=Maps.newHashMap();
	
	static 
	{				
		values.put("session_exist", new ConfigValue() {			
			public Object getValue() {
				return Sessions.getCurrent()!=null;
			}
		});
		
		values.put("session_count", new ConfigValue() {			
			public Object getValue() {
				return Sessions.getCount();
			}
		});
				
		Ch2Properties prop=SpringContext.getApplicationContext().getBean(Ch2Properties.class);
		
		String appNode=null;
		if (StringUtils.isBlank(appNode=prop.getAppNode())) {			
			try {
	            appNode=InetAddress.getLocalHost().getHostName();
	        } catch (Throwable ignored) {
	        }
        }
		
		values.put("sys_version", new SimpleValue(prop.getVersion()));
		values.put("sys_enviroment", new SimpleValue(prop.getEnviroment()));
		values.put("sys_date", new CurrentDate());		
		values.put("sys_deploy_date", new SimpleValue(prop.getDeployTime()));		
		values.put("sys_node", new SimpleValue(appNode));	
	}
	
	
	
	@Override
	public Object resolveVariable(String name) throws XelException {
		ConfigValue cfgValue=values.get(name);	
		return cfgValue != null ? cfgValue.getValue() : null;	
	}

}
