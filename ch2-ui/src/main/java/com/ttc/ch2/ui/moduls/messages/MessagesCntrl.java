package com.ttc.ch2.ui.moduls.messages;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.zkoss.bind.BindContext;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.util.media.AMedia;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Iframe;
import org.zkoss.zul.event.PagingEvent;

import com.ttc.ch2.bl.message.MessagesService;
import com.ttc.ch2.domain.common.QueryCondition;
import com.ttc.ch2.domain.common.SortCondition;
import com.ttc.ch2.domain.common.SortCondition.Direction;
import com.ttc.ch2.domain.messages.EmailHistory;
import com.ttc.ch2.ui.common.SessionHelper;
import com.ttc.ch2.ui.common.security.UserContext.UserContextStaticName;
import com.ttc.ch2.ui.moduls.messages.common.MessagesForm;
import com.ttc.ch2.ui.moduls.messages.common.PagingEmailHistoryModel;


@org.springframework.stereotype.Component("MessagesCntrl")
@Scope("request")
public class MessagesCntrl {

	private static final Logger logger = LoggerFactory.getLogger(MessagesCntrl.class);
	
	@Inject
	private MessagesService service;
	
	
	private MessagesForm form;
	private EmailHistory filter;
	private EmailHistory selectedValue;
	private PagingEmailHistoryModel listModel;
	private boolean showDetail;
	private String groupBoxTitle;
	
	@Wire("#mailContent")
	private Iframe mailIframe=null;
	
	@Init
	public void init() {
		logger.trace("Messages:init-start");
		filter=new EmailHistory();	
		form=new MessagesForm();
		QueryCondition condition=new QueryCondition(0, PagingEmailHistoryModel.PAGE_SIZE,new SortCondition("messageDeliveryTime", Direction.DESC));		
		SessionHelper.putAttributeToUserContext(UserContextStaticName.SORT_CONDITION_MESSAGES, condition);
		listModel=new PagingEmailHistoryModel(service,condition,filter);	
		logger.trace("Messages:init-end");	
	}
	
	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);	
	}
	
	
	@Command
	@NotifyChange({"showDetail","selectedValue","commentsListModel","groupBoxTitle"})
	public void onShowDetail(@BindingParam("element") EmailHistory selected) {
		showDetail=true;		
		selectedValue=selected;
		mailIframe.setContent(new EMailMedia(selectedValue.getMessage()));				
		groupBoxTitle=Labels.getLabel("messages.message_list.details_title")+" "+selected.getSubject();
	}
	
	
	@Command("onPagingList")
	@NotifyChange("listModel")
	public void onPagingList(BindContext ctx) {	
		logger.trace("UploadTourInfo:onPagingList-start");
		PagingEvent event = (PagingEvent) ctx.getTriggerEvent();	
		QueryCondition condition=(QueryCondition) SessionHelper.getAttributeFromUserContext(UserContextStaticName.SORT_CONDITION_MESSAGES);
		listModel=new PagingEmailHistoryModel(service,condition,filter,event.getActivePage());
		logger.trace("UploadTourInfo:onPagingList-end");
	}
	
	@Command
    @NotifyChange({"listModel"})
    public void changeFilter() {
		logger.trace("MessagesCntrl:changeFilter-start");
		filter.setProccessName(form.getProcessNameFromString());
		filter.setSubject(form.getSubject());
		filter.setMessageDeliveryTime(form.getDate());
		filter.setStatus(form.getStatusFromString());
		QueryCondition condition=(QueryCondition) SessionHelper.getAttributeFromUserContext(UserContextStaticName.SORT_CONDITION_MESSAGES);
    	listModel=new PagingEmailHistoryModel(service,condition,filter,0);
    	logger.trace("MessagesCntrl:changeFilter-end");
    }
	
	public EmailHistory getFilter() {
		return filter;
	}


	public void setFilter(EmailHistory filter) {
		this.filter = filter;
	}


	public EmailHistory getSelectedValue() {
		return selectedValue;
	}


	public void setSelectedValue(EmailHistory selectedValue) {
		this.selectedValue = selectedValue;
	}


	public PagingEmailHistoryModel getListModel() {
		return listModel;
	}


	public void setListModel(PagingEmailHistoryModel listModel) {
		this.listModel = listModel;
	}


	public boolean isShowDetail() {
		return showDetail;
	}


	public void setShowDetail(boolean showDetail) {
		this.showDetail = showDetail;
	}


	public String getGroupBoxTitle() {
		return groupBoxTitle;
	}


	public void setGroupBoxTitle(String groupBoxTitle) {
		this.groupBoxTitle = groupBoxTitle;
	}


	public MessagesForm getForm() {
		return form;
	}


	public void setForm(MessagesForm form) {
		this.form = form;
	}
	
	
	class EMailMedia extends AMedia{

		public EMailMedia(String data) {
			super("Content Mail", "html", "text/html", data);
		}
		
	}
}
