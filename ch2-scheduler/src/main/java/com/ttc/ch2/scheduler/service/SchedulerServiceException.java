package com.ttc.ch2.scheduler.service;

public class SchedulerServiceException extends Exception {

	
	public SchedulerServiceException()
	{
		super();
	}
	
	public SchedulerServiceException(String msg)
	{
		super(msg);		
	}
	
	public SchedulerServiceException(Throwable throwable)
	{
		super(throwable);
	}
	
	public SchedulerServiceException(String msg,Throwable throwable)
	{
		super(msg,throwable);
	}
}
