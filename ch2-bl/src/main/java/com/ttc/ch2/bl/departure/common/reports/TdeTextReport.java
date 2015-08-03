package com.ttc.ch2.bl.departure.common.reports;

import java.util.Date;

import com.google.common.base.Optional;
import com.google.common.collect.Iterables;
import com.ttc.ch2.bl.departure.common.MessagesManager;
import com.ttc.ch2.bl.departure.common.OperationStatus;
import com.ttc.ch2.bl.departure.common.TDMessage;
import com.ttc.ch2.bl.departure.common.TropicSynchronizeMessages;
import com.ttc.ch2.common.DateHelper;
import com.ttc.ch2.common.DateHelper.CalculateTimePattern;
import com.ttc.ch2.domain.comment.TDComment;
import com.ttc.ch2.domain.jobs.QuartzJob;

public class TdeTextReport extends BaseReport {

	
	public TdeTextReport(OperationStatus opStatus, MessagesManager msgMgr) {
		super(opStatus,msgMgr);
	}
	
	public void create()
	{	
		mainComments();
		exceptionComments();
		
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
	
		msgMgr.clear();
	}

	
	private void mainComments()
	{
		String startProcess=DateHelper.dateTimeToString(opStatus.getStartOperation());
		String endProcess=DateHelper.dateTimeToString(opStatus.getEndOperation());
		String duration=DateHelper.calculateTime(opStatus.getStartOperation(), opStatus.getEndOperation(), CalculateTimePattern.HMS);
			
		TDComment comment=new TDComment();
		comment.setMessageCode(TropicSynchronizeMessages.EXTENDED_STATUS.getCode());
		comment.setMessage(TropicSynchronizeMessages.getMessage(TropicSynchronizeMessages.EXTENDED_STATUS.getCode(),startProcess,endProcess,duration));
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
		
		
		Optional<TDMessage> msgToRemove1=Iterables.tryFind(msgMgr.getMainMessages(), new MessageFinder(TropicSynchronizeMessages.SYNCHRONIZE_START.getCode()));
		msgMgr.getMainMessages().remove(msgToRemove1.isPresent() ? msgToRemove1.get() : null);
		Optional<TDMessage> msgToRemove2=Iterables.tryFind(msgMgr.getMainMessages(), new MessageFinder(TropicSynchronizeMessages.SYNCHRONIZE_END.getCode()));
		msgMgr.getMainMessages().remove(msgToRemove2.isPresent() ? msgToRemove2.get() : null);
		
		content.append("Additional information:").append(END_OF_LINE);
		for (TDMessage defaultComments : msgMgr.getMainMessages()) {
			content.append(TAB).append(defaultComments.getMsgEnum().getCode()+": "+defaultComments.getMessage()).append(END_OF_LINE);
		}
		
		msgMgr.getMainMessages().clear();
		comment.setContent(content.toString());
	}
}
