package com.ttc.ch2.ui.common.config;

import java.util.Date;

import org.zkoss.util.resource.Labels;

import com.ttc.ch2.common.DateHelper;

public class CurrentDate implements ConfigValue{
	@Override
	public Object getValue() {		
		return DateHelper.dateToString(new Date(),Labels.getLabel("format.datatime"));
	}
}
