package com.ttc.ch2.ui.moduls.user.common;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;
import com.ttc.ch2.common.ordering.OrderingFunctionByName;
import com.ttc.ch2.common.ordering.OrderingSellingCompanyByCode;
import com.ttc.ch2.common.predicates.FindCompaniesForBrandPredicate;
import com.ttc.ch2.common.predicates.FindEntityByIdPredicate;
import com.ttc.ch2.domain.Brand;
import com.ttc.ch2.domain.Function;
import com.ttc.ch2.domain.SellingCompany;
import com.ttc.ch2.domain.auth.CCAPIAuthority;
import com.ttc.ch2.ui.common.ChooseModel;

public class ChooseModelCCAPI extends ChooseModel<Function>{

	private Map<Function,SellingCompanyFiltredModel> companyModels=null; 
	
	private List<SellingCompany> allSellingCompany;
	
	private SellingCompanyFiltredModel currentCompany;
		
	private ChooseModelCCAPI(List<Function> choicesList,List<Function> selectedList,List<SellingCompany> allSellingCompany) {
		super(choicesList, selectedList);
		companyModels=Maps.newHashMap();
		this.allSellingCompany=allSellingCompany;
	}
		
		
	public void remove() {
		super.remove();
		companyModels.remove(getSelectdElementFromSelectedList());
	}
	
	public void choose(){
		super.choose();		
		Collections.sort(getSelectedList(), new OrderingFunctionByName());		
		currentCompany=new SellingCompanyFiltredModel(allSellingCompany,new ArrayList<SellingCompany>());
		companyModels.put(getSelectdElementFromChoicesList(),currentCompany);		
	}

	public SellingCompanyFiltredModel getCurrentCompany() {
		return currentCompany;
	}

	public void setCurrentCompany(SellingCompanyFiltredModel currentCompany) {
		this.currentCompany = currentCompany;
	}
	
	public void selectFunction(Function selectedFunction,Brand selectedBrand)
	{			
		if (selectedFunction != null) {	
			ChooseModel<SellingCompany> chooseCompany=companyModels.get(selectedFunction);
			List<SellingCompany> selectedComp=chooseCompany.getSelectedList();			
			List<SellingCompany> listComapniesToChoose=Lists.newArrayList(Iterables.filter(allSellingCompany, Predicates.and(new FindCompaniesForBrandPredicate(selectedBrand),new FindNoSelectedCompanies(selectedComp))));			
			currentCompany=new SellingCompanyFiltredModel(listComapniesToChoose,selectedComp);
			currentCompany.setSelectedBrand(selectedBrand);
			currentCompany.filtredByBrand(selectedBrand);
			companyModels.put(selectedFunction,currentCompany);					
		}
		else{
			currentCompany=null;
		}
	}	
	class FindNoSelectedCompanies implements Predicate<SellingCompany>
	{
		private List<SellingCompany> selectedCompanies;
		
		public FindNoSelectedCompanies(List<SellingCompany> selectedCompanies) {
			super();
			this.selectedCompanies = selectedCompanies;
		}

		@Override
		public boolean apply(SellingCompany input) {
			
			Optional<SellingCompany> s=Iterables.tryFind(selectedCompanies, new FindEntityByIdPredicate(input.getId()));			
			return !s.isPresent();
		}		
	}

	public Map<Function, SellingCompanyFiltredModel> getCompanyModels() {
		return companyModels;
	}
	
	
	public static ChooseModelCCAPI getChooseModelCCAPIInstance(List<Function> choicesList,List<SellingCompany> allSellingCompany){
		
		Collections.sort(choicesList, new OrderingFunctionByName());
		Collections.sort(allSellingCompany, new OrderingSellingCompanyByCode());			
		
		return new ChooseModelCCAPI(choicesList,new ArrayList<Function>(),allSellingCompany);
	}
	
	public static ChooseModelCCAPI getChooseModelCCAPIInstance(Set<CCAPIAuthority> auths,List<Function> allFunction,List<SellingCompany> allSellingCompany)	
	{		
		Multimap<Function,SellingCompany> authData = ArrayListMultimap.create();
		for (CCAPIAuthority auth : auths) {
			authData.put(auth.getFunction(), auth.getSellingCompany());
		}
		
		//functions
		List<Function> functionToChoose=Lists.newArrayList();
		for (Function function : allFunction) {
			boolean toChoose=true;
			for (Function authFun : authData.keySet()) {				
				if(function.getName().equals(authFun.getName())){
							toChoose=false;
							break;
						}				
			}
			if(toChoose){
			functionToChoose.add(function);
			}
		}
		List<Function> functionSelectd=Lists.newArrayList((authData.keySet()));
		
		Collections.sort(functionToChoose, new OrderingFunctionByName());
		Collections.sort(functionSelectd, new OrderingFunctionByName());
		Collections.sort(allSellingCompany, new OrderingSellingCompanyByCode());
		
		ChooseModelCCAPI chooseModelCCAPI=new ChooseModelCCAPI(functionToChoose,functionSelectd , allSellingCompany);
		
		//companies
		for (Function function : authData.keySet()) {								
			List<SellingCompany> companiesToChoose=Lists.newArrayList();
			for (SellingCompany company : allSellingCompany) {
				boolean toChoose=true;
				for (SellingCompany authCompany : authData.get(function)) {				
					if(company.getName().equals(authCompany.getName())){
								toChoose=false;
								break;
							}				
				}
				if(toChoose){
					companiesToChoose.add(company);
				}
			}								
			Collection<SellingCompany> companiesSelected=authData.get(function);			
			List<SellingCompany> selectedComapniesList=Lists.newArrayList(companiesSelected);			
			Collections.sort(companiesToChoose, new OrderingSellingCompanyByCode());
			Collections.sort(selectedComapniesList, new OrderingSellingCompanyByCode());
			chooseModelCCAPI.getCompanyModels().put(function, new SellingCompanyFiltredModel(companiesToChoose,selectedComapniesList));							
		}		
		return chooseModelCCAPI;
	}

}
