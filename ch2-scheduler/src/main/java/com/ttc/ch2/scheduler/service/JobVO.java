package com.ttc.ch2.scheduler.service;

import java.util.Date;

public class JobVO {
	
	private String jobName;
	private String jobGroupName;
	private Date nextFireTime;
	private String statusJob;
	private String whoScheduled;
	private String brandCode;
	private boolean pbDisabled;
	private boolean needReset;
	private String pbOperationTitle;
	private String listButtons;

	public JobVO(String jobName, String jobGroupName, Date nextFireTime,String statusJob, String whoScheduled,String brandCode,boolean pbEnabled,boolean needReset) {
		super();
		this.jobName = jobName;
		this.jobGroupName = jobGroupName;
		this.nextFireTime = nextFireTime;
		this.statusJob = statusJob;
		this.whoScheduled=whoScheduled;
		this.brandCode=brandCode;
		this.pbDisabled=!pbEnabled;		
		this.needReset=needReset;		
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getJobGroupName() {
		return jobGroupName;
	}

	public void setJobGroupName(String jobGroupName) {
		this.jobGroupName = jobGroupName;
	}

	public Date getNextFireTime() {
		return nextFireTime;
	}

	public void setNextFireTime(Date nextFireTime) {
		this.nextFireTime = nextFireTime;
	}

	public String getStatusJob() {
		return statusJob;
	}

	public void setStatusJob(String statusJob) {
		this.statusJob = statusJob;
	}

	public String getWhoScheduled() {
		return whoScheduled;
	}

	public void setWhoScheduled(String whoScheduled) {
		this.whoScheduled = whoScheduled;
	}

	public String getBrandCode() {
		return brandCode;
	}

	public void setBrandCode(String brandCode) {
		this.brandCode = brandCode;
	}
	


	public String getPbOperationTitle() {
		return pbOperationTitle;
	}

	public void setPbOperationTitle(String pbOperationTitle) {
		this.pbOperationTitle = pbOperationTitle;
	}

	public boolean isPbDisabled() {
		return pbDisabled;
	}

	public void setPbDisabled(boolean pbDisabled) {
		this.pbDisabled = pbDisabled;
	}

	public boolean isNeedReset() {
		return needReset;
	}

	public void setNeedReset(boolean needReset) {
		this.needReset = needReset;
	}

	public String getListButtons() {
		return listButtons;
	}

	public void setListButtons(String listButtons) {
		this.listButtons = listButtons;
	}

	
}
