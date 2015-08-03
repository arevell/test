package com.ttc.ch2.common.predicates;

import com.google.common.base.Predicate;
import com.ttc.ch2.domain.Brand;

public class FindBrandByCodePredicate implements Predicate<Brand>
{
	private String code;
	
	public FindBrandByCodePredicate(String code) {
		super();
		this.code = code;
	}

	@Override
	public boolean apply(Brand input) {
		return code.equals(input.getCode());
	}		
}