package com.ttc.ch2.ui.moduls.user.common;


public class UserGuiForm {
	
	private String login;
	private String password;
	private String oldPassword;
	private String email;
	private boolean ldapUsed;
	private boolean enabled;
	private boolean passwordExist;

	
	private String groupBrand;
	private String groupAdmin;
	private String selectedGroup;

		
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public boolean isLdapUsed() {
		return ldapUsed;
	}
	public void setLdapUsed(boolean ldapUsed) {
		this.ldapUsed = ldapUsed;
	}
	public String getGroupBrand() {
		return groupBrand;
	}
	public void setGroupBrand(String groupBrand) {
		this.groupBrand = groupBrand;
	}
	public String getGroupAdmin() {
		return groupAdmin;
	}
	public void setGroupAdmin(String groupAdmin) {
		this.groupAdmin = groupAdmin;
	}
	public String getSelectedGroup() {
		return selectedGroup;
	}
	public void setSelectedGroup(String selectedGroup) {
		this.selectedGroup = selectedGroup;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public boolean isPasswordExist() {
		return passwordExist;
	}
	public void setPasswordExist(boolean passwordExist) {
		this.passwordExist = passwordExist;
	}
	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
}
