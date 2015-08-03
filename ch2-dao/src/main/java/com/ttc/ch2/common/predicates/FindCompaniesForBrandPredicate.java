package com.ttc.ch2.common.predicates;

import com.google.common.base.Predicate;
import com.ttc.ch2.domain.Brand;
import com.ttc.ch2.domain.SellingCompany;

public class FindCompaniesForBrandPredicate implements Predicate<SellingCompany>
{
	private Brand brand;
	
	public FindCompaniesForBrandPredicate(Brand brand) {
		super();
		this.brand = brand;
	}

	@Override
	public boolean apply(SellingCompany input) {
		if (brand==null)
			return true;
		return input.getBrand().getCode().equals(brand.getCode());
	}		
}