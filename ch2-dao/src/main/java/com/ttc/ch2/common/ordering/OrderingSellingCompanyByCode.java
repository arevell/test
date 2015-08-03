package com.ttc.ch2.common.ordering;

import com.google.common.collect.Ordering;
import com.ttc.ch2.domain.SellingCompany;

public class OrderingSellingCompanyByCode extends Ordering<SellingCompany> {

	@Override
	public int compare(SellingCompany o1, SellingCompany o2) {
		if(o1==null && o2==null)
			return 0;
		if(o1!=null && o2==null)
			return 1;
		if(o1==null && o2!=null)
			return -1;
		return o1.getCode().compareTo(o2.getCode());
	}
}