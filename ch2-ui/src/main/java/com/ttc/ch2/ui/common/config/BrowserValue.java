package com.ttc.ch2.ui.common.config;

import org.zkoss.zk.ui.Executions;

import com.ttc.ch2.ui.common.Browsers;

class BrowserValue implements ConfigValue{
	private Object [] values; // IE,CHROME,FIREFOX,OTHER
	BrowserValue(Object ... values)
	{
		this.values=values;
	}
	@Override
	public Object getValue() {
		if(Browsers.IE==Browsers.getBrowserByName(Executions.getCurrent().getBrowser()))				
			return values[0];
		else if(Browsers.CHROME==Browsers.getBrowserByName(Executions.getCurrent().getBrowser())) 
			return values[1];
		else if(Browsers.FIREFOX==Browsers.getBrowserByName(Executions.getCurrent().getBrowser())) 
			return values[2];
		else
			return values[3];
	}
}


