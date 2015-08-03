package com.ttc.ch2.ui.common;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.UnhandledException;
import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.zkoss.util.resource.Locator;
import org.zkoss.web.util.resource.ServletContextLocator;

import com.google.common.base.Throwables;

public class Log4jInitializer implements ServletContextListener {
	
	private static final String DEFAULT_PROPERY_PATH="WEB-INF/spring/ch2main.properties";
	
	private void initializeLog4j(ServletContext sc){
				
		String log4jpath=getLog4jPath(sc);		
		if(StringUtils.hasText(log4jpath) ){			
			try{				
				PropertyConfigurator.configure(log4jpath);			
				Logger logger = LoggerFactory.getLogger(Log4jInitializer.class);
				
				if(new File(log4jpath).exists()){			
					logger.info("Initialized log4j from:"+log4jpath);
				}
				else{
					logger.info("Initialized log4j from default localization - initialization from "+log4jpath+" failed");
				}
			}
			catch (Exception e){
				Logger logger = LoggerFactory.getLogger(Log4jInitializer.class);
				logger.info("Initialized log4j from default localization - initialization from "+log4jpath+" failed");
			}
		}
		else{
			Logger logger = LoggerFactory.getLogger(Log4jInitializer.class);
			logger.info("Initialized log4j from default localization");
		}		
	}
	
	private String getLog4jPath(ServletContext sc){				
		Locator locator=new ServletContextLocator(sc);
		InputStream inputStream=null;
		try{
			if(StringUtils.hasText(System.getProperty("ch2.config.properties"))){
				String localPathProperties=	"file:///"+System.getProperty("ch2.config.properties");			
				inputStream= locator.getResourceAsStream(localPathProperties);
			}
			if(inputStream==null){
				inputStream= locator.getResourceAsStream(DEFAULT_PROPERY_PATH);
			}
		}
		catch (Exception e) {
			inputStream= locator.getResourceAsStream(DEFAULT_PROPERY_PATH);	
		}
					
		Properties prop=new Properties();
		try {
			prop.load(inputStream);			
			return prop.getProperty("common.log4j.path");
		} catch (IOException e) {
			sc.log(Throwables.getStackTraceAsString(e));
		}
		finally{
			IOUtils.closeQuietly(inputStream);
		}
		return null;		
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		initializeLog4j(sce.getServletContext());		
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {		
	}

}
