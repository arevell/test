package com.ttc.ch2.ui.moduls.user;

import java.util.Map;
import java.util.Set;

import javax.inject.Inject;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Executions;

import com.google.common.base.Optional;
import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;
import com.ttc.ch2.bl.user.UserService;
import com.ttc.ch2.common.predicates.FindEntityByIdsPredicate;
import com.ttc.ch2.common.predicates.FindGroupByNamePredicate;
import com.ttc.ch2.dao.BrandDAO;
import com.ttc.ch2.domain.Brand;
import com.ttc.ch2.domain.auth.Group;
import com.ttc.ch2.domain.auth.GroupNames;
import com.ttc.ch2.domain.user.UserGui;
import com.ttc.ch2.ui.common.ChooseModel;
import com.ttc.ch2.ui.common.config.Ch2URIs;
import com.ttc.ch2.ui.common.exceptions.CH2Exception;
import com.ttc.ch2.ui.moduls.user.common.UserGuiForm;
import com.ttc.common.params.ParamsUtils;

@Component("userGuiWizDel")
@Scope("request")
public class UserGuiWizDel {
	
	private static final Logger logger=LoggerFactory.getLogger(UserGuiWizDel.class);
			
	@Inject
	private UserService userService;
	
	@Inject
	private BrandDAO brandDAO;
	
	private UserGuiForm form;
	private String titleSuffix;
	private boolean deleteBrandUser;
	private UserGui deleteObject=null;	
	private ChooseModel<Brand> chooseModel;
			
	
	@Init
	public void init() {		
		logger.trace("UserGuiWizDel:init-start");
		
		if (StringUtils.isBlank(Executions.getCurrent().getParameter("param"))) {
			throw new CH2Exception(Labels.getLabel("common.cntrl.params.notexist",new Object[]{this.getClass().getSimpleName()}));
		}		
		Map<String,String> params = ParamsUtils.getInstance().decodeAllParam(Executions.getCurrent().getParameter("param"));		
		String param1= params.get("id");		
		initDelMode(param1);
		logger.trace("UserGuiWizDel:init-end");
	}
	
	
	private void initDelMode(String id)
	{
		logger.trace("UserGuiWizDel:initDelMode-start");
		UserGui o = userService.findUserGui(Long.parseLong(id));
		titleSuffix=Labels.getLabel("users.wiz.del.account");
		form=new UserGuiForm();
		form.setGroupAdmin(GroupNames.ADMINS.toString());		
		form.setGroupBrand(GroupNames.BRANDS.toString());					
		form.setLogin(o.getUsername());		
		form.setEmail(o.getEmail());
		form.setEnabled(o.getEnabled());
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
			deleteBrandUser=true;
		}
		else{
			optGroup=Iterables.tryFind(o.getGroups(), new FindGroupByNamePredicate(GroupNames.ADMINS.toString()));
			if(optGroup.isPresent()){
				form.setSelectedGroup(GroupNames.ADMINS.toString());				
			}
		}
		deleteObject = o;
		logger.trace("UserGuiWizDel:initDelMode-end");
	}

	@Command
	@NotifyChange({"listAllRoles","selectedBrands"})
	public void onSave(){	
		logger.trace("UserGuiWizDel:onSave-start");
		userService.deleteUserGui(deleteObject.getId());		
		Executions.sendRedirect(Ch2URIs.OPERATOR_LIST.getPath());
		logger.trace("UserGuiWizDel:onSave-end");
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


	public boolean isDeleteBrandUser() {
		return deleteBrandUser;
	}


	public void setDeleteBrandUser(boolean deleteBrandUser) {
		this.deleteBrandUser = deleteBrandUser;
	}


	public String getTitleSuffix() {
		return titleSuffix;
	}


	public void setTitleSuffix(String titleSuffix) {
		this.titleSuffix = titleSuffix;
	}
}
