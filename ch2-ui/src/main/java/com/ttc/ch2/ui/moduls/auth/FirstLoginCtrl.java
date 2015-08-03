package com.ttc.ch2.ui.moduls.auth;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.Messagebox;

import com.ttc.ch2.bl.user.UserService;
import com.ttc.ch2.bl.user.UserServiceException;
import com.ttc.ch2.common.SecurityHelper;
import com.ttc.ch2.domain.auth.Role;
import com.ttc.ch2.domain.auth.UserGuiDetails;
import com.ttc.ch2.domain.user.UserGui;
import com.ttc.ch2.ui.common.config.Ch2URIs;
import com.ttc.ch2.ui.common.config.JspCh2URIs;


@Component("FirstLoginCtrl")
@Scope("request")
public class FirstLoginCtrl {
	
	private static final Logger logger = LoggerFactory.getLogger(FirstLoginCtrl.class);
		
	@Inject
	private UserService userService;
	
	private String groupBoxTitle;
	private FirstLoginForm form;

	
	@Init
	public void init() {
		logger.trace("FirstLoginCtrl:init-start");
		if(SecurityHelper.isUserGui()==false){
			Executions.sendRedirect(JspCh2URIs.LOGIN.getPath());
			return;
		}
		UserGui user=SecurityHelper.getUserGuiPrincipal().getUserDb();
		
		this.form=new FirstLoginForm();
		this.form.setUser(user);		
		this.groupBoxTitle=Labels.getLabel("auth.firstlogin.userName")+":"+	user.getUsername();	
		logger.trace("FirstLoginCtrl:init-end");
	}
	
	
	@Command
	@NotifyChange({})
	public void onSave(){	
		logger.trace("FirstLoginCtrl:onSave-start");
		
		UserGui user=SecurityHelper.getUserGuiPrincipal().getUserDb();
		if(user.isLdapAccount()){			
			Messagebox.show(Labels.getLabel("auth.firstlogin.validation.ldap"), Labels.getLabel("auth.firstlogin.validation_title_box"), Messagebox.OK, Messagebox.ERROR);
			return;
		}

		try{
		userService.chgPassword(form.getPasswordOld(),form.getPasswordNew(),form.getUser());
		SecurityContextHolder.clearContext();
		} catch (UserServiceException e) {
			Messagebox.show(e.getMessage(), Labels.getLabel("auth.firstlogin.validation_title_box"), Messagebox.OK, Messagebox.ERROR);
			return;
		}
		Executions.sendRedirect(JspCh2URIs.LOGIN.getPath());
		logger.trace("FirstLoginCtrl:onSave-end");
	}

	
	@Command
	@NotifyChange({})
	public void onCancel(){			
		logger.trace("FirstLoginCtrl:onCancel-start");
		UserGuiDetails principal=SecurityHelper.getUserGuiPrincipal();				
		 if(principal.getUserDb().getFirstLog()==true)
			 Executions.sendRedirect(JspCh2URIs.LOGIN.getPath());		         
         else if(SecurityHelper.hasAuthority(Role.ADMINISTRATOR))
        	 Executions.sendRedirect(Ch2URIs.AUDIT.getPath());
         else if(SecurityHelper.hasAuthority(Role.BRAND))
        	 Executions.sendRedirect(Ch2URIs.TOUR_MATCH_STATUS_HTML.getPath());
		 logger.trace("FirstLoginCtrl:enclosing_method-end");
	}

	public FirstLoginForm getForm() {
		return form;
	}


	public void setForm(FirstLoginForm form) {
		this.form = form;
	}


	public String getGroupBoxTitle() {
		return groupBoxTitle;
	}


	public void setGroupBoxTitle(String groupBoxTitle) {
		this.groupBoxTitle = groupBoxTitle;
	}




}
