package com.ttc.ch2.ui.moduls.details;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.zkoss.bind.annotation.Init;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Executions;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.ttc.ch2.bl.message.CommentsService;
import com.ttc.ch2.dao.comment.TypeComment;
import com.ttc.ch2.domain.comment.Comment;
import com.ttc.ch2.ui.common.config.Ch2URIs;
import com.ttc.ch2.ui.moduls.details.common.CommentContentDecorator;
import com.ttc.ch2.ui.moduls.details.common.DefaultCommentDecorator;
import com.ttc.ch2.ui.moduls.details.common.TdBrandDetailsDecorator;
import com.ttc.ch2.ui.moduls.details.common.TdMainCommentDecorator;
import com.ttc.ch2.ui.moduls.details.common.TiAdditionalDetailsDecorator;
import com.ttc.ch2.ui.moduls.details.common.TiErrorsDetailsDecorator;
import com.ttc.ch2.ui.moduls.details.common.TiMainCommentDecorator;
import com.ttc.common.params.ParamsUtils;

@Component("CommentDetails")
@Scope("request")
public class CommentDetails {

	private static final Logger logger = LoggerFactory.getLogger(CommentDetails.class);
	
	private Map<String,CommentContentDecorator> decorators;
	

	private static final int MAX_LENGHT=1000;
	
	@Inject
	private CommentsService service;
	
	private Comment comment;
	private String panelTitle;
	private boolean showContent;
	private boolean showError;
	private String returnPath;
	
	
	private List<String> parts=Lists.newArrayList();
	private int activePage=0;
	
	@PostConstruct
	private void springInit(){
		Map<String,CommentContentDecorator> localDecorators=Maps.newHashMap();
		localDecorators.put(DefaultCommentDecorator.name, new DefaultCommentDecorator());
		localDecorators.put(TiMainCommentDecorator.name, new TiMainCommentDecorator());			
		localDecorators.put(TiAdditionalDetailsDecorator.name, new TiAdditionalDetailsDecorator());			
		localDecorators.put(TiErrorsDetailsDecorator.name, new TiErrorsDetailsDecorator());			
		localDecorators.put(TdMainCommentDecorator.name, new TdMainCommentDecorator());			
		localDecorators.put(TdBrandDetailsDecorator.name, new TdBrandDetailsDecorator());			
		decorators=localDecorators;
	}
	
	@Init
	public void init(){
		logger.trace("CommentDetails:init-start");
		Map<String,String> params=Maps.newHashMap();
		if (StringUtils.isNotBlank(Executions.getCurrent().getParameter("param"))) {
			params = ParamsUtils.getInstance().decodeAllParam(Executions.getCurrent().getParameter("param"));
		}
		String paramId= params.get("id");		
		String paramType= params.get("type");
		String paramRpath= params.get("rPath");
		String paramRid= params.get("rId");
		String commentDecorator=params.get("rComDec");
		Preconditions.checkArgument(StringUtils.isNotBlank(paramId),"Request need param id");
		Preconditions.checkArgument(StringUtils.isNotBlank(paramId),"Request need param type");
		Preconditions.checkArgument(StringUtils.isNotBlank(paramRpath),"Request need param type");
		calculateReturnPath(paramRpath, paramRid);
		
		comment=service.getCommentById(Long.valueOf(paramId), TypeComment.valueOf(paramType));
		if (StringUtils.isNotBlank(comment.getContent())) {			
			String content="";
			if(StringUtils.isNotEmpty(commentDecorator) && decorators.containsKey(commentDecorator)){
				content=decorators.get(commentDecorator).decorateContent(comment);
			}
			else{
				content=decorators.get(DefaultCommentDecorator.name).decorateContent(comment);
			}		
//			parts=Lists.newArrayList(Splitter.fixedLength(MAX_LENGHT).split(content));			
//			comment.setContent(parts.get(activePage));
			comment.setContent(content);
			showContent=true;
		}
		
		if(StringUtils.isNotBlank(comment.getStackTrace()))
		{
			comment.setStackTrace(comment.getStackTrace().replace("\n", "<br/>"));
			showError=true;
		}
		panelTitle=Labels.getLabel("messages.comments_list.col.details")+" - "+comment.getMessage();
		logger.trace("CommentDetails:init-end");
	}
	
	
	private void calculateReturnPath(String paramRpath,String paramRid)
	{
		String queryPath="";
		if(StringUtils.isNotBlank(paramRid)){
		 queryPath="?param="+ParamsUtils.encodeOneParam("id", paramRid);
		}		
		returnPath=Ch2URIs.valueOf(paramRpath).getPath()+queryPath;
	}
	
//	@Command("onPagingList")
//	@NotifyChange({"comment","parts"})
//	public void onPagingList(BindContext ctx) {				
//		PagingEvent event = (PagingEvent) ctx.getTriggerEvent();			    	
//		String content=comment.getContent().replace("\n", "<br/>");			
//		parts=Lists.newArrayList(Splitter.fixedLength(MAX_LENGHT).split(content));			
//		activePage=event.getActivePage();		
//		comment.setContent(parts.get(activePage));				
//	}
	
		

	public Comment getComment() {
		return comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}

	public String getPanelTitle() {
		return panelTitle;
	}

	public void setPanelTitle(String panelTitle) {
		this.panelTitle = panelTitle;
	}

	public boolean isShowContent() {
		return showContent;
	}

	public void setShowContent(boolean showContent) {
		this.showContent = showContent;
	}

	public boolean isShowError() {
		return showError;
	}

	public void setShowError(boolean showError) {
		this.showError = showError;
	}


	public String getReturnPath() {
		return returnPath;
	}


	public void setReturnPath(String returnPath) {
		this.returnPath = returnPath;
	}

	
}
