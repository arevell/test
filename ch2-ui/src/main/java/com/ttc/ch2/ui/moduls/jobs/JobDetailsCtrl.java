package com.ttc.ch2.ui.moduls.jobs;

import javax.inject.Inject;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.zkoss.bind.annotation.Init;
import org.zkoss.util.resource.Labels;

import com.google.common.base.Preconditions;
import com.ttc.ch2.bl.departure.common.TropicSynchronizeMessages;
import com.ttc.ch2.common.DateHelper;
import com.ttc.ch2.domain.comment.QHComment;
import com.ttc.ch2.domain.comment.TDComment;
import com.ttc.ch2.domain.jobs.QuartzJobHistory;
import com.ttc.ch2.scheduler.service.QuartzJobCh2Service;
import com.ttc.ch2.ui.common.zkcontrolers.AbstractEncryptedParamHandelComp;
import com.ttc.ch2.ui.moduls.details.common.DefaultCommentDecorator;
import com.ttc.ch2.ui.moduls.details.common.TdBrandDetailsDecorator;
import com.ttc.ch2.ui.moduls.details.common.TdMainCommentDecorator;


@Component("JobDetailsCtrl")
@Scope("request")
public class JobDetailsCtrl extends AbstractEncryptedParamHandelComp {

	private static final Logger logger = LoggerFactory.getLogger(JobDetailsCtrl.class);
		
	@Inject
	private QuartzJobCh2Service quartzJobCh2Service;
	
	private boolean showQJComments;
	private boolean showTDHistory;
	private String panelTitle;
	private QuartzJobHistory jobHistory;
	
	@Init(superclass=true)
	public void init(){
		logger.trace("JobDetailsCtrl:init-start");		
		String paramId= params.get("id");		
		Preconditions.checkArgument(StringUtils.isNotBlank(paramId),"Request need param id");
		jobHistory=quartzJobCh2Service.getFullDataQuartzJobHistory(Long.parseLong(paramId));
		showQJComments=(jobHistory!=null && jobHistory.getComments()!=null) ?jobHistory.getComments().size()>0 : false;		
		showTDHistory=jobHistory.getTourDepartureHistory()!=null;
		panelTitle=Labels.getLabel("jobs.job_details.main_panel_title")+" "+DateHelper.dateTimeToString(jobHistory.getStartDate());
		logger.trace("CommentDetails:init-end");
	}
	
	
	public String calculateDecorator(TDComment comment){

		String result=DefaultCommentDecorator.name;
		try{						
				if(TropicSynchronizeMessages.SYNCHRONIZE_STATUS==TropicSynchronizeMessages.getByCode(comment.getMessageCode())){
					result=TdMainCommentDecorator.name;
				}
				else if(TropicSynchronizeMessages.BRAND_STATUS==TropicSynchronizeMessages.getByCode(comment.getMessageCode())){
					result=TdBrandDetailsDecorator.name;
				}							
		} catch (Exception e) {
			logger.error("",e);
		}
		return result;
	}
	
	public String decorateQHistoryComments(QHComment  comment){	
		if(StringUtils.isNotBlank(comment.getMessage())){
			DefaultCommentDecorator defaultDecorator=new DefaultCommentDecorator();
			String message=defaultDecorator.decorateContent(comment.getMessage());
			return message;
		}
		return "";
	}

	public QuartzJobHistory getJobHistory() {
		return jobHistory;
	}

	public void setJobHistory(QuartzJobHistory jobHistory) {
		this.jobHistory = jobHistory;
	}

	public String getPanelTitle() {
		return panelTitle;
	}

	public void setPanelTitle(String panelTitle) {
		this.panelTitle = panelTitle;
	}

	public boolean isShowTDHistory() {
		return showTDHistory;
	}

	public void setShowTDHistory(boolean showTDHistory) {
		this.showTDHistory = showTDHistory;
	}

	public boolean isShowQJComments() {
		return showQJComments;
	}

	public void setShowQJComments(boolean showQJComments) {
		this.showQJComments = showQJComments;
	}
}
