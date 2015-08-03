package com.ttc.ch2.scheduler.common;

public abstract class Executor {
	
	public abstract void execute() throws ExecutorExcetpion;
	
	public void invoke() throws ExecutorExcetpion
	{
		try{
			execute();
		}
		catch(Exception e)
		{
			throw new ExecutorExcetpion(e);
		}
	}
}
