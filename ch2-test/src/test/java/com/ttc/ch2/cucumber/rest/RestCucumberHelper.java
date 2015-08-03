package com.ttc.ch2.cucumber.rest;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class RestCucumberHelper {
	
	public static ClassPathXmlApplicationContext applicationContext;
	static
	{	
		   try{
			   applicationContext = new ClassPathXmlApplicationContext("classpath:/META-INF/spring/testOnlyCtx.xml","classpath:/META-INF/spring/dbCtx.xml");
			    }catch(Throwable e)
			    {
			        e.printStackTrace();
			    }
	}
}
