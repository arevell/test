package com.ttc.ch2.scheduler.service;

import java.util.Date;

public class SchedulerVO {

	enum Status{STARTED,STOPED,STAND_BY_MODE};
	
	private String schedulerName;
	private Status status;
	private Date startDate;

	public String getSchedulerName() {
		return schedulerName;
	}

	public void setSchedulerName(String schedulerName) {
		this.schedulerName = schedulerName;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}	
	
	public boolean isStandByMode()
	{
		return status==Status.STAND_BY_MODE;
	}
	public boolean isNotStandByMode()
	{
		return !isStandByMode();
	}
}
