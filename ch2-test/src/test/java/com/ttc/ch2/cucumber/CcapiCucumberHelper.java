package com.ttc.ch2.cucumber;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ttc.ch2.domain.auth.CCAPIAuthority;
import com.ttc.ch2.domain.user.UserCCAPI;

public class CcapiCucumberHelper {
	
	public static ClassPathXmlApplicationContext applicationContext;
	public static String endPoint=null;
	public static String tropicEndpoint=null; 
	
	
	static
	{	
		   try{
			   applicationContext = new ClassPathXmlApplicationContext("classpath:/META-INF/spring/testOnlyCtx.xml","classpath:/META-INF/spring/dbCtx.xml");
			   endPoint = applicationContext.getBeanFactory().resolveEmbeddedValue("${endpoint.ccapi_ws}");
			   tropicEndpoint = applicationContext.getBeanFactory().resolveEmbeddedValue("${endpoint.tropics_build_ws}");
			    }catch(Throwable e)
			    {
			        e.printStackTrace();
			    }
	}
		
	public static void after(ApplicationContext applicationContext)  {	
		
		SessionFactory sf=applicationContext.getBean(SessionFactory.class);
		Session s=null;
		Transaction t=null;
		try
		{
			s=sf.openSession();
			t=s.beginTransaction();		
			
			{
				List<UserCCAPI> lists= s.createQuery("from UserCCAPI where username='cucumber-user1'").list();
				for (UserCCAPI u : lists) {		
					
					for (CCAPIAuthority auth : u.getCcapiAuthorities()) {
						s.delete(auth);
					}				
					s.delete(u);
				};
			}
			{
				List<UserCCAPI> lists= s.createQuery("from UserCCAPI where username='cucumber-user2'").list();
				for (UserCCAPI u : lists) {		
					
					for (CCAPIAuthority auth : u.getCcapiAuthorities()) {
						s.delete(auth);
					}				
					s.delete(u);
				};
			}
		}
		finally
		{
			if(t!=null)
				t.commit();
			if(s!=null){
				s.flush();
				s.close();
			}
		}		
	}
}
