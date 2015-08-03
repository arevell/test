package com.ttc.ch2.ui.moduls.user;

import java.util.HashSet;
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
import com.google.common.base.Optional;
import com.google.common.collect.Iterables;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.ttc.ch2.bl.user.UserService;
import com.ttc.ch2.common.predicates.FindEntityByIdsPredicate;
import com.ttc.ch2.common.predicates.FindGroupByNamePredicate;
import com.ttc.ch2.dao.BrandDAO;
import com.ttc.ch2.dao.security.GroupDAO;
import com.ttc.ch2.domain.Brand;
import com.ttc.ch2.domain.auth.Group;
import com.ttc.ch2.domain.auth.GroupNames;
import com.ttc.ch2.domain.user.UserGui;
import com.ttc.ch2.ui.common.ChooseModel;
import com.ttc.ch2.ui.common.config.Ch2URIs;
import com.ttc.ch2.ui.common.zkcontrolers.AbstractEncryptedParamHandelComp.ParamNames;
import com.ttc.ch2.ui.moduls.user.common.UserGuiForm;
import com.ttc.ch2.ui.moduls.user.common.UserGuiFormValidator;
import com.ttc.common.params.ParamsUtils;

@Component("userGuiWiz")
@Scope("request")
public class UserGuiWiz {
	
	private static final Logger logger=LoggerFactory.getLogger(UserGuiWiz.class);
			
	private UserGuiForm form;
	
	private String titleSuffix;
	
	private boolean brandSectionVisible;

	private UserGui editOperator=null;
	
	private ChooseModel<Brand> chooseModel;
			
	@Inject
	private UserService userService;
	
	@Inject
	private BrandDAO brandDAO;
	
	@Inject
	private GroupDAO groupDAO;
	
	
	
	

	@Init
	public void init() {		
		logger.trace("OperatorWiz:init-start");
		
		Map<String,String> params=Maps.newHashMap();
		if (StringUtils.isNotBlank(Executions.getCurrent().getParameter("param"))) {
			params = ParamsUtils.getInstance().decodeAllParam(Executions.getCurrent().getParameter("param"));
		}
		String param1= params.get("id");		
		String param2= params.get("type");
		
		if (StringUtils.isEmpty(param1) || StringUtils.isEmpty(param2)) {
			initAddMode();
		} else {
			initEditMode(param1);
		}				
		logger.trace("OperatorWiz:init-end");
	}
	
	private void initAddMode()
	{
		titleSuffix=Labels.getLabel("users.wiz.add.account");	
//		List<Group> allGroups=groupDAO.findAll();
		form=new UserGuiForm();
		form.setSelectedGroup(GroupNames.BRANDS.toString());	
		this.brandSectionVisible=true;
		chooseModel=new ChooseModel<Brand>(Sets.newHashSet(brandDAO.findAll()), new HashSet<Brand>());
//		form.setGroupAdmin(Iterables.find(allGroups, new FindGroupByName(GroupNames.ADMINS.toString())));		
//		form.setGroupBrand(Iterables.find(allGroups, new FindGroupByName(GroupNames.BRANDS.toString())));		
		form.setGroupAdmin(GroupNames.ADMINS.toString());		
		form.setGroupBrand(GroupNames.BRANDS.toString());		
		form.setEnabled(true);
	}
	
	private void initEditMode(String id)
	{
		UserGui o = userService.findUserGui(Long.parseLong(id));
		if(o==null)
		{									
			String param=ParamsUtils.encodeOneParam(ParamNames.msgBox.toString(), Labels.getLabel("users.validation.user_not_exist"));
			Executions.sendRedirect(Ch2URIs.OPERATOR_LIST.getPath()+"?param="+param);
			return;
		}
		
		
		brandSectionVisible=Iterables.tryFind(o.getGroups(), new FindGroupByNamePredicate(GroupNames.BRANDS.toString())).isPresent();
		titleSuffix=Labels.getLabel("users.wiz.edt.account");
		form=new UserGuiForm();
		form.setGroupAdmin(GroupNames.ADMINS.toString());		
		form.setGroupBrand(GroupNames.BRANDS.toString());					
		form.setLogin(o.getUsername());		
		form.setEmail(o.getEmail());
		form.setEnabled(o.getEnabled());
		form.setOldPassword(o.getPassword());
		form.setPasswordExist(StringUtils.isNotEmpty(o.getPassword()));
		if(StringUtils.isEmpty(o.getPassword())){
			form.setLdapUsed(true);
		}
				
		Set<Brand>selectedBrands = Sets.newHashSet(o.getBrands());
		Set<Brand>listAllBrands = Sets.newHashSet(Sets.newHashSet(brandDAO.findAll()));
		Iterables.removeIf(listAllBrands, new FindEntityByIdsPredicate<Brand>(selectedBrands));		
		chooseModel=new ChooseModel<Brand>(listAllBrands, selectedBrands);
							
		Optional<Group> optGroup=Iterables.tryFind(o.getGroups(), new FindGroupByNamePredicate(GroupNames.BRANDS.toString()));				
		if(optGroup.isPresent()){			
			form.setSelectedGroup(GroupNames.BRANDS.toString());
		}
		else{
			optGroup=Iterables.tryFind(o.getGroups(), new FindGroupByNamePredicate(GroupNames.ADMINS.toString()));
			if(optGroup.isPresent()){
				form.setSelectedGroup(GroupNames.ADMINS.toString());				
			}
		}
		editOperator = o;
	}
	
	@Command
	@NotifyChange({"chooseModel"})
	public void onChooseAll(){	
		chooseModel.chooseAll();
	}
	@Command
	@NotifyChange({"chooseModel"})
	public void onRemoveAll(){		
		chooseModel.removeAll();
	}
	
	@Command
	@NotifyChange({"chooseModel"})
	public void onRemove(){		
		chooseModel.remove();
	}
	
	@Command
	@NotifyChange({"chooseModel"})
	public void onChoose(){		
		chooseModel.choose();
	}

	
	@Command
	@NotifyChange({"form.password"})
	public void onLDAPCheck(){		

		form.setLdapUsed(!form.isLdapUsed());
		form.setPassword(null);
	}

	@Command
    @NotifyChange({"brandSectionVisible","chooseModel","form.selectedGroup"})
    public void changeGroup(@BindingParam("element") String selectedGroup) {
		if (StringUtils.isEmpty(selectedGroup)) {
			brandSectionVisible=true;
			form.setSelectedGroup(GroupNames.BRANDS.toString());
		}
		else{		
			brandSectionVisible=selectedGroup.equals(GroupNames.BRANDS.toString());			
		}
		onRemoveAll();
	}
	

	@Command
	@NotifyChange({"listAllRoles","selectedBrands"})
	public void onSave(){	
		
		boolean modeEdit=editOperator!=null;
		List<Group> allGroups=groupDAO.findAll();	
		UserGui o=modeEdit ? editOperator :new UserGui();
		
		o.setPassword(form.getPassword());
		o.setEmail(form.getEmail());
		o.setEnabled(form.isEnabled());	
		o.setBrands(Sets.newHashSet(chooseModel.getSelectedList()));
		o.setGroups(Sets.newHashSet(Iterables.find(allGroups, new FindGroupByNamePredicate(form.getSelectedGroup()))));
				
		UserGuiFormValidator externalValidation=new UserGuiFormValidator();
		List<String> errorMsg=null;
		if((errorMsg=externalValidation.validateBrand(o)).size()>0){			
			Messagebox.show(Joiner.on("\n").join(errorMsg), Labels.getLabel("users.usergui_wiz.validation.validation_title_box"), Messagebox.OK, Messagebox.ERROR);
			return;
		}
		if((errorMsg=externalValidation.validateUser(o,form.getLogin())).size()>0){			
			Messagebox.show(Joiner.on("\n").join(errorMsg), Labels.getLabel("users.usergui_wiz.validation.validation_title_box"), Messagebox.OK, Messagebox.ERROR);
			return;
		}
		else {
			o.setUsername(form.getLogin());
		}
		
		
		userService.mergeUserGui(o,form.isLdapUsed());		
		Executions.sendRedirect(Ch2URIs.OPERATOR_LIST.getPath());
	}

	

	public UserGuiForm getForm() {
		return form;
	}

	public void setForm(UserGuiForm form) {
		this.form = form;
	}

	public ChooseModel<Brand> getChooseModel() {
		return chooseModel;
	}

	public void setChooseModel(ChooseModel<Brand> chooseModel) {
		this.chooseModel = chooseModel;
	}

	public boolean isBrandSectionVisible() {
		return brandSectionVisible;
	}

	public void setBrandSectionVisible(boolean brandSectionVisible) {
		this.brandSectionVisible = brandSectionVisible;
	}

	public String getTitleSuffix() {
		return titleSuffix;
	}

	public void setTitleSuffix(String titleSuffix) {
		this.titleSuffix = titleSuffix;
	}
}
