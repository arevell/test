package com.ttc.ch2.bl.upload.common;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.base.Throwables;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.ttc.ch2.common.DateHelper;
import com.ttc.ch2.common.DateHelper.CalculateTimePattern;
import com.ttc.ch2.domain.comment.TIComment;
import com.ttc.ch2.domain.upload.UploadTourInfoFileStatus;

public class MessageManager {
	
	public static final String MODIFY_USER="System";
	public String END_OF_LINE="\n";
	public String TAB="\t";
	
	private OperationStatus operationStatus;
	private List<TIMessage> mainMessages=Lists.newArrayList();
	private Multimap<String,TIMessage> fileMessages= ArrayListMultimap.create();

	
	public MessageManager(OperationStatus operationStatus) {
		super();
		this.operationStatus = operationStatus;
	}
	
	public void addMessage(String productCode, TIMessage tiMessage)
	{
		if(StringUtils.isEmpty(productCode))
			mainMessages.add(tiMessage);
		else
		{
			fileMessages.put(productCode, tiMessage);
		}
		
	}
	
	public void createTIComments()
	{	
		createMainComments();			
		createProductComments();
		
	}
	
	
	private void createProductComments() {
			
		Map<String,Collection<TIMessage>> filesWithErrors=Maps.newLinkedHashMap();
		Map<String,Collection<TIMessage>> filesWithoutErrors=Maps.newLinkedHashMap();
		for (String key : fileMessages.keySet()) {			
			Collection<TIMessage> listMsgForFile=fileMessages.get(key);
			
			boolean errorExist=false;
			for (TIMessage tiMessage : listMsgForFile) {				
				if(tiMessage.getMsgEnum().getCode().contains("ERR"))
				{
					filesWithErrors.put(key, Lists.newLinkedList(listMsgForFile));
					errorExist=true;
					break;
				}
			}
			if(errorExist==false)
				filesWithoutErrors.put(key, Lists.newLinkedList(listMsgForFile));
		}		
//		fileMessages.clear();
			
		if( filesWithErrors.size()>0)
		{
			TIComment comment=new TIComment();
			comment.setMessageCode(TourInfoMessages.UPLOAD_STATUS_ERROR.getCode());
			comment.setMessage(TourInfoMessages.getMessage(TourInfoMessages.UPLOAD_STATUS_ERROR));
			comment.setModifiedBy(MODIFY_USER);
			comment.setModifiedTime(new Date());	
			comment.setUploadTourInfoFile(operationStatus.getUploadTourInfoFile());
			operationStatus.getUploadTourInfoFile().getComments().add(comment);
			
			StringBuilder contentError=new StringBuilder();
			contentError.append("Error information:").append(END_OF_LINE);	
			int i=1;
			for (String key : filesWithErrors.keySet()) {
				contentError.append((i++)+". File Name:").append(key).append(END_OF_LINE);				
				for (TIMessage msg : filesWithErrors.get(key)) {
					contentError.append(TAB).append(msg.getMsgEnum().getCode()).append(" ").append(msg.getMessage()).append(END_OF_LINE);
					if(msg.getE()!=null)
					{
						contentError.append(TAB).append(Throwables.getStackTraceAsString(msg.getE())).append(END_OF_LINE);
						contentError.append(END_OF_LINE);
					}
				}
			}			
			comment.setContent(contentError.toString());
		}
		if( filesWithoutErrors.size()>0)
		{
			TIComment comment=new TIComment();
			comment.setMessageCode(TourInfoMessages.UPLOAD_STATUS_INFO.getCode());
			comment.setMessage(TourInfoMessages.getMessage(TourInfoMessages.UPLOAD_STATUS_INFO));
			comment.setModifiedBy(MODIFY_USER);
			comment.setModifiedTime(new Date());	
			comment.setUploadTourInfoFile(operationStatus.getUploadTourInfoFile());
			operationStatus.getUploadTourInfoFile().getComments().add(comment);
			
			StringBuilder contentInfo=new StringBuilder();
			
			contentInfo.append("Additional information:").append(END_OF_LINE);
			int i=1;
			for (String key : filesWithoutErrors.keySet()) {
				contentInfo.append((i++)+". File Name:").append(key).append(END_OF_LINE);	
				List<TIMessage> msgs=Lists.newArrayList(filesWithoutErrors.get(key));
				for (TIMessage msg : msgs) {
					contentInfo.append(TAB).append(msg.getMsgEnum().getCode()).append(" ").append(msg.getMessage()).append(END_OF_LINE);
				}
			}			
			comment.setContent(contentInfo.toString());
		}		
	}

	private void createMainComments()
	{
		TIComment comment=null;
		
		Optional<TIMessage> uploadStrart=Iterables.tryFind(mainMessages, new MessageFinder(TourInfoMessages.UPLOAD_START.getCode()));
		Optional<TIMessage> uploadEnd=Iterables.tryFind(mainMessages, new MessageFinder(TourInfoMessages.UPLOAD_END.getCode()));
		if(uploadStrart.isPresent())
		{						
			mainMessages.remove(uploadStrart.get());
			if(uploadEnd.isPresent()==false)
			{
			comment=new TIComment();
			comment.setMessageCode(uploadStrart.get().getMsgEnum().getCode());
			comment.setMessage(uploadStrart.get().getMessage());
			comment.setModifiedBy(MODIFY_USER);
			comment.setModifiedTime(uploadStrart.get().getTime());	
			comment.setUploadTourInfoFile(operationStatus.getUploadTourInfoFile());
			operationStatus.getUploadTourInfoFile().getComments().add(comment);
			}
			else
			{				
				String startOperation=DateHelper.dateTimeToString(operationStatus.getStartOperation());
				String endOperation=DateHelper.dateTimeToString(operationStatus.getEndOperation());
				String durationOperation=DateHelper.calculateTime(operationStatus.getStartOperation(), operationStatus.getEndOperation(), CalculateTimePattern.HMS);
				
				mainMessages.remove(uploadEnd.get());				
				comment=new TIComment();
				comment.setMessageCode(TourInfoMessages.UPLOAD_STATUS.getCode());
				comment.setMessage(TourInfoMessages.getMessage(TourInfoMessages.UPLOAD_STATUS, startOperation,endOperation,durationOperation));
				comment.setModifiedBy(MODIFY_USER);
				comment.setModifiedTime(uploadStrart.get().getTime());	
				comment.setUploadTourInfoFile(operationStatus.getUploadTourInfoFile());
				operationStatus.getUploadTourInfoFile().getComments().add(comment);
				
				
				StringBuilder content=new StringBuilder();
				content.append("Zip file name:").append(operationStatus.getUploadTourInfoFile().getName()).append(END_OF_LINE);
				content.append("Upload by:").append(operationStatus.getWhoUploded().getUsername()).append(END_OF_LINE);
				content.append("Upload source:").append(operationStatus.getUploadTourInfoFile().getSourceUploadFile()).append(END_OF_LINE);
				content.append("Upload start:").append(startOperation).append(END_OF_LINE);
				content.append("Upload end:").append(endOperation).append(END_OF_LINE);
				content.append("Time elapsed:").append(durationOperation).append(END_OF_LINE);
				content.append("Registred files in zip archive:").append(operationStatus.getCountXml()).append(END_OF_LINE);
				content.append("Selected insert rows to Content Repository:").append(operationStatus.getInsertCount()).append(END_OF_LINE);
				content.append("Selected update rows in Content Repository:").append(operationStatus.getUpdateCount()).append(END_OF_LINE);
//				content.append("Deleted rows from Content Repository:").append(operationStatus.getDeleteCount()).append(END_OF_LINE);
				content.append("Rejected files reason that same MD5:").append(operationStatus.getRejectedXmlMd5()).append(END_OF_LINE);				
				content.append(END_OF_LINE);
				
				
				content.append("Errors:").append(END_OF_LINE);								
				for (TIMessage message : Iterables.filter(mainMessages, new MessageErrorFinder())) {					
//					comment.setContent(message.getMessage());	
					content.append(TAB).append(message.getMsgEnum().getCode()).append(" ").append(message.getMessage()).append(END_OF_LINE);
					if(message.getE()!=null)
						content.append(TAB).append(TAB).append(Throwables.getStackTraceAsString(message.getE())).append(END_OF_LINE);
					content.append(END_OF_LINE);
				}
								
				content.append("Additional information:").append(END_OF_LINE);								
				for (TIMessage message : Iterables.filter(mainMessages, Predicates.not(new MessageErrorFinder()))) {
					content.append(TAB).append(message.getMsgEnum().getCode()).append(" ").append(message.getMessage()).append(END_OF_LINE);					
				}
				comment.setContent(content.toString());
											
				// First comment 
				TourInfoMessages statusMsg=operationStatus.getStatus()==UploadTourInfoFileStatus.FAIL ? TourInfoMessages.FAIL_INFO :TourInfoMessages.SUCCESS_INFO;							
				comment=new TIComment();
				comment.setMessageCode(statusMsg.getCode());
				comment.setMessage(TourInfoMessages.getMessage(statusMsg, operationStatus.getUploadTourInfoFile().getName(),operationStatus.getCountXml()));
				comment.setModifiedBy(MODIFY_USER);
				comment.setModifiedTime(uploadStrart.get().getTime());	
				comment.setUploadTourInfoFile(operationStatus.getUploadTourInfoFile());
				operationStatus.getUploadTourInfoFile().getComments().add(comment);
			}
		}
	}
	
	public Multimap<String, TIMessage> getFileMessages() {
		return fileMessages;
	}

	public List<TIMessage> getMainMessages() {
		return mainMessages;
	}
	
		
	class MessageErrorFinder implements Predicate<TIMessage>
	{
		@Override
		public boolean apply(TIMessage input) {
			return input.getMsgEnum().getCode().contains("ERR");
		}
	}
}


