package com.ttc.ch2.ui.moduls.messages;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.zkoss.bind.BindContext;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zul.event.PagingEvent;

import com.google.common.collect.Lists;
import com.ttc.ch2.bl.departure.common.TropicSynchronizeMessages;
import com.ttc.ch2.bl.message.CommentsService;
import com.ttc.ch2.bl.upload.common.TourInfoMessages;
import com.ttc.ch2.dao.comment.TypeComment;
import com.ttc.ch2.domain.common.QueryCondition;
import com.ttc.ch2.domain.common.SortCondition;
import com.ttc.ch2.domain.common.SortCondition.Direction;
import com.ttc.ch2.ui.common.SessionHelper;
import com.ttc.ch2.ui.common.security.UserContext.UserContextStaticName;
import com.ttc.ch2.ui.moduls.details.common.DefaultCommentDecorator;
import com.ttc.ch2.ui.moduls.details.common.TdBrandDetailsDecorator;
import com.ttc.ch2.ui.moduls.details.common.TdMainCommentDecorator;
import com.ttc.ch2.ui.moduls.details.common.TiAdditionalDetailsDecorator;
import com.ttc.ch2.ui.moduls.details.common.TiErrorsDetailsDecorator;
import com.ttc.ch2.ui.moduls.details.common.TiMainCommentDecorator;
import com.ttc.ch2.ui.moduls.messages.common.CommentDec;
import com.ttc.ch2.ui.moduls.messages.common.CommentsForm;
import com.ttc.ch2.ui.moduls.messages.common.PagingCommentsModel;


@org.springframework.stereotype.Component("CommentsCntrl")
@Scope("request")
public class CommentsCntrl {

	private static final Logger logger = LoggerFactory.getLogger(CommentsCntrl.class);
	
	@Inject
	private CommentsService service;
	
	private CommentsForm commentsForm;
	
	private CommentDec filter;
	private CommentDec selectedValue;
	
	private PagingCommentsModel listModel;		
	@Init
	public void init() {
		logger.trace("CommentsCntrl:init-start");
		
		if(SessionHelper.getAttributeFromUserContext(UserContextStaticName.FORM_LIST_COMMENTS)==null){
			commentsForm=new CommentsForm();
			commentsForm.setChooseType(TypeComment.getValues());
			commentsForm.setSelectedType(TypeComment.TIComment);
			filter=new CommentDec();
			filter.setTypeComment(TypeComment.TIComment);	
			SessionHelper.putAttributeToUserContext(UserContextStaticName.FORM_LIST_COMMENTS, filter);
		}
		else{
			filter=(CommentDec)SessionHelper.getAttributeFromUserContext(UserContextStaticName.FORM_LIST_COMMENTS);
			commentsForm=new CommentsForm();
			commentsForm.setChooseType(TypeComment.getValues());
			commentsForm.setSelectedType(filter.getTypeComment());
			commentsForm.setCode(filter.getGrCode());
			commentsForm.setMessage(filter.getComment().getMessage());
			commentsForm.setModifiedTime(filter.getComment().getModifiedTime());
		}
		
		QueryCondition condition=new QueryCondition(0, PagingCommentsModel.PAGE_SIZE,new SortCondition("modifiedTime", Direction.DESC));		
		SessionHelper.putAttributeToUserContext(UserContextStaticName.SORT_CONDITION_COMMENTS, condition);
		listModel=new PagingCommentsModel(service,condition,filter);
		logger.trace("CommentsCntrl:init-end");
	}
	
	
	@Command("onPagingList")
	@NotifyChange("listModel")
	public void onPagingList(BindContext ctx) {		
		logger.trace("CommentsCntrl:onPagingList-start");
		PagingEvent event = (PagingEvent) ctx.getTriggerEvent();	
		QueryCondition condition=(QueryCondition) SessionHelper.getAttributeFromUserContext(UserContextStaticName.SORT_CONDITION_COMMENTS);
		listModel=new PagingCommentsModel(service,condition,filter,event.getActivePage());
		logger.trace("CommentsCntrl:onPagingList-end");
	}
	
	@Command
    @NotifyChange({"listModel"})
    public void changeFilter() {
		logger.trace("CommentsCntrl:changeFilter-start");
		filter.setTypeComment(commentsForm.getSelectedType());
		filter.setGrCode(commentsForm.getCode());
//		filter.getComment().setMessageCode(commentsForm.getCode());
		filter.getComment().setMessage(commentsForm.getMessage());
		filter.getComment().setModifiedTime(commentsForm.getModifiedTime());
		SessionHelper.putAttributeToUserContext(UserContextStaticName.FORM_LIST_COMMENTS, filter);		
		QueryCondition condition=(QueryCondition) SessionHelper.getAttributeFromUserContext(UserContextStaticName.SORT_CONDITION_COMMENTS);
    	listModel=new PagingCommentsModel(service,condition,filter,0);
    	logger.trace("CommentsCntrl:changeFilter-end");
    }
	
	
	public String calculateDecorator(CommentDec comment){

		String result=DefaultCommentDecorator.name;
		try{
			if(TypeComment.TIComment==comment.getTypeComment()){
				if(TourInfoMessages.UPLOAD_STATUS==TourInfoMessages.getByCode(comment.getComment().getMessageCode())){
					result=TiMainCommentDecorator.name;
				}
				else if(TourInfoMessages.UPLOAD_STATUS_INFO==TourInfoMessages.getByCode(comment.getComment().getMessageCode())){
					result=TiAdditionalDetailsDecorator.name;
				}		
				else if(TourInfoMessages.UPLOAD_STATUS_ERROR==TourInfoMessages.getByCode(comment.getComment().getMessageCode())){
					result=TiErrorsDetailsDecorator.name;
				}		
			  }
			else if(TypeComment.TDComment==comment.getTypeComment()){
				if(TropicSynchronizeMessages.SYNCHRONIZE_STATUS==TropicSynchronizeMessages.getByCode(comment.getComment().getMessageCode())){
					result=TdMainCommentDecorator.name;
				}
				else if(TropicSynchronizeMessages.BRAND_STATUS==TropicSynchronizeMessages.getByCode(comment.getComment().getMessageCode())){
					result=TdBrandDetailsDecorator.name;
				}				
			}
		} catch (Exception e) {
			logger.error("",e);
		}
		return result;
	}


	public CommentDec getFilter() {
		return filter;
	}


	public void setFilter(CommentDec filter) {
		this.filter = filter;
	}


	public CommentDec getSelectedValue() {
		return selectedValue;
	}


	public void setSelectedValue(CommentDec selectedValue) {
		this.selectedValue = selectedValue;
	}


	public PagingCommentsModel getListModel() {
		return listModel;
	}


	public void setListModel(PagingCommentsModel listModel) {
		this.listModel = listModel;
	}

	public CommentsForm getCommentsForm() {
		return commentsForm;
	}


	public void setCommentsForm(CommentsForm commentsForm) {
		this.commentsForm = commentsForm;
	}
}
