package com.ttc.ch2.ui.moduls.user;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.Messagebox;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.ttc.ch2.bl.token.TokenService;
import com.ttc.ch2.bl.user.UserService;
import com.ttc.ch2.dao.BrandDAO;
import com.ttc.ch2.dao.FunctionDAO;
import com.ttc.ch2.dao.SellingCompanyDAO;
import com.ttc.ch2.domain.Brand;
import com.ttc.ch2.domain.Function;
import com.ttc.ch2.domain.SellingCompany;
import com.ttc.ch2.domain.auth.CCAPIAuthority;
import com.ttc.ch2.domain.common.SortCondition;
import com.ttc.ch2.domain.common.SortCondition.Direction;
import com.ttc.ch2.domain.user.UserCCAPI;
import com.ttc.ch2.ui.common.config.Ch2URIs;
import com.ttc.ch2.ui.common.zkcontrolers.AbstractEncryptedParamHandelComp.ParamNames;
import com.ttc.ch2.ui.moduls.user.common.ChooseModelCCAPI;
import com.ttc.ch2.ui.moduls.user.common.TokenValidator;
import com.ttc.ch2.ui.moduls.user.common.UserCcapiForm;
import com.ttc.ch2.ui.moduls.user.common.UserCcapiFormValidator;
import com.ttc.common.params.ParamsUtils;

@Component("userCcapiWiz")
@Scope("request")
public class UserCcapiWiz {
	
	private static final Logger logger=LoggerFactory.getLogger(UserCcapiWiz.class);
	private static Brand EmptyBrand;
	
	static
	{
		EmptyBrand=new Brand();
		EmptyBrand.setBrandName(Labels.getLabel("common.combobox.empty_value"));
	}
			
	private UserCcapiForm form;
	private String token;
	
	private String titleSuffix;

	private UserCCAPI editOperator=null;
	
	private ChooseModelCCAPI chooseModelCCAPI;
	private Function selectedFunction;
//	private Function selectedDcFunction;
	
	private Brand selectedBrand;
	private List<Brand> brands;
		
	@Inject
	private UserService userService;
	
	@Inject
	private BrandDAO brandDAO;
	
	@Inject
	private SellingCompanyDAO sellingCompanyDAO;
	
	@Inject
	private FunctionDAO functionDAO;
	
	@Inject
	private TokenService tokenService;
	
	
	@Init
	public void init() {		
		logger.trace("UserCcapiWiz:init-start");
		
		Map<String,String> params=Maps.newHashMap();
		if (StringUtils.isNotBlank(Executions.getCurrent().getParameter("param"))) {
			params = ParamsUtils.getInstance().decodeAllParam(Executions.getCurrent().getParameter("param"));
		}
		String param1= params.get("id");		
		String param2= params.get("type");
		
		brands=Lists.newArrayList();
		brands.add(EmptyBrand);
		brands.addAll(brandDAO.findAll());
		
		if (StringUtils.isEmpty(param1) || StringUtils.isEmpty(param2)) {
			initAddMode();
		} else {
			initEditMode(param1);
		}				
		logger.trace("UserCcapiWiz:init-end");
	}
	
	private void initAddMode() {
		titleSuffix=Labels.getLabel("users.wiz.add.account");	
		List<SellingCompany> allSellingCompany=sellingCompanyDAO.findAllSellingCompany(new SortCondition("brand.code",Direction.ASC));
		chooseModelCCAPI=ChooseModelCCAPI.getChooseModelCCAPIInstance(functionDAO.findAll(),  allSellingCompany);
		form=new UserCcapiForm();
		form.setEnabled(true);		
	}
	
	private void initEditMode(String id) {
		UserCCAPI o = userService.findUserCCAPI(Long.parseLong(id));		
		if(o==null)
		{									
			String param=ParamsUtils.encodeOneParam(ParamNames.msgBox.toString(), Labels.getLabel("users.validation.user_not_exist"));
			Executions.sendRedirect(Ch2URIs.OPERATOR_CCAPI_LIST.getPath()+"?param="+param);
			return;
		}
			
		
		List<SellingCompany> allSellingCompany=sellingCompanyDAO.findAllSellingCompany(new SortCondition("brand.code",Direction.ASC));
		titleSuffix=Labels.getLabel("users.wiz.edt.account");
		form=new UserCcapiForm();
					
		form.setName(o.getUsername());		
		form.setEmail(o.getEmail());
		form.setEnabled(o.getEnabled());
		token=o.getToken();
		form.setToken(o.getToken());
		form.setAdress(o.getAddress());
		
		chooseModelCCAPI = ChooseModelCCAPI.getChooseModelCCAPIInstance(o.getCcapiAuthorities(), functionDAO.findAll(), allSellingCompany);	
		editOperator = o;
	}
	
	@Command
	@NotifyChange({"chooseModelCCAPI"})
	public void onChooseCompanyAll(){		
		if(chooseModelCCAPI.getCurrentCompany()!=null)
		chooseModelCCAPI.getCurrentCompany().chooseAll();
	}
	@Command
	@NotifyChange({"chooseModelCCAPI"})
	public void onRemoveCompanyAll(){		
		if(chooseModelCCAPI.getCurrentCompany()!=null)
		chooseModelCCAPI.getCurrentCompany().removeAll();
	}
	
	@Command
	@NotifyChange({"chooseModelCCAPI"})
	public void onRemoveComapany(){
		if(chooseModelCCAPI.getCurrentCompany()!=null)
		chooseModelCCAPI.getCurrentCompany().remove();
	}
	
	@Command
	@NotifyChange({"chooseModelCCAPI"})
	public void onChooseCompany(){
		if(chooseModelCCAPI.getCurrentCompany()!=null)
		chooseModelCCAPI.getCurrentCompany().choose();
	}
	
	@Command
	@NotifyChange({"chooseModelCCAPI","selectedFunction"})
	public void onRemove(){		
		chooseModelCCAPI.remove();
		selectedFunction=null;
		chooseModelCCAPI.selectFunction(selectedFunction,selectedBrand==EmptyBrand ? null :selectedBrand);
	}
	
	@Command
	@NotifyChange({"chooseModelCCAPI","selectedFunction"})
	public void onChoose(){		
		chooseModelCCAPI.choose();
		selectedFunction=chooseModelCCAPI.getSelectdElementFromChoicesList();
		chooseModelCCAPI.selectFunction(selectedFunction,selectedBrand==EmptyBrand ? null :selectedBrand);
	}
	
	@Command
	@NotifyChange({"chooseModelCCAPI","selectedFunction"})
	public void onDcSelectFunction(@BindingParam("element") Function selected){
		selectedFunction=selected;
		chooseModelCCAPI.selectFunction(selectedFunction,selectedBrand==EmptyBrand ? null :selectedBrand);
	}
	
	
	@Command
    @NotifyChange({"chooseModelCCAPI","selectedFunction"})
    public void changeFilter() {		
		chooseModelCCAPI.selectFunction(selectedFunction,selectedBrand==EmptyBrand ? null :selectedBrand);		
	}
	
	@Command
    @NotifyChange({"token"})
    public void onGenerateToken() {		
		token=tokenService.getGenerateToken();
	}

	@Command
	public void onSave(){		
		boolean modeEdit=editOperator!=null;		
		UserCCAPI o=modeEdit ? editOperator :new UserCCAPI();		
		o.setEmail(form.getEmail());
		o.setAddress(form.getAdress());
		o.setEnabled(form.isEnabled());	
	 
		List<Function> selectedFunctions=chooseModelCCAPI.getSelectedList();
		Set<CCAPIAuthority> auths=Sets.newHashSet();
		for (Function function : selectedFunctions) {
			List<SellingCompany> selectedCompanies=chooseModelCCAPI.getCompanyModels().get(function).getSelectedList();
			
			if(selectedCompanies.size()>0){			
				for (SellingCompany sellingCompany : selectedCompanies) {
					CCAPIAuthority auth=new CCAPIAuthority();
					auth.setSellingCompany(sellingCompany);
					auth.setFunction(function);
					auths.add(auth);
				}	
			}
			else{
				CCAPIAuthority auth=new CCAPIAuthority();
				auth.setFunction(function);
				auths.add(auth);
			}			
		}		
		o.setCcapiAuthorities(auths);
		
		
		UserCcapiFormValidator extraValidUserCCAPI=new UserCcapiFormValidator();		
		List<String> errorMsg=null;	
		if((errorMsg=new TokenValidator().validateToken(o,token)).size()>0){			
			Messagebox.show(Joiner.on("\n").join(errorMsg), Labels.getLabel("users.operator_ccapi_wiz.validation.validation_title_box"), Messagebox.OK, Messagebox.ERROR);
			return;
		}
		else {
			o.setToken(token);
		}
			
		
		if((errorMsg=extraValidUserCCAPI.functionCorrect(o)).size()>0){			
			Messagebox.show(Joiner.on("\n").join(errorMsg), Labels.getLabel("users.operator_ccapi_wiz.validation.validation_title_box"), Messagebox.OK, Messagebox.ERROR);
			return;
		}
		if((errorMsg=extraValidUserCCAPI.functionUploadCorrect(o)).size()>0){			
			Messagebox.show(Joiner.on("\n").join(errorMsg), Labels.getLabel("users.operator_ccapi_wiz.validation.validation_title_box"), Messagebox.OK, Messagebox.ERROR);
			return;
		}
		if((errorMsg=extraValidUserCCAPI.validateUser(o,form.getName())).size()>0){			
			Messagebox.show(Joiner.on("\n").join(errorMsg), Labels.getLabel("users.operator_ccapi_wiz.validation.validation_title_box"), Messagebox.OK, Messagebox.ERROR);
			return;
		}
		else {
			o.setUsername(form.getName());
		}			
		userService.mergeUserCCAPI(o);
		Executions.sendRedirect(Ch2URIs.OPERATOR_CCAPI_LIST.getPath());
	}


	public ChooseModelCCAPI getChooseModelCCAPI() {
		return chooseModelCCAPI;
	}

	public void setChooseModelCCAPI(ChooseModelCCAPI chooseModelCCAPI) {
		this.chooseModelCCAPI = chooseModelCCAPI;
	}

	public UserCcapiForm getForm() {
		return form;
	}

	public void setForm(UserCcapiForm form) {
		this.form = form;
	}

	public Function getSelectedFunction() {
		return selectedFunction;
	}

	public void setSelectedFunction(Function selectedFunction) {
		this.selectedFunction = selectedFunction;
	}

	public List<Brand> getBrands() {
		return brands;
	}

	public void setBrands(List<Brand> brands) {
		this.brands = brands;
	}

	public Brand getSelectedBrand() {
		return selectedBrand;
	}

	public void setSelectedBrand(Brand selectedBrand) {
		this.selectedBrand = selectedBrand;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getTitleSuffix() {
		return titleSuffix;
	}

	public void setTitleSuffix(String titleSuffix) {
		this.titleSuffix = titleSuffix;
	}
}
