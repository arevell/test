package com.ttc.ch2.ui.moduls.user.common;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.ttc.ch2.common.ordering.OrderingSellingCompanyByCode;
import com.ttc.ch2.common.predicates.FindCompaniesForBrandPredicate;
import com.ttc.ch2.domain.Brand;
import com.ttc.ch2.domain.SellingCompany;
import com.ttc.ch2.ui.common.ChooseModel;

public class SellingCompanyFiltredModel extends ChooseModel<SellingCompany>{

	private Brand selectedBrand=null;
	private List<SellingCompany> selectedListFiltredByBrand;
	
		
	public SellingCompanyFiltredModel(List<SellingCompany> choicesList,List<SellingCompany> selectedList) {
		super(choicesList, selectedList);
	}
	public SellingCompanyFiltredModel(Set<SellingCompany> choicesList,Set<SellingCompany> selectedList) {
		super(choicesList, selectedList);
	}
		
	public void filtredByBrand(Brand brand) {	
		if(brand==null){
			selectedListFiltredByBrand=getSelectedList();
		}
		else {			
			selectedListFiltredByBrand= Lists.newArrayList(Iterables.filter(getSelectedList(), new FindCompaniesForBrandPredicate(selectedBrand)));					
		}
		Collections.sort(selectedListFiltredByBrand, new OrderingSellingCompanyByCode());
	}
	
	
	public void chooseAll(){	
		super.chooseAll();
		filtredByBrand(selectedBrand);
	}
		
	public void removeAll(){		
		super.removeAll();
		filtredByBrand(selectedBrand);
	}
	
	public void remove(){
		super.remove();
		filtredByBrand(selectedBrand);
	}
	
	public void choose(){
		super.choose();		
		filtredByBrand(selectedBrand);
	}
	
	public Brand getSelectedBrand() {
		return selectedBrand;
	}
	public void setSelectedBrand(Brand selectedBrand) {
		this.selectedBrand = selectedBrand;
	}
	
	
	public List<SellingCompany> getSelectedListFiltredByBrand() {
		return selectedListFiltredByBrand;
	}
	public void setSelectedListFiltredByBrand(
			List<SellingCompany> selectedListFiltredByBrand) {
		this.selectedListFiltredByBrand = selectedListFiltredByBrand;
	}
}
