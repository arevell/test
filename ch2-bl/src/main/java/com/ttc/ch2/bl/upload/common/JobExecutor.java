package com.ttc.ch2.bl.upload.common;


public interface JobExecutor {

	public abstract void init();
	
	public abstract void release();
	
	public abstract void execute();
}
