package com.ttc.ch2.bl.departure.common.reports;

import java.util.List;

import com.google.common.base.Throwables;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.ttc.ch2.bl.departure.common.MessagesManager;
import com.ttc.ch2.bl.departure.common.OperationStatus;
import com.ttc.ch2.bl.departure.common.TDMessage;
import com.ttc.ch2.bl.departure.common.TropicSynchronizeMessages;
import com.ttc.ch2.domain.comment.TDComment;


public class BaseReport {
	
	protected String END_OF_LINE="\n";
	protected String TAB="\t";
	
	protected OperationStatus opStatus;
	protected MessagesManager msgMgr;
	
	public BaseReport(OperationStatus opStatus, MessagesManager msgMgr) {
		super();
		this.opStatus = opStatus;
		this.msgMgr = msgMgr;
	}


	
	
	protected void exceptionComments()
	{
		//exceptions
				Iterable<TDMessage> exceptionMessage=Iterables.filter(msgMgr.getErrorMessages(), new MessageFinder(TropicSynchronizeMessages.UNEXPECTED_EXCEPTION.getCode()));
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
				msgMgr.getErrorMessages().removeAll(toRemove);
				toRemove.clear();
				
				Iterable<TDMessage> oaExceptionMessage=Iterables.filter(msgMgr.getErrorMessages(), new MessageFinder(TropicSynchronizeMessages.OUTGOING_ARCHIVE_EXCEPTION.getCode()));
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
				msgMgr.getErrorMessages().removeAll(toRemove);
				toRemove.clear();
				
	}

}
