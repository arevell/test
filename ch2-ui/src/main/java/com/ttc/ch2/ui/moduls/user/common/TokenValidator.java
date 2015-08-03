package com.ttc.ch2.ui.moduls.user.common;

import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpRequest;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.zkoss.bind.Property;
import org.zkoss.bind.ValidationContext;
import org.zkoss.bind.validator.AbstractValidator;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Executions;

import com.google.common.collect.Lists;
import com.ttc.ch2.bl.token.UniqueTokenServiceException;
import com.ttc.ch2.dao.security.UserCCAPIDAO;
import com.ttc.ch2.domain.user.UserCCAPI;
import com.ttc.ch2.ui.common.SessionHelper;

public class TokenValidator extends AbstractValidator{

	private UserCCAPIDAO userCCAPIDAO;
	
	public TokenValidator()
	{		
		userCCAPIDAO =WebApplicationContextUtils.getWebApplicationContext(SessionHelper.getSession().getServletContext()).getBean(UserCCAPIDAO.class);
	}
	
	@Override
	public void validate(ValidationContext ctx) {						
        Map<String,Property> beanProps = ctx.getProperties(ctx.getProperty().getBase());
//        UserCcapiWiz form=(UserCcapiWiz) ctx.getProperty().getBase();
//        SimpleForm simpleForm =(SimpleForm) beanProps.get(".").getValue();  		
		validateToken(ctx,(String)beanProps.get("token").getValue());
	}

	private void validateToken(ValidationContext ctx, String valueToken)
	{			
		if(StringUtils.isNotEmpty(valueToken)){
			if(valueToken.length()>40){
				this.addInvalidMessage(ctx, "token",Labels.getLabel("common.field.lenght.tolong"));
			}
			
			if(Base64.isArrayByteBase64(valueToken.getBytes())==false){
				this.addInvalidMessage(ctx, "token",Labels.getLabel("users.operator_ccapi_wiz.validation.token"));
			}			
		}						
	}
	
	
	public List<String> validateToken(UserCCAPI user,String valueToken)
	{
		List<String> result=Lists.newArrayList();
		if (StringUtils.isEmpty(valueToken)) {
			result.add(Labels.getLabel("users.operator_ccapi_wiz.validation.tokennull"));
		}
		
		if (StringUtils.isBlank(user.getToken()) || user.getToken().equals(valueToken)==false) {
			UserCCAPI findedToken= userCCAPIDAO.findByToken(valueToken);
			if (findedToken != null) {
				result.add(Labels.getLabel("users.operator_ccapi_wiz.validation.token_exists"));
			}	
		}
		
		return result;
	}
}
