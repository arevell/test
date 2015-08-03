package com.ttc.ch2.common;

import java.io.StringReader;
import java.net.URL;
import java.util.Date;
import java.util.Set;

import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.apache.commons.lang.time.DateUtils;
import org.apache.ecs.xml.XML;
import org.xml.sax.SAXException;

import com.google.common.collect.Sets;
import com.ttc.ch2.api.ccapi.v3.tourinfo._2014._01._3.TourInfo;
import com.ttc.ch2.common.enums.ProcessName;
import com.ttc.ch2.domain.Brand;
import com.ttc.ch2.domain.SellingCompany;
import com.ttc.ch2.domain.auth.Group;
import com.ttc.ch2.domain.comment.CREComment;
import com.ttc.ch2.domain.comment.QHComment;
import com.ttc.ch2.domain.comment.TDComment;
import com.ttc.ch2.domain.comment.TIComment;
import com.ttc.ch2.domain.departure.TourDepartureHistory;
import com.ttc.ch2.domain.departure.TourDepartureHistory.TourDepartureStatus;
import com.ttc.ch2.domain.jobs.QuartzJobHistory;
import com.ttc.ch2.domain.jobs.QuartzJobHistory.JobHistoryStatus;
import com.ttc.ch2.domain.messages.EmailHistory;
import com.ttc.ch2.domain.messages.EmailHistory.EmailStatus;
import com.ttc.ch2.domain.user.UserCCAPI;
import com.ttc.ch2.domain.user.UserGui;

public class SampleGenerator {

	public static  Brand generateBrand()
	{
		Brand brand=new Brand();	
		brand.setSellingCompanies(Sets.newHashSet(generateSellingCompany(brand)));		
		brand.setBrandName("brand name:1");
		brand.setCode("BV");
		return brand;
	}
	
	public static UserCCAPI generateUser(String userName)
	{
		UserCCAPI ccapiUser=new UserCCAPI();
		ccapiUser.setAddress("n/a");
		ccapiUser.setToken("token:1");
		ccapiUser.setEmail("n/a");
		ccapiUser.setUsername(userName);
		ccapiUser.setEnabled(true);
		return ccapiUser;
	}
	
	public static UserGui generateUserUi(String userName)
	{
		Set<Group> group=Sets.newHashSet();
		
		UserGui user=new UserGui();
		user.setEmail("n/a");
		user.setUsername(userName);
		user.setEnabled(true);
		user.setPassword("xxxxx");
		user.setGroups(group);
		return user;
	}
	public static SellingCompany generateSellingCompany(Brand brand)
	{
		SellingCompany s=new SellingCompany();
		s.setCode("CHUKLS");
		s.setName("xxxxxx");
		s.setBrand(brand);
		return s;
	}
	
	public static EmailHistory generateEmailHistory(int index)
	{
		EmailHistory emailHistory=new EmailHistory();
		emailHistory.setProccessName(ProcessName.UPLOAD);
		emailHistory.setFrom("xxxFrom-"+index+"@op.pl");
		emailHistory.setSubject("subject:"+index);
		emailHistory.setMessage("content messages:"+index);
		emailHistory.setStatus(EmailStatus.Success);
		emailHistory.setMessageDeliveryTime(new Date());
		emailHistory.setTo("xxxTo-"+index+"@op.pl");	
		return emailHistory;
	}
	
	public static TIComment generateSampleTIComment(int index)
	{
		TIComment comment=new TIComment();
		comment.setMessage("message:"+index);
		comment.setContent("content:"+index);
		comment.setMessageCode("code:"+index);
		comment.setModifiedBy("system");
		comment.setModifiedTime(new Date());
		comment.setStackTrace("stack trace:"+index);
		return comment;
	}
	
	public static TDComment generateSampleTDComment(int index)
	{
		TDComment comment=new TDComment();
		comment.setMessage("message:"+index);
		comment.setContent("content:"+index);
		comment.setMessageCode("code:"+index);
		comment.setModifiedBy("system");
		comment.setModifiedTime(new Date());
		comment.setStackTrace("stack trace:"+index);
		return comment;
	}
	
	public static CREComment generateSampleCREComment(int index)
	{
		CREComment comment=new CREComment();
		comment.setMessage("message:"+index);
		comment.setContent("content:"+index);
		comment.setMessageCode("code:"+index);
		comment.setModifiedBy("system");
		comment.setModifiedTime(new Date());
		comment.setStackTrace("stack trace:"+index);
		return comment;
	}
	public static QHComment generateSampleQHComment(int index)
	{
		QHComment comment=new QHComment();
		comment.setMessage("message:"+index);
		comment.setContent("content:"+index);
		comment.setMessageCode("code:"+index);
		comment.setModifiedBy("system");
		comment.setModifiedTime(new Date());
		comment.setStackTrace("stack trace:"+index);
		return comment;
	}
	
	public static QuartzJobHistory generateSampleQuartzJobHistory(int index)
	{
		QuartzJobHistory quartz=new QuartzJobHistory();
		quartz.setExecutedBy("test");
		quartz.setExecutionTime(1000l);
		quartz.setStartDate(new Date());
		quartz.setStatus(JobHistoryStatus.Success);
		quartz.setBrand(generateBrand());
		
		TourDepartureHistory td=generateSampleTourDepartureHistory(index);
		td.setQuartzJobHistory(quartz);
		quartz.setTourDepartureHistory(td);
		
		QHComment comment=generateSampleQHComment(index);
		comment.setQuartzJobHistory(quartz);		
		quartz.getComments().add(comment);
		return quartz;
	}
	
	public static TourDepartureHistory generateSampleTourDepartureHistory(int index)
	{
		TourDepartureHistory tdh=new TourDepartureHistory();
		tdh.setImportedCount(0+index);
		tdh.setStatus(TourDepartureStatus.SUCCESS_OPERATION_END);
		tdh.setUpdatedCount(0+index);
		tdh.setOperationStart(DateUtils.addDays(new Date(), -2));
		tdh.setOperationEnd(DateUtils.addDays(new Date(), -1));
		tdh.setModifiedBy("test");
		
		TDComment c=generateSampleTDComment(index);
		c.setTourDepartureHistory(tdh);
		tdh.getComments().add(c);		
		return tdh;
	}
	
	
	public static String generateSampleXml()
	{
		XML rootElement = new XML("RootElement");
		 rootElement.addElement(new XML("element").addAttribute("name", "element:1"));
		 rootElement.addElement(new XML("element").addAttribute("name", "element:2"));
		 rootElement.addElement(new XML("element").addAttribute("name", "element:3"));
		 return rootElement.toString();
	}
	
	
	public static Schema generateSchemaTourInfo() throws SAXException
	{
		URL xsdUrlA = TourInfo.class.getResource("TourInfo.3.0.xsd");
		URL xsdUrlB = TourInfo.class.getResource("location_lists.2.xsd");
		SchemaFactory schemaFactory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
		
		String W3C_XSD_TOP_ELEMENT =
		"<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n"
		   + "<xs:schema xmlns:xs=\"http://www.w3.org/2001/XMLSchema\" targetNamespace=\"http://www.ttc.com/ch2/api/ccapi/v3/TourInfo/2014/01/3.0\" elementFormDefault=\"qualified\">\n"
		   + "<xs:include schemaLocation=\"" +xsdUrlA.getPath() +"\"/>\n"
		   + "<xs:include schemaLocation=\"" +xsdUrlB.getPath() +"\"/>\n"
		   +"</xs:schema>";
		//System.out.println(W3C_XSD_TOP_ELEMENT);
		Schema schema = schemaFactory.newSchema(new StreamSource(new StringReader(W3C_XSD_TOP_ELEMENT), "xsdTop"));
		return schema;
	}
}
