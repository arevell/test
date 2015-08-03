package com.ttc.ch2.ui.moduls.user.common;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.zkoss.bind.Property;
import org.zkoss.bind.SimpleForm;
import org.zkoss.bind.ValidationContext;
import org.zkoss.bind.validator.AbstractValidator;
import org.zkoss.util.resource.Labels;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.ttc.ch2.bl.user.UserService;
import com.ttc.ch2.common.predicates.FindGroupByNamePredicate;
import com.ttc.ch2.dao.security.UserGuiDAO;
import com.ttc.ch2.domain.auth.GroupNames;
import com.ttc.ch2.domain.user.User;
import com.ttc.ch2.domain.user.UserCCAPI;
import com.ttc.ch2.domain.user.UserGui;
import com.ttc.ch2.ui.common.SessionHelper;
import com.ttc.ch2.ui.common.validators.MailValidator;

public class UserGuiFormValidator extends AbstractValidator {
		
	
	
	private UserService userService;
	
	
	
	public UserGuiFormValidator()
	{		
		userService =WebApplicationContextUtils.getWebApplicationContext(SessionHelper.getSession().getServletContext()).getBean(UserService.class);
	}
	
	@Override
	public void validate(ValidationContext ctx) 
	{		
		UserGuiForm form=(UserGuiForm) ctx.getProperty().getBase();
        Map<String,Property> beanProps = ctx.getProperties(ctx.getProperty().getBase());        
        SimpleForm simpleForm =(SimpleForm) beanProps.get(".").getValue();                
        validateLogin(ctx, (String)beanProps.get("login").getValue());
        if(simpleForm.getField("ldapUsed")!=null)
        {
	     if((boolean) simpleForm.getField("ldapUsed")==false) {	    	 
		    	String password=(String)beanProps.get("password").getValue();
		        if((form.isPasswordExist()==false || (form.isPasswordExist()==true &&
		        		StringUtils.isNotEmpty(password) && !form.getOldPassword().equals(password)))){ 	        
		        	validatePassword(ctx, password);
		        }
	        }
        }
        else {
        	throw new UnsupportedOperationException(Labels.getLabel("common.field.notregistred","ldapUsed"));
        }
        validateGroup(ctx, (String)beanProps.get("selectedGroup").getValue());
        validateMail(ctx, (String)beanProps.get("email").getValue());
	}
	
	private void validateLogin(ValidationContext ctx, String value)
	{
		if (StringUtils.isEmpty(value)) {
			this.addInvalidMessage(ctx, "login",Labels.getLabel("common.field.required"));
		}
		else if(value.length()>40){
			this.addInvalidMessage(ctx, "login",Labels.getLabel("common.field.lenght.tolong"));
		}		
		else if(value.matches("^[^\\s]+$")==false){
			this.addInvalidMessage(ctx, "name",Labels.getLabel("users.validation.invalidLogin"));
		}	
	}
	
	private void validatePassword(ValidationContext ctx, String value)
	{
		if (StringUtils.isEmpty(value)) {
			this.addInvalidMessage(ctx, "password",Labels.getLabel("common.field.required"));
		}
		else if(value.length()>32){
			this.addInvalidMessage(ctx, "password",Labels.getLabel("common.field.lenght.tolong"));
		}	
		else if(value.matches(UserService.passwordPattern)==false)
			this.addInvalidMessage(ctx, "password",Labels.getLabel("users.usergui_wiz.validation.bed_password"));
		
	}
	
	private void validateGroup(ValidationContext ctx, String value)
	{		
		if(StringUtils.isEmpty(value)){
				this.addInvalidMessage(ctx, "selectedGroup",Labels.getLabel("common.field.required"));
		}
		else
		{
			try
			 {  
				GroupNames groupName=GroupNames.valueOf(value);			 
				if(!(groupName==GroupNames.ADMINS || groupName==GroupNames.BRANDS))
				{
					this.addInvalidMessage(ctx, "selectedGroup",Labels.getLabel("users.usergui_wiz.validation.group",new Object[]{groupName.toString()}));	
				}
			 }
			catch(Exception e)
			{
				this.addInvalidMessage(ctx, "selectedGroup",e.getMessage());
			}
		}
		
	}
	
	private void validateMail(ValidationContext ctx, String value)
	{		
		if(StringUtils.isNotEmpty(value)){
			if(value.length()>200){
				this.addInvalidMessage(ctx, "email",Labels.getLabel("common.field.lenght.tolong"));
			}			
					
			if(new MailValidator().validate(value)==false){
				this.addInvalidMessage(ctx, "email",Labels.getLabel("common.field.email.incorrect"));
			}
		}
		
	}
	public List<String> validateBrand(UserGui user)
	{
		List<String> result=Lists.newArrayList();	
		if (Iterables.tryFind(user.getGroups(),new FindGroupByNamePredicate(GroupNames.BRANDS.toString())).isPresent() && user.getBrands().size()==0) {
			result.add(Labels.getLabel("users.usergui_wiz.validation.brandEmpty"));
		}
		
		return result;
	}
	
	public List<String> validateUser(UserGui user,String userName)
	{
		List<String> result=Lists.newArrayList();
		if (StringUtils.isBlank(user.getUsername()) || user.getUsername().equals(userName)==false) {			
			User findedUser= userService.findUserByName(userName);
			if (findedUser != null) {
				result.add(Labels.getLabel("users.validation.userExist"));
			}	
		}
		return result;
	}
	
}
