package com.ttc.ch2.ui.moduls.user;


import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.ListModelList;

import com.ttc.ch2.bl.user.UserService;
import com.ttc.ch2.domain.user.UserCCAPI;
import com.ttc.ch2.ui.common.config.Ch2URIs;
import com.ttc.ch2.ui.common.exceptions.CH2Exception;
import com.ttc.ch2.ui.moduls.user.common.CcapiDetailsDecorator;
import com.ttc.ch2.ui.moduls.user.common.UserCcapiForm;
import com.ttc.common.params.ParamsUtils;

@Component("userCcapiWizDel")
@Scope("request")
public class UserCcapiWizDel {
	
	private static final Logger logger=LoggerFactory.getLogger(UserCcapiWizDel.class);
			
	private UserCcapiForm form;	
	private String titleSuffix;
	private ListModelList<CcapiDetailsDecorator> detailsModel;
	private String groupBoxTitle=null;
	private UserCCAPI editOperator=null;
		
	@Inject
	private UserService userService;
		
	
	@Init
	public void init() {		
		logger.trace("UserCcapiWizDel:init-start");				
		if (StringUtils.isBlank(Executions.getCurrent().getParameter("param"))) {
			throw new CH2Exception(Labels.getLabel("common.cntrl.params.notexist",new Object[]{this.getClass().getSimpleName()}));
		}		
		Map<String,String> params = ParamsUtils.getInstance().decodeAllParam(Executions.getCurrent().getParameter("param"));		
		String param1= params.get("id");				
		initDelMode(param1);

		logger.trace("UserCcapiWizDel:init-end");
	}
	
	
	private void initDelMode(String id)
	{
		logger.trace("UserCcapiWizDel:initDelMode-start");
		UserCCAPI o = userService.findUserCCAPI(Long.parseLong(id));
		titleSuffix=Labels.getLabel("users.wiz.del.account");
		form=new UserCcapiForm();
					
		form.setName(o.getUsername());		
		form.setEmail(o.getEmail());
		form.setEnabled(o.getEnabled());
		form.setToken(o.getToken());		
		form.setAdress(o.getAddress());
		
		detailsModel=new ListModelList<CcapiDetailsDecorator>(CcapiDetailsDecorator.buildList(o.getCcapiAuthorities()));	
		editOperator = o;
		logger.trace("UserCcapiWizDel:initDelMode-end");
	}
	
	@Command
	public void onSave(){
		logger.trace("UserCcapiWizDel:onSave-start");
		userService.deleteUserCCAPI(editOperator.getId());
		Executions.sendRedirect(Ch2URIs.OPERATOR_CCAPI_LIST.getPath());
		logger.trace("UserCcapiWizDel:onSave-end");
	}

	
	

	public UserCcapiForm getForm() {
		return form;
	}

	public void setForm(UserCcapiForm form) {
		this.form = form;
	}


	public ListModelList<CcapiDetailsDecorator> getDetailsModel() {
		return detailsModel;
	}


	public void setDetailsModel(ListModelList<CcapiDetailsDecorator> detailsModel) {
		this.detailsModel = detailsModel;
	}


	public String getGroupBoxTitle() {
		return groupBoxTitle;
	}


	public void setGroupBoxTitle(String groupBoxTitle) {
		this.groupBoxTitle = groupBoxTitle;
	}


	public String getTitleSuffix() {
		return titleSuffix;
	}


	public void setTitleSuffix(String titleSuffix) {
		this.titleSuffix = titleSuffix;
	}
}
