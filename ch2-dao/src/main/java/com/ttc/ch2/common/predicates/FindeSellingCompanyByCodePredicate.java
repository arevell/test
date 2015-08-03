package com.ttc.ch2.common.predicates;

import com.google.common.base.Predicate;
import com.ttc.ch2.domain.SellingCompany;

public class FindeSellingCompanyByCodePredicate implements Predicate<SellingCompany>
{
	private String  sellingCompanyCode;

	public FindeSellingCompanyByCodePredicate(String sellingCompanyCode) {
		super();
		this.sellingCompanyCode = sellingCompanyCode;
	}

	@Override
	public boolean apply(SellingCompany input) {
	
		return sellingCompanyCode.equals(input.getCode());
	}		
}