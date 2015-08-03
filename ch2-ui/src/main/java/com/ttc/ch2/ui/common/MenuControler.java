package com.ttc.ch2.ui.common;

import java.io.Serializable;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.zkoss.util.resource.Labels;

import com.ttc.ch2.common.DateHelper;
import com.ttc.ch2.common.SecurityHelper;
import com.ttc.ch2.domain.auth.Role;
import com.ttc.ch2.domain.user.UserGui;
import com.ttc.ch2.ui.common.security.UserContext;

@Component("MenuControler")
@Scope("session")
public class MenuControler implements Serializable{

	private static final long serialVersionUID = 5739487099652540552L;
	
	private boolean brandRole;
	private boolean adminRole;
	private boolean userNotLDAP;
	private String login;
	private String brand;
	
	private String currentDate;
	
	@Value("${common.enviroment}")
	private String enviroment; 	
	

	
	public MenuControler()
	{	
		UserGui u=(UserGui) SessionHelper.getAttributeFromUserContext(UserContext.UserContextStaticName.LOGED_USER);
		this.brandRole=SecurityHelper.hasAuthority(Role.BRAND);
		this.adminRole=SecurityHelper.hasAuthority(Role.ADMINISTRATOR);
		this.userNotLDAP=u.isLdapAccount()==false;
		this.login=u.getUsername();
		if(u.getBrands().iterator().hasNext())
		this.brand=u.getBrands().iterator().next().getBrandName();		
	}
	
	public boolean isBrandRole() {
		return brandRole;
	}
	
	public boolean isAdminRole() {
		return adminRole;
	}

	public String getLogin() {
		return login;
	}

	public String getBrand() {
		return brand;
	}

	public String getEnviroment() {
		return enviroment;
	}

	public String getCurrentDate() {
		return currentDate;
	}

	public boolean isUserNotLDAP() {
		return userNotLDAP;
	}

	public void setUserNotLDAP(boolean userNotLDAP) {
		this.userNotLDAP = userNotLDAP;
	}



}
