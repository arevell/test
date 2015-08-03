package com.ttc.ch2.common;

import java.io.StringReader;
import java.net.URL;
import java.util.Date;
import java.util.Random;
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
import com.ttc.ch2.domain.BrandLock;
import com.ttc.ch2.domain.Function;
import com.ttc.ch2.domain.SellingCompany;
import com.ttc.ch2.domain.auth.CCAPIAuthority;
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
import com.ttc.ch2.domain.upload.UploadStatus;
import com.ttc.ch2.domain.upload.UploadTourInfoFile;
import com.ttc.ch2.domain.upload.UploadTourInfoFileSource;
import com.ttc.ch2.domain.upload.UploadTourInfoFileStatus;
import com.ttc.ch2.domain.user.UserCCAPI;
import com.ttc.ch2.domain.user.UserGui;

public class BlSampleGenerator {

	public static  Brand generateBrand()
	{
		Brand brand=new Brand();	
		brand.setSellingCompanies(Sets.newHashSet(generateSellingCompany(brand)));		
		brand.setBrandName("brand name:1");
		brand.setCode("BC");
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
		
		CCAPIAuthority authority=new CCAPIAuthority();
		authority.setFunction(generateFunction(FunctionType.UPLOAD_TOUR_INFO));
		authority.setSellingCompany(generateSellingCompany(generateBrand()));
		ccapiUser.getCcapiAuthorities().add(authority);
		return ccapiUser;
	}
	
	
	public static Function generateFunction(FunctionType type){
		Function f=new Function();
		f.setName(type.getSimpleName());
		return f;
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
	
	
	public static UploadTourInfoFile generateUploadTourInfoFile(int index){
		UploadTourInfoFile uploadTourInfoFile=new UploadTourInfoFile();		
		uploadTourInfoFile.setBrand(generateBrand());
		uploadTourInfoFile.setName(genFileName(uploadTourInfoFile.getBrand().getCode()));
		uploadTourInfoFile.setStatus(UploadTourInfoFileStatus.PRE_PROCESSING);
		uploadTourInfoFile.setDateUpload(new Date());
		uploadTourInfoFile.setSourceUploadFile(UploadTourInfoFileSource.API);
		uploadTourInfoFile.setId((long) index);
		return uploadTourInfoFile;
	}
	
	public static UploadStatus generateUploadStatus(int index){
		UploadStatus uploadStatus=new UploadStatus();		
		uploadStatus.setBrandCode(generateBrand().getCode());
		uploadStatus.setCount(10);
		uploadStatus.setDateUpdate(new Date());
		uploadStatus.setFileNameDesc("File Description:"+index);
		uploadStatus.setMessages("Message:"+index);
		uploadStatus.setTypeMsg(TypeMsg.INF);
		uploadStatus.setValue(10);
		uploadStatus.setId((long) index);
		return uploadStatus;
	}
	
	public static BrandLock generateBrandLock(int index){
		BrandLock lock=new BrandLock();		
		lock.setBrandCode(generateBrand().getCode());
		lock.setProccessName(ProcessName.UPLOAD);
		return lock;
	}
	
	
	private static Random rnd=new Random();
	public static String genFileName(){		
		return genFileName("CH");
	}
	
	public static String genFileName(String brandCode){		
		int h=rnd.nextInt(11);	
		int m=rnd.nextInt(59);	
		int s=rnd.nextInt(59);	
		Date d=new Date();
		d=DateUtils.setHours(d, h);
		d=DateUtils.setMinutes(d, m);
		d=DateUtils.setSeconds(d, s);		
//		CH-2014-Jul-24T09-48-53Z.zip
		StringBuilder sb=new StringBuilder();
		sb.append(brandCode+"-"+DateHelper.dateToString(d,"yyyy-MM-dd'T'HH-mm-ss")).append("Z").append(".zip");
		return sb.toString();
	}
	
}
