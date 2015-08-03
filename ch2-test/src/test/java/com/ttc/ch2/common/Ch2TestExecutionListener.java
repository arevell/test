package com.ttc.ch2.common;

import org.springframework.test.context.TestContext;
import org.springframework.test.context.support.AbstractTestExecutionListener;

public abstract class Ch2TestExecutionListener extends AbstractTestExecutionListener{
	
	  private boolean beforExecute;
	  private boolean afterExecute;
	  public void beforeTestMethod(TestContext testContext){
		  if(beforExecute==false){
			  beforExecute=true;
			  beforeClass(testContext);
		  }		  
	  }
	  
	  public void afterTestMethod(TestContext testContext){
		  if(afterExecute==false){
			  afterExecute=true;
			  afterClass(testContext);
		  } 
	  }

	  public  void beforeClass(TestContext testContext){}
	  
	  public  void afterClass(TestContext testContext){}		
}


