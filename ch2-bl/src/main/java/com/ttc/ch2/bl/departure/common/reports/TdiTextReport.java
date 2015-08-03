package com.ttc.ch2.bl.departure.common.reports;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.google.common.base.Optional;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.ttc.ch2.bl.departure.common.BrandMessages;
import com.ttc.ch2.bl.departure.common.MessagesManager;
import com.ttc.ch2.bl.departure.common.OperationStatus;
import com.ttc.ch2.bl.departure.common.TDMessage;
import com.ttc.ch2.bl.departure.common.TropicSynchronizeMessages;
import com.ttc.ch2.common.DateHelper;
import com.ttc.ch2.common.DateHelper.CalculateTimePattern;
import com.ttc.ch2.common.enums.SystemDirection;
import com.ttc.ch2.domain.comment.TDComment;

public class TdiTextReport extends BaseReport implements Serializable{
	
	private static final long serialVersionUID = 6701974136895558610L;
		
	public TdiTextReport(OperationStatus opStatus, MessagesManager msgMgr) {
		super(opStatus,msgMgr);
	}

	
	public void create(){		
		// generate main status of operation
		// generate unexpected exceptions 
		// generate no brands
		// generate brands logs
		mainComments();
		exceptionComments();							
		// no brand log
		Optional<TDMessage> optional=Iterables.tryFind(msgMgr.getMainMessages(), new MessageFinder(TropicSynchronizeMessages.NO_BRANDS.getCode()));
		if(optional.isPresent()){
			TDComment comment=new TDComment();
			comment.setMessageCode(optional.get().getMsgEnum().getCode());
			comment.setMessage(optional.get().getMessage());
			comment.setModifiedBy(opStatus.getUser());
			comment.setModifiedTime(optional.get().getTime());			
			comment.setTourDepartureHistory(opStatus.getTourDepartureHistory());
			opStatus.getTourDepartureHistory().getComments().add(comment);
			return;
		}
		
		for (String key : msgMgr.getBrandMessages().keySet()){
			createTDCommentForBrand(key);			
		}
		
		msgMgr.clear();
	}
	
	private void mainComments(){
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
		content.append("Brand:").append(opStatus.getQuartzJobHistory().getBrand().getBrandName()).append(" (code:").append(opStatus.getQuartzJobHistory().getBrand().getCode()).append(")").append(END_OF_LINE);
		content.append("Operation start:").append(startProcess).append(END_OF_LINE);
		content.append("Operation stop:").append(endProcess).append(END_OF_LINE);
		content.append("Operation duration:").append(duration).append(END_OF_LINE);
		content.append("Operation status:").append(opStatus.getStatus()).append(END_OF_LINE);
				
		content.append("Imported tours per SC:").append(opStatus.getImportCount()).append(END_OF_LINE);
		if(SystemDirection.HABS==opStatus.getSystemDirection()){
			content.append("Tours per SC without checkSum:").append(opStatus.getTourWithOutCheckSum()).append(END_OF_LINE);
		}		
		content.append("Rejected tours per SC - reason products don't have departures:").append(opStatus.getRejectedCountDeparture()).append(END_OF_LINE);
		
		content.append("Inserted tours to Content Repository:").append(opStatus.getInsertCount()).append(END_OF_LINE);
		content.append("Updated tours in Content Repository:").append(opStatus.getUpdateCount()).append(END_OF_LINE);
		content.append("Rejected tours - reason the same MD5:").append(opStatus.getRejectMd5Count()).append(END_OF_LINE);		
		content.append("Rejected tours - reason invalid schema:").append(opStatus.getRejectedInvalidSchema()).append(END_OF_LINE);
		content.append(END_OF_LINE);
		
		Optional<TDMessage> msgToRemove1=Iterables.tryFind(msgMgr.getMainMessages(), new MessageFinder(TropicSynchronizeMessages.SYNCHRONIZE_START.getCode()));
		msgMgr.getMainMessages().remove(msgToRemove1.isPresent() ? msgToRemove1.get() : null);
		Optional<TDMessage> msgToRemove2=Iterables.tryFind(msgMgr.getMainMessages(), new MessageFinder(TropicSynchronizeMessages.SYNCHRONIZE_END.getCode()));
		msgMgr.getMainMessages().remove(msgToRemove2.isPresent() ? msgToRemove2.get() : null);
		
		content.append("Additional information:").append(END_OF_LINE);		
		Optional<TDMessage> msgWithBrandInfoStart=Iterables.tryFind(msgMgr.getMainMessages(), new MessageFinder(TropicSynchronizeMessages.OPERATION_FOR_BRAND_START.getCode()));
		Optional<TDMessage> msgWithBrandInfoEnd=Iterables.tryFind(msgMgr.getMainMessages(), new MessageFinder(TropicSynchronizeMessages.OPERATION_FOR_BRAND_END.getCode()));
		msgMgr.getMainMessages().remove(msgWithBrandInfoStart.isPresent() ? msgWithBrandInfoStart.get() : null);
		msgMgr.getMainMessages().remove(msgWithBrandInfoEnd.isPresent() ? msgWithBrandInfoEnd.get() : null);
		
		if(msgWithBrandInfoStart.isPresent()){
			content.append(TAB).append(msgWithBrandInfoStart.get().getMsgEnum().getCode()+": "+msgWithBrandInfoStart.get().getMessage()).append(END_OF_LINE);
		}
		if(msgWithBrandInfoEnd.isPresent()){
			content.append(TAB).append(msgWithBrandInfoEnd.get().getMsgEnum().getCode()+": "+msgWithBrandInfoEnd.get().getMessage()).append(END_OF_LINE);
		}
		
		for (TDMessage defaultComments : msgMgr.getMainMessages()) {
			content.append(TAB).append(defaultComments.getMsgEnum().getCode()+": "+defaultComments.getMessage()).append(END_OF_LINE);
		}
		
		msgMgr.getMainMessages().clear();
		comment.setContent(content.toString());
	}
	
	
	public TDComment  createTDCommentForBrand(String brandCode){
			BrandMessages brandMessage=msgMgr.getBrandMessages().get(brandCode);
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
			
		      content.append(TAB).append("Details SellingCompany:").append(END_OF_LINE);
			  for (String key : brandMessage.getComapnyMessages().keySet()) {
				 sellingCompaniesMessages(key,Lists.newArrayList(brandMessage.getComapnyMessages().get(key)), content);				
			  }			  
			  productMessage(content, brandMessage.getProductMessages());
			  
			  comment.setContent(content.toString());
			  
			  msgMgr.getBrandMessages().remove(brandCode);
			  return comment;	
	}
	
	private void sellingCompaniesMessages(String key,Collection<TDMessage> msgs,StringBuilder content){
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
		if(checkSumProduct.iterator().hasNext()){
			content.append(TAB).append(TAB).append("Check MD5 information:"+checkSumProduct.size()).append(END_OF_LINE);
		}		
		i=1;
		for (TDMessage tdMessage : checkSumProduct) {
			content.append(TAB).append(TAB).append(TAB).append(i+": "+tdMessage.getMessage()).append(END_OF_LINE);
			msgs.remove(tdMessage);
			i++;
		}
		
		
		List<TDMessage> importedCompany=Lists.newArrayList(Iterables.filter(msgs, new MessageFinder(TropicSynchronizeMessages.IMPORT_DATA_FOR_SELLING_COMPANY_PRODUCT_CODE.getCode())));
		if(importedCompany.iterator().hasNext()){
			content.append(TAB).append(TAB).append("Imported companies information:"+importedCompany.size()).append(END_OF_LINE);
		}
		i=1;
		for (TDMessage tdMessage : Lists.newArrayList(importedCompany)) {
			content.append(TAB).append(TAB).append(i+": "+tdMessage.getMessage()).append(END_OF_LINE);
			msgs.remove(tdMessage);
			i++;
		}
		
		
		if(msgs.size()>0){
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

}
