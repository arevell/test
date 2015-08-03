package com.ttc.ch2.ui.moduls.user.common;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.zkoss.bind.Property;
import org.zkoss.bind.SimpleForm;
import org.zkoss.bind.ValidationContext;
import org.zkoss.bind.validator.AbstractValidator;
import org.zkoss.util.resource.Labels;

import com.google.common.base.Predicate;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.ttc.ch2.bl.user.UserService;
import com.ttc.ch2.common.FunctionType;
import com.ttc.ch2.domain.Brand;
import com.ttc.ch2.domain.SellingCompany;
import com.ttc.ch2.domain.auth.CCAPIAuthority;
import com.ttc.ch2.domain.user.User;
import com.ttc.ch2.domain.user.UserCCAPI;
import com.ttc.ch2.ui.common.SessionHelper;
import com.ttc.ch2.ui.common.validators.MailValidator;

public class UserCcapiFormValidator extends AbstractValidator {
		
	private UserService userService;
	
	public UserCcapiFormValidator()
	{		
		userService =WebApplicationContextUtils.getWebApplicationContext(SessionHelper.getSession().getServletContext()).getBean(UserService.class);
	}
	
	@Override
	public void validate(ValidationContext ctx) 
	{		
		UserCcapiForm form=(UserCcapiForm) ctx.getProperty().getBase();
        Map<String,Property> beanProps = ctx.getProperties(ctx.getProperty().getBase());        
        SimpleForm simpleForm =(SimpleForm) beanProps.get(".").getValue();  
                
        validateName(ctx, (String)beanProps.get("name").getValue());        
        validateMail(ctx, (String)beanProps.get("email").getValue());
        validateAdress(ctx, (String)beanProps.get("adress").getValue());
	}
	
	private void validateName(ValidationContext ctx, String value)
	{
		if (StringUtils.isEmpty(value)) {
			this.addInvalidMessage(ctx, "name",Labels.getLabel("common.field.required"));
		}
		else if(value.length()>40){
			this.addInvalidMessage(ctx, "name",Labels.getLabel("common.field.lenght.tolong"));
		}			
		else if(value.matches("^[^\\s]+$")==false){
			this.addInvalidMessage(ctx, "name",Labels.getLabel("users.validation.invalidLogin"));
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
	
	private void validateAdress(ValidationContext ctx, String value)
	{
		if (StringUtils.isEmpty(value)) {
			this.addInvalidMessage(ctx, "adress",Labels.getLabel("common.field.required"));
		}
		else if(value.length()>200){
			this.addInvalidMessage(ctx, "adress",Labels.getLabel("common.field.lenght.tolong"));
		}		
	}
	
	/** this function expected than all function has minimum one selling company.
	 * system check this requarment on method functionCorrect invoked previews
	 * The method checks if the upload has all companies in brand */
	public List<String> functionUploadCorrect(UserCCAPI user)
	{
		Iterable<CCAPIAuthority> authsForUpload= Iterables.filter(user.getCcapiAuthorities(),new Predicate<CCAPIAuthority>() {
			@Override
			public boolean apply(CCAPIAuthority input) {
				return input.getFunction().getName().equals(FunctionType.UPLOAD_TOUR_INFO.getSimpleName());
			}
		});		
		Iterator<CCAPIAuthority> itrForCCAPIAuthority = authsForUpload.iterator();
		if(itrForCCAPIAuthority.hasNext()==false)
			return new ArrayList<String>();
				

		Multimap<String,SellingCompany> brandData = ArrayListMultimap.create();		
		for (Iterator<CCAPIAuthority> iterator = itrForCCAPIAuthority; iterator.hasNext();) {
			CCAPIAuthority auth =  iterator.next();			
			brandData.put(auth.getSellingCompany().getBrand().getCode(), auth.getSellingCompany());			
		}
		
		List<String> result=Lists.newArrayList();
		for (String brandCode : brandData.keySet()) {
			Collection<SellingCompany> l=brandData.get(brandCode);
			int currentSize=l.size();
			Brand brand=l.iterator().next().getBrand();			
			int expectedSize=brand.getSellingCompanies().size();
						
			if(expectedSize==currentSize){
				continue;
			}
			result.add(Labels.getLabel("users.operator_ccapi_wiz.validation.brandCountForUpload",new Object[]{brand.getCode(),expectedSize,currentSize,FunctionType.UPLOAD_TOUR_INFO.getSimpleName()}));
		}
		return result;
	}
	
	
	/**
	 * System need minimum one selling company for function
	 * */
	public List<String> functionCorrect(UserCCAPI user) {
		Iterable<CCAPIAuthority> authsWithoutCompany= Iterables.filter(user.getCcapiAuthorities(),new Predicate<CCAPIAuthority>() {
			@Override
			public boolean apply(CCAPIAuthority input) {
				return input.getSellingCompany()==null;
			}
		});		
		
		List<String> result=Lists.newArrayList();
		for (CCAPIAuthority ccapiAuthority : authsWithoutCompany) {
			result.add(Labels.getLabel("users.operator_ccapi_wiz.validation.functionwithoutCompany",new Object[]{ccapiAuthority.getFunction().getName()}));
		}
		
		return result;
	}
	
	public List<String> validateUser(UserCCAPI user,String userName)
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
