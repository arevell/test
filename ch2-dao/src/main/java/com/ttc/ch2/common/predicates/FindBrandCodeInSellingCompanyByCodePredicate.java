package com.ttc.ch2.common.predicates;

import com.google.common.base.Predicate;
import com.ttc.ch2.domain.SellingCompany;

public class FindBrandCodeInSellingCompanyByCodePredicate implements Predicate<SellingCompany> {

	private String brandCode;
	
	public FindBrandCodeInSellingCompanyByCodePredicate(String brandCode) {
		super();
		this.brandCode = brandCode;
	}

	@Override
	public boolean apply(SellingCompany input) {		
		return input.getBrand().getCode().equals(brandCode);
	}

}
