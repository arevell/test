package com.ttc.ch2.ui.moduls.user;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.zkoss.bind.BindContext;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.util.resource.Labels;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.event.PagingEvent;

import com.ttc.ch2.dao.security.UserCCAPIDAO;
import com.ttc.ch2.domain.common.QueryCondition;
import com.ttc.ch2.domain.common.SortCondition;
import com.ttc.ch2.domain.common.SortCondition.Direction;
import com.ttc.ch2.domain.user.UserCCAPI;
import com.ttc.ch2.ui.common.SessionHelper;
import com.ttc.ch2.ui.common.security.UserContext.UserContextStaticName;
import com.ttc.ch2.ui.common.zkcontrolers.AbstractEncryptedParamHandelComp;
import com.ttc.ch2.ui.moduls.user.common.CcapiDetailsDecorator;
import com.ttc.ch2.ui.moduls.user.common.PagingUserCCAPIModel;
import com.ttc.ch2.ui.moduls.user.common.PagingUserGuiModel;



@Component("userCcapiList")
@Scope("request")
public class UserCcapiList extends AbstractEncryptedParamHandelComp {
	
	private static final Logger logger=LoggerFactory.getLogger(UserCcapiList.class);
		
	@Inject
	private UserCCAPIDAO userDAO;
	
	private UserCCAPI filter;
	private UserCCAPI selectedValue;
	private PagingUserCCAPIModel listModel;
	
	private ListModelList<CcapiDetailsDecorator> detailsModel;
	
	private boolean showDetail=false;
	private String groupBoxTitle=null;

	@Init(superclass=true)
	public void init() { // Initialize
		logger.trace("UserCcapiList:init-start");
		filter=new UserCCAPI();	
		filter.setDelFlag(false);
		QueryCondition condition=new QueryCondition(0, PagingUserGuiModel.PAGE_SIZE,new SortCondition("username", Direction.ASC));		
		SessionHelper.putAttributeToUserContext(UserContextStaticName.SORT_CONDITION_USER, condition);
		listModel=new PagingUserCCAPIModel(userDAO,condition,filter);	
		detailsModel=new ListModelList<CcapiDetailsDecorator>();
		logger.trace("UserCcapiList:init-end");	
	}
		
    @Command
    @NotifyChange({"listModel"})
    public void changeFilter() {
    	logger.trace("UserCcapiList:changeFilter-start");
    	QueryCondition condition=(QueryCondition) SessionHelper.getAttributeFromUserContext(UserContextStaticName.SORT_CONDITION_USER);    	
    	listModel=new PagingUserCCAPIModel(userDAO,condition,filter,0);   
    	logger.trace("UserCcapiList:changeFilter-end");
    }
    
    
	@Command("onPagingList")
	@NotifyChange("listModel")
	public void onPagingList(BindContext ctx)
	{		
		logger.trace("UserCcapiList:onPagingList-start");
		PagingEvent event = (PagingEvent) ctx.getTriggerEvent();			
		QueryCondition condition=(QueryCondition) SessionHelper.getAttributeFromUserContext(UserContextStaticName.SORT_CONDITION_USER);    	
    	listModel=new PagingUserCCAPIModel(userDAO,condition,filter,event.getActivePage());		
		logger.trace("UserCcapiList:onPagingList-end");
	}
	
	@Command
	@NotifyChange({"showDetail","selectedValue","detailsModel","groupBoxTitle"})
	public void onShowDetail(@BindingParam("element") UserCCAPI selected)
	{
		showDetail=true;		
		selectedValue=selected;
				
		detailsModel=new ListModelList<CcapiDetailsDecorator>(CcapiDetailsDecorator.buildList(selectedValue.getCcapiAuthorities()));
		
		groupBoxTitle=Labels.getLabel("users.operator_ccapi_list.details.title")+": "+selected.getUsername();
	}

	public UserCCAPI getFilter() {
		return filter;
	}

	public void setFilter(UserCCAPI filter) {
		this.filter = filter;
	}

	public PagingUserCCAPIModel getListModel() {
		return listModel;
	}

	public void setListModel(PagingUserCCAPIModel listModel) {
		this.listModel = listModel;
	}

	public UserCCAPI getSelectedValue() {
		return selectedValue;
	}

	public void setSelectedValue(UserCCAPI selectedValue) {
		this.selectedValue = selectedValue;
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

	public ListModelList<CcapiDetailsDecorator> getDetailsModel() {
		return detailsModel;
	}

	public void setDetailsModel(ListModelList<CcapiDetailsDecorator> detailsModel) {
		this.detailsModel = detailsModel;
	}

	
}
