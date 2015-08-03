package com.ttc.ch2.bl.departure.common;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.ttc.ch2.bl.departure.common.reports.TdeTextReport;
import com.ttc.ch2.bl.departure.common.reports.TdiTextReport;

public class MessagesManager implements Serializable 
{
	private static final long serialVersionUID = 5699823897363355106L;
	
	private List<TDMessage> mainMessages=Lists.newArrayList();		
	private Map<String,BrandMessages> brandMessages=Maps.newHashMap();		
	private List<TDMessage> errorMessages=Lists.newArrayList();	
	
	private TdiTextReport tdiTextReport;
	private TdeTextReport tdeTextReport;

	
	public MessagesManager(OperationStatus opStatus) {
		super();	
		this.tdiTextReport=new TdiTextReport(opStatus, this);
		this.tdeTextReport=new TdeTextReport(opStatus, this);
	}
	
	public void addMessage(TDMessage msg)
	{
		if(StringUtils.isEmpty(msg.getBrandCode()))
		{
			mainMessages.add(msg);
		}
		else				
		{
			if (!brandMessages.containsKey(msg.getBrandCode())) {
				BrandMessages brandMsg=new BrandMessages();
				brandMessages.put(msg.getBrandCode(), brandMsg);
			}				
			BrandMessages brandMsg=brandMessages.get(msg.getBrandCode());				
			brandMsg.addMessage(msg);
		}
		
		if(msg.getE()!=null)
			errorMessages.add(msg);		
	}
	
	public void clear(){
		mainMessages.clear();
		brandMessages.clear();
		errorMessages.clear();
	}

	public void createTdiReport()
	{			
		tdiTextReport.create();
	}
	
	public void createTdeReport()
	{			
		tdeTextReport.create();
	}

	public List<TDMessage> getMainMessages() {
		return mainMessages;
	}

	public Map<String, BrandMessages> getBrandMessages() {
		return brandMessages;
	}

	public List<TDMessage> getErrorMessages() {
		return errorMessages;
	}
}