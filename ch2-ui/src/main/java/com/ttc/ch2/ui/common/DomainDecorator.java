package com.ttc.ch2.ui.common;

import java.util.List;

import org.zkoss.util.resource.Labels;

import com.google.common.base.Function;
import com.google.common.collect.Lists;

public abstract class DomainDecorator<T> {
	
	protected String emptyValue=null;
	protected List<String> values=null;
	protected String selectedValue=null;
		
	public DomainDecorator()
	{
		this(true);
	}
	
	public DomainDecorator(boolean useEmptyElement)
	{
		if(useEmptyElement)
		{
		this.emptyValue=Labels.getLabel("common.combobox.empty_value");		
		this.values=Lists.newArrayList(emptyValue);
		}
		else
		{
			this.values=Lists.newArrayList();
		}
	}
			
	public abstract T getValueByString(String v);

	public List<String> getValues() {
		return values;
	}
	
	public T getValueByString()
	{
		return getValueByString(selectedValue);
	}
	

	public String getSelectedValue() {
		return selectedValue;
	}

	public void setSelectedValue(String selectedValue) {
		this.selectedValue = selectedValue;
	}

	



}
