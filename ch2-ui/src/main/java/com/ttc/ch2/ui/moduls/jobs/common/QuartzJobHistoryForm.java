package com.ttc.ch2.ui.moduls.jobs.common;

import java.util.Date;
import java.util.List;

import org.zkoss.util.resource.Labels;

import com.google.common.collect.Lists;
import com.ttc.ch2.domain.jobs.QuartzJobHistory;
import com.ttc.ch2.domain.jobs.QuartzJobHistory.JobHistoryStatus;

public class QuartzJobHistoryForm {

	private String statusSelectedValue;
	private List<String> statusItems;
	private Date startDate;
	private String executedBy;
	private String uiName;

	private String jobGrName;
	
	public QuartzJobHistoryForm(){
		
		statusItems=Lists.newArrayList();
		statusItems.add(Labels.getLabel("common.combobox.empty_value"));
		for (int i = 0; i < QuartzJobHistory.JobHistoryStatus.values().length; i++) {
			statusItems.add(QuartzJobHistory.JobHistoryStatus.values()[i].toString());
		}
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getStatusSelectedValue() {
		return statusSelectedValue;
	}
	public JobHistoryStatus getJobHistoryStatusFromSelectedValue() {		
		try{
		return JobHistoryStatus.valueOf(statusSelectedValue);
		}catch(Exception e){}
		return null;
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
	
	public String getExecutedBy() {
		return executedBy;
	}

	public void setExecutedBy(String executedBy) {
		this.executedBy = executedBy;
	}

	

	public String getJobGrName() {
		return jobGrName;
	}

	public void setJobGrName(String jobGrName) {
		this.jobGrName = jobGrName;
	}
	
	public String getUiName() {
		return uiName;
	}

	public void setUiName(String uiName) {
		this.uiName = uiName;
	}

}
