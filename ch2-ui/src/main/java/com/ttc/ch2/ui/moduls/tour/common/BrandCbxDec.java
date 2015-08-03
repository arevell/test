package com.ttc.ch2.ui.moduls.tour.common;

import java.util.List;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.ttc.ch2.domain.Brand;
import com.ttc.ch2.ui.common.DomainDecorator;

public class BrandCbxDec extends DomainDecorator<Brand> {

	List<Brand> brands=null;

	
	public BrandCbxDec(List<Brand> brands) {
		super(false);
		this.brands = brands;
		values.addAll(Lists.newArrayList(Iterables.transform(brands, new BrandToString())));
	}

	
	@Override
	public Brand getValueByString(String v) {
		if(v.equals(emptyValue))
			return null;
		return Iterables.find(brands, new FindByBrandName(v));
	}
	
	class BrandToString implements Function<Brand, String>
	{
		@Override
		public String apply(Brand input) {
			return input.getBrandName();
		}					
	}
	
	class FindByBrandName implements Predicate<Brand>
	{
		private String brandName;

		public FindByBrandName(String brandName) {
			super();
			this.brandName = brandName;
		}

		@Override
		public boolean apply(Brand input) {
			return brandName.equals(input.getBrandName());
		}
		
	}
}
