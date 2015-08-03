package com.ttc.ch2.ui.mvc;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;

import org.apache.commons.lang.NumberUtils;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.ecs.xml.XML;
import org.apache.ecs.xml.XMLDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zkoss.zhtml.Pre;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.ttc.common.utils.ThrowablesHelper;
import com.ttc.util.messages.Message;
import com.ttc.util.messages.MessageBuilder;
import com.ttc.util.messages.Severity;
import com.ttc.util.messages.subject.MessageSubjectName;
import com.ttc.util.messages.type.FixedSubjectMessageType;
import com.ttc.util.soap_messages.type.SoapMessageContext;
import com.ttc.util.soap_messages.type.SoapStatus;



public class ExceptionXmlConverter {

	private static final Logger logger = LoggerFactory.getLogger(ExceptionXmlConverter.class);
	
	public String convert(Map<String,Object> params){
		logger.trace("ExceptionXmlConverter:convert-start");
	
		String result=null;
		try{
			result=convertToXmlByJaxb(params);
		}
		catch(Exception e){
			logger.error("",e);
			result="ExceptionXmlConverter cannot convert exception to xml";
	//		try{
	//			result=convertSimpleMessage(params);
	//		}
	//		catch (Exception e1)
	//		{
	//			logger.error("",e1);
	//			result="ExceptionXmlConverter cannot convert exception to xml";
	//		}
		}
		logger.trace("ExceptionXmlConverter:convert-end");
		return result;
	}
	
	public  String convertToXmlByJaxb(Exception e) throws Exception{
		logger.trace("ExceptionXmlConverter:convertToXmlByJaxb-start");
		
		Preconditions.checkArgument(e!=null,"ExceptionXmlConverter->convertToXmlByJaxb arg Exception is null");		
		 String code = "500";
		 List<String> messagesList=ThrowablesHelper.getAllMessages(e);
		
		 List<Message> msgs=Lists.newArrayList();
		 SoapMessageContext mctx=new SoapMessageContext();
			
		for (String exceptionMsg : messagesList) {				
			Message message=MessageBuilder.newMessage(new ExceptionMessages(code,exceptionMsg,Severity.ERROR)).build();
			msgs.add(message);
		}
		
		mctx.setMessages((Collection<Message>)msgs);
		mctx.setStatus(SoapStatus.FAILURE);
		logger.trace("ExceptionXmlConverter:convertToXmlByJaxb-end");
		return marshal(mctx);		
	}
	
	public  String convertToXmlByJaxb(int code,String msg,Severity severity) throws Exception{
		logger.trace("ExceptionXmlConverter:convertToXmlByJaxb-start");
		
		Preconditions.checkArgument(severity!=null,"ExceptionXmlConverter->convertToXmlByJaxb arg code is not initialize");
		Preconditions.checkArgument(StringUtils.isNotBlank(msg),"ExceptionXmlConverter->convertToXmlByJaxb arg msg is null");
		Preconditions.checkArgument(severity!=null,"ExceptionXmlConverter->convertToXmlByJaxb arg severity is null");
		
		List<Message> msgs=Lists.newArrayList();
		SoapMessageContext mctx=new SoapMessageContext();
		Message message=MessageBuilder.newMessage(new ExceptionMessages(String.valueOf(code),msg,severity)).build();
		msgs.add(message);		
		mctx.setMessages((Collection<Message>)msgs);
		mctx.setStatus(Severity.ERROR == severity ? SoapStatus.FAILURE : SoapStatus.SUCCESS);
		logger.trace("ExceptionXmlConverter:convertToXmlByJaxb-end");
		return marshal(mctx);		
	}
	

private String marshal(SoapMessageContext mctx) throws JAXBException{
	logger.trace("ExceptionXmlConverter:marshal-start");
	String result=null;
	try{
	JAXBContext jaxbContext = JAXBContext.newInstance(SoapMessageContext.class);			
	StringWriter sw=new StringWriter(); 
	JAXBElement<SoapMessageContext> wrapedElement = new JAXBElement<SoapMessageContext>(new QName("error"), SoapMessageContext.class, mctx);
				
	Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
	jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
	
	jaxbMarshaller.marshal(wrapedElement, sw);
	
	result=sw.getBuffer().toString();	
	}
	catch(Exception e){
		logger.error("",e);
		result="ExceptionXmlConverter cannot convert exception to xml";
	}
	logger.trace("ExceptionXmlConverter:marshal-end");
	return result;
}

	
	private  String convertToXmlByJaxb(Map<String,Object> params) throws Exception{
		logger.trace("ExceptionXmlConverter:convertToXmlByJaxb-start");
			 String description= (String) params.get("description");
			 Preconditions.checkArgument(StringUtils.isNotBlank(description),"ExceptionXmlConverter:convertToXmlByJaxb-> params arg need decription param");
			 String code = (String) ObjectUtils.toString(params.get("code"));
			 Preconditions.checkArgument(StringUtils.isNotBlank(code),"ExceptionXmlConverter:convertToXmlByJaxb-> params arg need code param");
			 List<String> messagesList=(List<String>) params.get("exception_msg");			 
			 Preconditions.checkArgument(messagesList!=null && messagesList.size()>0,"ExceptionXmlConverter:convertToXmlByJaxb-> params arg need exception_msg param");
			
			List<Message> msgs=Lists.newArrayList();
			SoapMessageContext mctx=new SoapMessageContext();
			Message messageDesc=MessageBuilder.newMessage(new ExceptionMessages(code,description,Severity.ERROR)).build();
			msgs.add(messageDesc);
			for (String exceptionMsg : messagesList) {				
				Message message=MessageBuilder.newMessage(new ExceptionMessages(code,exceptionMsg,Severity.ERROR)).build();
				msgs.add(message);
			}
			mctx.setMessages((Collection<Message>)msgs);
			mctx.setStatus(SoapStatus.FAILURE);
			logger.trace("ExceptionXmlConverter:convertToXmlByJaxb-end");
			return marshal(mctx);			
	}
	
	
	
	private String convertSimpleMessage(Map<String,Object> params){
		XML root=new XML("error");
		root.setPrettyPrint(true);
		
		XML title=new XML("title");
		title.setPrettyPrint(true);
		title.setTagText((String) params.get("title"));
		root.addElement(title);
		
		XML code=new XML("code");
		code.setPrettyPrint(true);
		code.setTagText((String) params.get("code"));
		root.addElement(code);
		
		XML messages=new XML("messages");
		messages.setPrettyPrint(true);		
		root.addElement(messages);
						
		List<String> messagesList=(List<String>) params.get("exception_msg");
		for (String txt : messagesList) {
			XML message=new XML("message");
			message.setPrettyPrint(true);
			message.setTagText(txt);
			messages.addElement(message);	
		}
		
		XMLDocument xmlDocument=new XMLDocument();		
		xmlDocument.addElement(root);
				
		return xmlDocument.toString();
	}
	
	
	
	private class ExceptionMessages implements FixedSubjectMessageType
	{
		private String code;
		private String description;
		private Severity severity;
		
		public ExceptionMessages(String code, String description,Severity severity) {
			super();
			this.code = code;
			this.description = description;
			this.severity = severity;
		}

		public String getCode() {
			return code;
		}

		public String getDescription() {
			return description;
		}

		public Severity getSeverity() {
			return severity;
		}

		@Override
		public List<MessageSubjectName> getSubjectNames() {
			return new ArrayList<MessageSubjectName>();
		}						
	}
}
