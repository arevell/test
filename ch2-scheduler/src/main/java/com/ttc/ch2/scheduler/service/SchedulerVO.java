package com.ttc.ch2.scheduler.service;

import java.util.Date;

import com.ttc.ch2.common.enums.SystemDirection;

public class SchedulerVO {

	enum Status{STARTED,STOPED,STAND_BY_MODE};
	
	private String schedulerName;
	private Status status;
	private Date startDate;
	private String systemDirection;
	
	private boolean selectedHabs;
	private boolean selectedTropics;
	
	public boolean isSelectedTropics() {
		return selectedTropics;
	}

	public void setSelectedTropics(boolean selectedTropics) {
		this.selectedTropics = selectedTropics;
	}

	public boolean isSelectedHabs() {
		return selectedHabs;
	}

	public void setSelectedHabs(boolean selectedHabs) {
		this.selectedHabs = selectedHabs;
	}

	public String getSystemDirection() {
		return systemDirection;
	}

	public void setSystemDirection(String systemDirection) {
		this.systemDirection = systemDirection;
		
		selectedHabs=SystemDirection.valueOf(systemDirection)==SystemDirection.HABS;
		selectedTropics=SystemDirection.valueOf(systemDirection)==SystemDirection.TROPICS;
	}

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
