package com.ttc.ch2.ui.moduls.auth;

import com.ttc.ch2.domain.user.UserGui;

public class FirstLoginForm {
	
	private UserGui user;
	private String passwordOld;
	private String passwordNew;
		
	public String getPasswordNew() {
		return passwordNew;
	}
	public void setPasswordNew(String passwordNew) {
		this.passwordNew = passwordNew;
	}
	public UserGui getUser() {
		return user;
	}
	public void setUser(UserGui user) {
		this.user = user;
	}
	public String getPasswordOld() {
		return passwordOld;
	}
	public void setPasswordOld(String passwordOld) {
		this.passwordOld = passwordOld;
	}

}
