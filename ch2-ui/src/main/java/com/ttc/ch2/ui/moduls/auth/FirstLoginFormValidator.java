package com.ttc.ch2.ui.moduls.auth;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.DigestUtils;
import org.zkoss.bind.Property;
import org.zkoss.bind.SimpleForm;
import org.zkoss.bind.ValidationContext;
import org.zkoss.bind.validator.AbstractValidator;
import org.zkoss.util.resource.Labels;

import com.google.common.collect.Lists;
import com.ttc.ch2.bl.user.UserService;
import com.ttc.ch2.bl.user.UserServiceException;
import com.ttc.ch2.common.SecurityHelper;
import com.ttc.ch2.domain.user.User;
import com.ttc.ch2.domain.user.UserGui;

public class FirstLoginFormValidator extends AbstractValidator {
		
	@Override
	public void validate(ValidationContext ctx) {		
		FirstLoginForm form=(FirstLoginForm) ctx.getProperty().getBase();
        Map<String,Property> beanProps = ctx.getProperties(ctx.getProperty().getBase());
        SimpleForm simpleForm =(SimpleForm) beanProps.get(".").getValue();  
                        
        validateOldPassword(ctx, (String)beanProps.get("passwordOld").getValue());               
        validateNewPassword(ctx, (String)beanProps.get("passwordNew").getValue(),(String)beanProps.get("passwordOld").getValue());               
	}
	
	private void validateOldPassword(ValidationContext ctx, String value) {
		if (StringUtils.isEmpty(value)) {
				this.addInvalidMessage(ctx, "passwordOld",Labels.getLabel("common.field.required"));
		}	
		else if(value.length()>32){
				this.addInvalidMessage(ctx, "passwordOld",Labels.getLabel("common.field.lenght.tolong"));
		}						
		try {
			if(new String(DigestUtils.md5DigestAsHex(value.getBytes("UTF-8"))).equals(SecurityHelper.getUserGuiPrincipal().getUserDb().getPassword())==false){
				this.addInvalidMessage(ctx, "passwordOld",Labels.getLabel("auth.firstlogin.validation.incorrectPswd"));
			}
		}catch (UnsupportedEncodingException e) {
			this.addInvalidMessage(ctx, "passwordOld",Labels.getLabel("auth.firstlogin.validation.incorrectPswd"));
		}
	}
	
	private void validateNewPassword(ValidationContext ctx, String newPswd,String oldPswd) {
		if (StringUtils.isEmpty(newPswd)) {
			this.addInvalidMessage(ctx, "passwordNew",Labels.getLabel("common.field.required"));
		}
	
		if(newPswd.matches(UserService.passwordPattern)==false){
			this.addInvalidMessage(ctx, "passwordNew",Labels.getLabel("auth.firstlogin.validation.incorrectPswd"));
		}
		
		try {
			if(new String(DigestUtils.md5DigestAsHex(oldPswd.getBytes("UTF-8"))).equals(new String(DigestUtils.md5DigestAsHex(newPswd.getBytes("UTF-8"))))){
				this.addInvalidMessage(ctx, "passwordNew",Labels.getLabel("auth.firstlogin.validation.pswdthatsame"));
			}
		} catch (UnsupportedEncodingException e) {
				this.addInvalidMessage(ctx, "passwordNew",Labels.getLabel("auth.firstlogin.validation.pswdthatsame"));
		}
	}
	
	
}
