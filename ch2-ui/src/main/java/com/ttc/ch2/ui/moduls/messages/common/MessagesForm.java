package com.ttc.ch2.ui.moduls.messages.common;

import java.util.Date;
import java.util.List;

import org.zkoss.util.resource.Labels;

import com.google.common.collect.Lists;
import com.ttc.ch2.common.enums.ProcessName;
import com.ttc.ch2.domain.messages.EmailHistory.EmailStatus;

public class MessagesForm {

	private String processNameSelectedValue;
	private List<String> processNameItems;
	private String subject;
	private Date date;
	private String statusSelectedValue;
	private List<String> statusItems;
	
	
	public MessagesForm() {	
		processNameItems=Lists.newArrayList();
		processNameItems.add(Labels.getLabel("common.combobox.empty_value"));
		for (int i = 0; i < ProcessName.values().length; i++) {
			processNameItems.add( ProcessName.values()[i].toString());
		}
		
		statusItems=Lists.newArrayList();
		statusItems.add(Labels.getLabel("common.combobox.empty_value"));
		for (int i = 0; i < EmailStatus.values().length; i++) {
			statusItems.add( EmailStatus.values()[i].toString());
		}
	}
	public ProcessName getProcessNameFromString()
	{
		try{
		return ProcessName.valueOf(processNameSelectedValue);
		}
		catch(Exception e){};
		return null;
	}
	public EmailStatus getStatusFromString()
	{
		try{
			return EmailStatus.valueOf(statusSelectedValue);
			}
			catch(Exception e){};
			return null;
	}
	
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}


	public String getProcessNameSelectedValue() {
		return processNameSelectedValue;
	}

	public void setProcessNameSelectedValue(String processNameSelectedValue) {
		this.processNameSelectedValue = processNameSelectedValue;
	}

	public List<String> getProcessNameItems() {
		return processNameItems;
	}

	public void setProcessNameItems(List<String> processNameItems) {
		this.processNameItems = processNameItems;
	}
	public String getStatusSelectedValue() {
		return statusSelectedValue;
	}
	public void setStatusSelectedValue(String statusSelectedValue) {
		this.statusSelectedValue = statusSelectedValue;
	}
	public List<String> getStatusItems() {
		return statusItems;
	}
	public void setStatusItems(List<String> statusItems) {
		this.statusItems = statusItems;
	}
	
}
