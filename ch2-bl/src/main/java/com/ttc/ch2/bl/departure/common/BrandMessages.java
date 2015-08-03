package com.ttc.ch2.bl.departure.common;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.ttc.ch2.bl.departure.common.reports.MessageFinderByCodeType;

public class BrandMessages implements Serializable
{
	private static final long serialVersionUID = 8523446183539433120L;
	private List<TDMessage> massages=Lists.newArrayList();
	private Multimap<String,TDMessage> comapnyMessages=ArrayListMultimap.create();
	private Multimap<String, TDMessage> productMessages=ArrayListMultimap.create();

	public void addMessage(TDMessage msg)
	{
		if(StringUtils.isEmpty(msg.getSellingCompanyCode()) && StringUtils.isEmpty(msg.getProductCode()))
		{
			massages.add(msg);
		}
		if(StringUtils.isNotEmpty(msg.getSellingCompanyCode()))
		{			
			comapnyMessages.put(msg.getSellingCompanyCode(), msg);		
		}
		
		if(StringUtils.isNotEmpty(msg.getProductCode()))
		{
			productMessages.put(msg.getProductCode(),msg);
		}	
	}

	public List<TDMessage> getMassages() {
		return massages;
	}

	public Multimap<String, TDMessage> getComapnyMessages() {
		return comapnyMessages;
	}

	public Multimap<String, TDMessage> getProductMessages() {
		return productMessages;
	}

	public boolean isFailSynchronize()
	{
		boolean result=false;		
		result= Iterables.filter(massages, new MessageFinderByCodeType("E")).iterator().hasNext() ;
		if(result)
			return true;
		
		for (String key : comapnyMessages.keySet()) {		
			result=Iterables.filter(comapnyMessages.get(key), new MessageFinderByCodeType("E")).iterator().hasNext();
			if(result)
				return true;
		}
		
		for (String key : productMessages.keySet()) {		
			result=Iterables.filter(productMessages.get(key), new MessageFinderByCodeType("E")).iterator().hasNext();
			if(result)
				return true;
		}
		return result; 
	}

}
