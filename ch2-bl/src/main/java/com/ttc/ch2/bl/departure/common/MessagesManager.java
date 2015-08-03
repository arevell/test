package com.ttc.ch2.bl.departure.common;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.base.Throwables;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.ttc.ch2.common.DateHelper;
import com.ttc.ch2.common.DateHelper.CalculateTimePattern;
import com.ttc.ch2.domain.comment.TDComment;

public class MessagesManager implements Serializable 
{
	private static final long serialVersionUID = 5699823897363355106L;
	
	public String END_OF_LINE="\n";
	public String TAB="\t";
	
	private final OperationStatus opStatus;

	private List<TDMessage> mainMessages=Lists.newArrayList();		
	private Map<String,BrandMessages> brandMessages=Maps.newHashMap();		
	private List<TDMessage> errorMessages=Lists.newArrayList();	
	
	public MessagesManager(OperationStatus opStatus) {
		super();
		this.opStatus = opStatus;
	}
	
	public void addMessage(TDMessage msg)
	{
		if(StringUtils.isEmpty(msg.getBrandCode()))
		{
			mainMessages.add(msg);
		}
		else				
		{
			if (!brandMessages.containsKey(msg.getBrandCode())) {
				BrandMessages brandMsg=new BrandMessages();
				brandMessages.put(msg.getBrandCode(), brandMsg);
			}				
			BrandMessages brandMsg=brandMessages.get(msg.getBrandCode());				
			brandMsg.addMessage(msg);
		}
		
		if(msg.getE()!=null)
			errorMessages.add(msg);		
	}
	
	private void clear(){
		mainMessages.clear();
		brandMessages.clear();
		errorMessages.clear();
	}

	
	public void createTDComments()
	{		
		// generate main status of operation
		// generate unexpected exceptions 
		// generate no brands
		// generate brands logs
		mainComments();
		exceptionComments();							
		// no brand log
		Optional<TDMessage> optional=Iterables.tryFind(mainMessages, new MessageFinder(TropicSynchronizeMessages.NO_BRANDS.getCode()));
		if(optional.isPresent())
		{
			TDComment comment=new TDComment();
			comment.setMessageCode(optional.get().getMsgEnum().getCode());
			comment.setMessage(optional.get().getMessage());
			comment.setModifiedBy(opStatus.getUser());
			comment.setModifiedTime(optional.get().getTime());			
			comment.setTourDepartureHistory(opStatus.getTourDepartureHistory());
			opStatus.getTourDepartureHistory().getComments().add(comment);
			return;
		}
		
		for (String key : brandMessages.keySet()) 
		{
			createTDCommentForBrand(key);			
		}
		
		clear();
	}


	private void mainComments()
	{
		String startProcess=DateHelper.dateTimeToString(opStatus.getStartOperation());
		String endProcess=DateHelper.dateTimeToString(opStatus.getEndOperation());
		String duration=DateHelper.calculateTime(opStatus.getStartOperation(), opStatus.getEndOperation(), CalculateTimePattern.HMS);
			
		TDComment comment=new TDComment();
		comment.setMessageCode(TropicSynchronizeMessages.SYNCHRONIZE_STATUS.getCode());
		comment.setMessage(TropicSynchronizeMessages.getMessage(TropicSynchronizeMessages.SYNCHRONIZE_STATUS.getCode(),startProcess,endProcess,duration));
		comment.setModifiedBy(opStatus.getUser());
		comment.setModifiedTime(new Date());			
		comment.setTourDepartureHistory(opStatus.getTourDepartureHistory());
		opStatus.getTourDepartureHistory().getComments().add(comment);
		
		StringBuilder content=new StringBuilder();
		content.append("Main information:").append(END_OF_LINE);
		content.append("Operation start:").append(startProcess).append(END_OF_LINE);
		content.append("Operation stop:").append(endProcess).append(END_OF_LINE);
		content.append("Operation duration:").append(duration).append(END_OF_LINE);
		content.append("Operation status:").append(opStatus.getStatus()).append(END_OF_LINE);
		
		content.append("Imported departures:").append(opStatus.getImportCount()).append(END_OF_LINE);
		content.append("Inserted rows to Content Repository:").append(opStatus.getInsertCount()).append(END_OF_LINE);
		content.append("Updated rows in Content Repository:").append(opStatus.getUpdateCount()).append(END_OF_LINE);
//		content.append("Deleted rows from Content Repository:").append(opStatus.getDeleteCount()).append(END_OF_LINE);
		content.append("Rejected departures reason that the same MD5:").append(opStatus.getRejectMd5Count()).append(END_OF_LINE);
		content.append("Rejected departures reason - product does not have departures:").append(opStatus.getRejectedCountDeparture()).append(END_OF_LINE);		
		content.append(END_OF_LINE);
		
		Optional<TDMessage> msgToRemove1=Iterables.tryFind(mainMessages, new MessageFinder(TropicSynchronizeMessages.SYNCHRONIZE_START.getCode()));
		mainMessages.remove(msgToRemove1.isPresent() ? msgToRemove1.get() : null);
		Optional<TDMessage> msgToRemove2=Iterables.tryFind(mainMessages, new MessageFinder(TropicSynchronizeMessages.SYNCHRONIZE_END.getCode()));
		mainMessages.remove(msgToRemove2.isPresent() ? msgToRemove2.get() : null);
		
		content.append("Additional information:").append(opStatus.getImportCount()).append(END_OF_LINE);
		for (TDMessage defaultComments : mainMessages) {
			content.append(TAB).append(defaultComments.getMsgEnum().getCode()+": "+defaultComments.getMessage()).append(END_OF_LINE);
		}
		
		mainMessages.clear();
		comment.setContent(content.toString());
	}
	
	private void exceptionComments()
	{
		//exceptions
				Iterable<TDMessage> exceptionMessage=Iterables.filter(errorMessages, new MessageFinder(TropicSynchronizeMessages.UNEXPECTED_EXCEPTION.getCode()));
				List<TDMessage> toRemove=Lists.newArrayList();
				for (TDMessage tdMessage : exceptionMessage) {
					
					TDComment comment=new TDComment();
					comment.setMessageCode(TropicSynchronizeMessages.UNEXPECTED_EXCEPTION.getCode());
					comment.setMessage(tdMessage.getMessage());
					comment.setModifiedBy(opStatus.getUser());
					comment.setModifiedTime(tdMessage.getTime());		
					comment.setStackTrace(Throwables.getStackTraceAsString(tdMessage.getE()));
					comment.setTourDepartureHistory(opStatus.getTourDepartureHistory());
					opStatus.getTourDepartureHistory().getComments().add(comment);
					toRemove.add(tdMessage);
				}
				errorMessages.removeAll(toRemove);
				toRemove.clear();
				
				Iterable<TDMessage> oaExceptionMessage=Iterables.filter(errorMessages, new MessageFinder(TropicSynchronizeMessages.OUTGOING_ARCHIVE_EXCEPTION.getCode()));
				for (TDMessage tdMessage : oaExceptionMessage) {
					
					TDComment comment=new TDComment();
					comment.setMessageCode(TropicSynchronizeMessages.OUTGOING_ARCHIVE_EXCEPTION.getCode());
					comment.setMessage(tdMessage.getMessage());
					comment.setModifiedBy(opStatus.getUser());
					comment.setModifiedTime(tdMessage.getTime());		
					comment.setStackTrace(Throwables.getStackTraceAsString(tdMessage.getE()));
					comment.setTourDepartureHistory(opStatus.getTourDepartureHistory());
					opStatus.getTourDepartureHistory().getComments().add(comment);
					toRemove.add(tdMessage);
				}
				errorMessages.removeAll(toRemove);
				toRemove.clear();
				
	}
	
	public TDComment  createTDCommentForBrand(String brandCode)
	{
			BrandMessages brandMessage=getBrandMessages().get(brandCode);
		    TDMessage brandStatus=Iterables.find(brandMessage.getMassages(), new MessageFinder(TropicSynchronizeMessages.BRAND_STATUS.getCode()));		
		    TDComment comment=new TDComment();
			comment.setMessageCode(TropicSynchronizeMessages.BRAND_STATUS.getCode());
			comment.setMessage(brandStatus.getMessage() + (brandMessage.isFailSynchronize() ? " - fail": " - success"));	
			comment.setModifiedBy(opStatus.getUser());
			comment.setModifiedTime(new Date());			
			comment.setTourDepartureHistory(opStatus.getTourDepartureHistory());
			opStatus.getTourDepartureHistory().getComments().add(comment);
			brandMessage.getMassages().remove(brandStatus);
		
			StringBuilder content=new StringBuilder();
			content.append("Brands information:").append(END_OF_LINE);	
					Optional<TDMessage> brandStart=Iterables.tryFind(brandMessage.getMassages(), new MessageFinder(TropicSynchronizeMessages.OPERATION_FOR_BRAND_START.getCode()));
					if(brandStart.isPresent()){
						content.append(TAB).append(brandStart.get().getMessage()).append(END_OF_LINE);
						brandMessage.getMassages().remove(brandStart.get());
					}
					
					Optional<TDMessage> brandEnd=Iterables.tryFind(brandMessage.getMassages(), new MessageFinder(TropicSynchronizeMessages.OPERATION_FOR_BRAND_END.getCode()));
					if(brandEnd.isPresent()){
						content.append(TAB).append(brandEnd.get().getMessage()).append(END_OF_LINE);
						brandMessage.getMassages().remove(brandEnd.get());
					}

					for (TDMessage tdMessage : brandMessage.getMassages()) {
						content.append(TAB).append(tdMessage.getMsgEnum().getCode()).append(": ").append(tdMessage.getMessage()).append(END_OF_LINE);
					}
			
		      content.append(TAB).append("Details sellingCompany:").append(END_OF_LINE);
			  for (String key : brandMessage.getComapnyMessages().keySet()) {
				 sellingCompaniesMessages(key,Lists.newArrayList(brandMessage.getComapnyMessages().get(key)), content);				
			  }			  
			  productMessage(content, brandMessage.getProductMessages());
			  
			  comment.setContent(content.toString());
			  
			  getBrandMessages().remove(brandCode);
			  return comment;	
	}
	
	private void sellingCompaniesMessages(String key,Collection<TDMessage> msgs,StringBuilder content)
	{
		content.append(TAB).append(TAB).append("SellingCompany:"+key).append(END_OF_LINE);				
		List<TDMessage> rejectedCompany=Lists.newArrayList(Iterables.filter(msgs, new MessageFinder(TropicSynchronizeMessages.REJECT_DATA_FOR_SELLING_COMPANY_PRODUCT_CODE.getCode())));
		if (rejectedCompany.iterator().hasNext()){
			content.append(TAB).append(TAB).append("Rejected companies information::"+rejectedCompany.size()).append(END_OF_LINE);
		}
		
		 
		
		int i=1;
		for (TDMessage tdMessage :rejectedCompany) {
			content.append(TAB).append(TAB).append(i+": "+tdMessage.getMessage()).append(END_OF_LINE);
			msgs.remove(tdMessage);
			i++;
		}
		
		List<TDMessage> checkSumProduct=Lists.newArrayList(Iterables.filter(msgs, new MessageFinder(TropicSynchronizeMessages.TD_CHECK_SUM_EXIST_IN_CR.getCode())));
		if(checkSumProduct.iterator().hasNext())
		{
			content.append(TAB).append(TAB).append("Check MD5 information:"+checkSumProduct.size()).append(END_OF_LINE);
		}		
		i=1;
		for (TDMessage tdMessage : checkSumProduct) {
			content.append(TAB).append(TAB).append(TAB).append(i+": "+tdMessage.getMessage()).append(END_OF_LINE);
			msgs.remove(tdMessage);
			i++;
		}
		
		
		List<TDMessage> importedCompany=Lists.newArrayList(Iterables.filter(msgs, new MessageFinder(TropicSynchronizeMessages.IMPORT_DATA_FOR_SELLING_COMPANY_PRODUCT_CODE.getCode())));
		if(importedCompany.iterator().hasNext())
		{
			content.append(TAB).append(TAB).append("Imported companies information:"+importedCompany.size()).append(END_OF_LINE);
		}
		i=1;
		for (TDMessage tdMessage : Lists.newArrayList(importedCompany)) {
			content.append(TAB).append(TAB).append(i+": "+tdMessage.getMessage()).append(END_OF_LINE);
			msgs.remove(tdMessage);
			i++;
		}
		
		
		if(msgs.size()>0)
		{
			content.append(TAB).append(TAB).append("Additional information:").append(END_OF_LINE);
			for (TDMessage tdMessage : msgs) {
				content.append(TAB).append(TAB).append(tdMessage.getMsgEnum().getCode()).append(": ").append(tdMessage.getMessage()).append(END_OF_LINE);
			}			
		}				
	}
	
	
	private void productMessage(StringBuilder content,	Multimap<String, TDMessage> productMessages) {
		
		content.append(END_OF_LINE);
		content.append(TAB).append("Product Details:").append(END_OF_LINE);
		int i=1;
		for (String key : productMessages.keySet()) {			
			content.append(TAB).append(TAB).append(i+": Product:").append(key).append(END_OF_LINE);
			
			Collection<TDMessage> msg=productMessages.get(key);
			for (TDMessage tdMessage : msg) {
				content.append(TAB).append(TAB).append(TAB).append(tdMessage.getMsgEnum().getCode()+": "+tdMessage.getMessage()).append(END_OF_LINE);	
			}			
			i++;
		}
		
	}

	public List<TDMessage> getMainMessages() {
		return mainMessages;
	}

	public Map<String, BrandMessages> getBrandMessages() {
		return brandMessages;
	}	
}

class BrandMessages implements Serializable
{
	private static final long serialVersionUID = 8523446183539433120L;
	private List<TDMessage> massages=Lists.newArrayList();
	private Multimap<String,TDMessage> comapnyMessages=ArrayListMultimap.create();
	private Multimap<String, TDMessage> productMessages=ArrayListMultimap.create();

	public void addMessage(TDMessage msg)
	{
		if(StringUtils.isEmpty(msg.getSellingCompanyCode()) && StringUtils.isEmpty(msg.getProductCode()))
		{
			massages.add(msg);
		}
		if(StringUtils.isNotEmpty(msg.getSellingCompanyCode()))
		{			
			comapnyMessages.put(msg.getSellingCompanyCode(), msg);		
		}
		
		if(StringUtils.isNotEmpty(msg.getProductCode()))
		{
			productMessages.put(msg.getProductCode(),msg);
		}	
	}

	public List<TDMessage> getMassages() {
		return massages;
	}

	public Multimap<String, TDMessage> getComapnyMessages() {
		return comapnyMessages;
	}

	public Multimap<String, TDMessage> getProductMessages() {
		return productMessages;
	}

	boolean isFailSynchronize()
	{
		boolean result=false;		
		result= Iterables.filter(massages, new MessageFinderByCodeType("E")).iterator().hasNext() ;
		if(result)
			return true;
		
		for (String key : comapnyMessages.keySet()) {		
			result=Iterables.filter(comapnyMessages.get(key), new MessageFinderByCodeType("E")).iterator().hasNext();
			if(result)
				return true;
		}
		
		for (String key : productMessages.keySet()) {		
			result=Iterables.filter(productMessages.get(key), new MessageFinderByCodeType("E")).iterator().hasNext();
			if(result)
				return true;
		}
		return result; 
	}
	
}

class MessageFinder implements Predicate<TDMessage>
{
	private String code;

	public MessageFinder(String code) {
		super();
		this.code = code;
	}

	@Override
	public boolean apply(TDMessage input) {
		return input.getMsgEnum().getCode().equals(code);
	}	
}

class MessageFinderByCodeType implements Predicate<TDMessage>
{
	private String codeType;
	
	public MessageFinderByCodeType(String code) {
		super();
		this.codeType = code;
	}
	
	@Override
	public boolean apply(TDMessage input) {
		return input.getMsgEnum().getCode().contains(codeType);
	}
	
	
	
}