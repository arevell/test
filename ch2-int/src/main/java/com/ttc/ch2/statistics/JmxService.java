package com.ttc.ch2.statistics;

import java.util.List;

import javax.annotation.PreDestroy;
import javax.inject.Inject;
import javax.management.InstanceAlreadyExistsException;
import javax.management.InstanceNotFoundException;
import javax.management.IntrospectionException;
import javax.management.MBeanInfo;
import javax.management.MBeanRegistrationException;
import javax.management.MBeanServer;
import javax.management.MBeanServerFactory;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import javax.management.ObjectName;
import javax.management.ReflectionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ttc.ch2.dao.BrandDAO;
import com.ttc.ch2.domain.Brand;

@Service
public class JmxService {

private static final Logger logger = LoggerFactory.getLogger(JmxService.class);
	
	private static final String jmxPrefix = "ch2.audit:name=statsContext.";

	@Inject
	private StatisticsBean statisticsBean;

	@Inject
	private BrandDAO brandDAO;
	
	public void jmxRegister(){
		
		List<Brand> brandList= brandDAO.findAll();
		
		for (Brand brand : brandList) {
			jmxBrandInfoRegister(brand.getCode());
		}
	}
	
	@PreDestroy
	public void jmxUnRegister() {
		try {
			List<Brand> brandList= brandDAO.findAll();
			List<MBeanServer> server=MBeanServerFactory.findMBeanServer(null);	
			MBeanServer localMbs=server.get(0);
			
			for (Brand brand : brandList) {
				String name=jmxPrefix+brand.getCode();
				ObjectName mbeanName = new ObjectName(name);
				localMbs.unregisterMBean(mbeanName);
			}
		}catch(Exception e) {
			logger.error("",e);
		}
	}
	
	private void jmxBrandInfoRegister(String brandCode){
		
		String name=jmxPrefix+brandCode;
		try{
			List<MBeanServer> server=MBeanServerFactory.findMBeanServer(null);	
			MBeanServer mbs=server.get(0);
			MBeanInfo i=mbs.getMBeanInfo(new ObjectName(name));					
		}catch(InstanceNotFoundException e){					
			List<MBeanServer> server=MBeanServerFactory.findMBeanServer(null);	
			MBeanServer localMbs=server.get(0);
			ObjectName mbeanName;												
			try {
				mbeanName = new ObjectName(name);											
				BrandJmxMBean mJmx=new BrandJmx(brandCode, statisticsBean);
				localMbs.registerMBean(mJmx, mbeanName);
			} catch (MalformedObjectNameException | InstanceAlreadyExistsException | MBeanRegistrationException | NotCompliantMBeanException e1) {
				logger.error("",e1);
			}						
		} catch (IntrospectionException e) {
			logger.error("",e);
		} catch (MalformedObjectNameException e) {
			logger.error("",e);
		} catch (ReflectionException e) {
			logger.error("",e);
		}	    
		
	}
}
