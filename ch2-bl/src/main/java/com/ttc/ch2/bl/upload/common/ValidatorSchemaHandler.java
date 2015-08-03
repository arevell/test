package com.ttc.ch2.bl.upload.common;

import java.util.List;

import javax.xml.bind.ValidationEvent;
import javax.xml.bind.ValidationEventHandler;
import javax.xml.bind.ValidationEventLocator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;


public class ValidatorSchemaHandler implements ValidationEventHandler 
{	
	private static final Logger logger=LoggerFactory.getLogger(ValidatorSchemaHandler.class);
	
	private List<String> logs=Lists.newArrayList();
	private boolean noErrors=true;

	@Override
	public boolean handleEvent(ValidationEvent event) {
		  if (event.getSeverity() != ValidationEvent.WARNING)
          {
              ValidationEventLocator vel = event.getLocator();
              String msg="Line:Col[" + vel.getLineNumber() + ":" + vel.getColumnNumber() + "]:" + event.getMessage();
              logger.error(msg);
              logs.add(msg);
              noErrors=false;
          }
          return true;
	}

	public boolean isNoErrors() {
		return noErrors;
	}

	public List<String> getLogs() {
		return logs;
	}

	public void setupDefault()
	{
		logs.clear();
		noErrors=true;
	}
}