package com.ttc.ch2.scheduler.service;

import java.util.Date;

import org.quartz.JobDetail;
import org.quartz.SchedulerException;
import org.quartz.SchedulerListener;
import org.quartz.Trigger;

public class StatisticListenerQuartz implements SchedulerListener {

	private Date currentDateStart=null; // time than quartz start currenly running
	private Date dateStart=null; // time than quartz start last time
	private Date dateEnd=null;
	
	
	
	
	@Override
	public void jobScheduled(Trigger trigger) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void jobUnscheduled(String triggerName, String triggerGroup) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void triggerFinalized(Trigger trigger) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void triggersPaused(String triggerName, String triggerGroup) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void triggersResumed(String triggerName, String triggerGroup) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void jobAdded(JobDetail jobDetail) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void jobDeleted(String jobName, String groupName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void jobsPaused(String jobName, String jobGroup) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void jobsResumed(String jobName, String jobGroup) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void schedulerError(String msg, SchedulerException cause) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void schedulerInStandbyMode() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void schedulerStarted() {
		dateStart=new Date();
		currentDateStart=dateStart;
		
	}

	@Override
	public void schedulerShutdown() {
		dateEnd=new Date();
		currentDateStart=null;
	}

	@Override
	public void schedulerShuttingdown() {
		// TODO Auto-generated method stub
		
	}

	public Date getDateStart() {
		return dateStart;
	}

	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
	}

	public Date getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}

	public Date getCurrentDateStart() {
		return currentDateStart;
	}

	public void setCurrentDateStart(Date currentDateStart) {
		this.currentDateStart = currentDateStart;
	}

}
