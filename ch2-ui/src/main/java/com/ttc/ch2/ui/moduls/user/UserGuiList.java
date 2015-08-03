package com.ttc.ch2.ui.moduls.user;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.zkoss.bind.BindContext;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zul.event.PagingEvent;

import com.ttc.ch2.dao.security.UserGuiDAO;
import com.ttc.ch2.domain.common.QueryCondition;
import com.ttc.ch2.domain.common.SortCondition;
import com.ttc.ch2.domain.common.SortCondition.Direction;
import com.ttc.ch2.domain.user.UserGui;
import com.ttc.ch2.ui.common.SessionHelper;
import com.ttc.ch2.ui.common.security.UserContext.UserContextStaticName;
import com.ttc.ch2.ui.common.zkcontrolers.AbstractEncryptedParamHandelComp;
import com.ttc.ch2.ui.moduls.user.common.PagingUserGuiModel;



@Component("userGuiList")
@Scope("request")
public class UserGuiList  extends AbstractEncryptedParamHandelComp  {
	
	private static final Logger logger=LoggerFactory.getLogger(UserGuiList.class);
		
	@Inject
	private UserGuiDAO userGuiDAO;
	
	private UserGui filter;
	private PagingUserGuiModel listModel;

	@Init(superclass=true)
	public void init() { // Initialize
		logger.trace("OperatorList:init-start");	
		filter=new UserGui();
		filter.setDelFlag(false);
		QueryCondition condition=new QueryCondition(0, PagingUserGuiModel.PAGE_SIZE,new SortCondition("username", Direction.ASC));		
		SessionHelper.putAttributeToUserContext(UserContextStaticName.SORT_CONDITION_USER, condition);
		listModel=new PagingUserGuiModel(userGuiDAO,condition,filter);			
		logger.trace("OperatorList:init-end");		
	}
		
    @Command
    @NotifyChange({"listModel"})
    public void changeFilter() {
    	logger.trace("OperatorList:changeFilter-start");
    	QueryCondition condition=(QueryCondition) SessionHelper.getAttributeFromUserContext(UserContextStaticName.SORT_CONDITION_USER);    	
    	listModel=new PagingUserGuiModel(userGuiDAO,condition,filter,0);   
    	logger.trace("OperatorList:changeFilter-end");
    }
    
    
	@Command("onPagingList")
	@NotifyChange("listModel")
	public void onPagingList(BindContext ctx) {		
		logger.trace("UploadTourInfo:onPagingList-start");
		PagingEvent event = (PagingEvent) ctx.getTriggerEvent();			
		QueryCondition condition=(QueryCondition) SessionHelper.getAttributeFromUserContext(UserContextStaticName.SORT_CONDITION_USER);    	
    	listModel=new PagingUserGuiModel(userGuiDAO,condition,filter,event.getActivePage());		
		logger.trace("UploadTourInfo:onPagingList-end");
	}
		
	

	public PagingUserGuiModel getListModel() {
		return listModel;
	}

	public void setListModel(PagingUserGuiModel listModel) {
		this.listModel = listModel;
	}

	public UserGui getFilter() {
		return filter;
	}

	public void setFilter(UserGui filter) {
		this.filter = filter;
	}
}
