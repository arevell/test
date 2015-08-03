package com.ttc.ch2.cucumber;



import org.junit.Assert;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringHelper {
	
	public static ClassPathXmlApplicationContext applicationContext;
	public static String endPoint=null;
	public static String tropicEndpoint=null; 
		
	static{	
		   try{
			   applicationContext = new ClassPathXmlApplicationContext("classpath:/META-INF/spring/testCtx.xml","classpath:/META-INF/spring/mongoDbDaoCtx.xml","classpath:/META-INF/spring/daoCtx.xml","classpath:/META-INF/spring/blCtx.xml");
			   endPoint = applicationContext.getBeanFactory().resolveEmbeddedValue("${endpoint.ccapi_ws}");
			   tropicEndpoint = applicationContext.getBeanFactory().resolveEmbeddedValue("${endpoint.tropics_build_ws}");
			    }catch(Throwable e){
			        e.printStackTrace();
			        Assert.assertNotNull("ApplicationContext not initialized",applicationContext);
		   }
	}

}
