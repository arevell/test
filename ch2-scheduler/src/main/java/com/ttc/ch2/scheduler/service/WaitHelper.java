package com.ttc.ch2.scheduler.service;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.inject.Inject;
import javax.inject.Named;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

@Component
public class  WaitHelper
{	
	private static final Logger logger = LoggerFactory.getLogger(WaitHelper.class);
	@Inject
	@Qualifier("schedulerFactoryBean")
	private SchedulerFactoryBean schedulerFactory;

	public static long sleepTime=1000*10;
	private static int loopMax=5;
	private Boolean semafor=Boolean.FALSE;
	private Lock lock = new ReentrantLock();
	
	public void waitOnStartScheduler() {
		int count=0;
		do{
			try {
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {
				
			}
			count++;
			if(schedulerFactory.isRunning())
				break;
				
		}while((count<loopMax));
	}
	
	public void waitOnStopScheduler() {
		int count=0;
		do{
			try {
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {
			}
			count++;
			if(schedulerFactory.isRunning()==false)
				break;
		}while(count<loopMax);
	}
	
	public void waitOnRefresh() {
		int count=0;
		do{
			try {
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {				
			}
			count++;
//			if(schedulerFactory.isRunning()==false)
//				break;
		}while(count<loopMax);
	}


	public boolean semaforLock() {		
		lock.lock();
		boolean result=false;
		if(semafor==false){
				semafor=true;
				result=true;
			}		
		lock.unlock();		
		return result;		
	}
	
	public void releaseSemafor() {
		lock.lock();
		semafor=false;
		lock.unlock();		
	}		
}