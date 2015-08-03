package com.ttc.ch2.bl.lock;

public abstract class Executor {
	
	public abstract void execute() throws ExecutorException;
	
	public void invoke() throws ExecutorException
	{
		try{
			execute();
		}
		catch(ExecutorException e){
			throw e;
		}
		catch(Exception e)
		{
			throw new ExecutorException(e);
		}
	}
}
