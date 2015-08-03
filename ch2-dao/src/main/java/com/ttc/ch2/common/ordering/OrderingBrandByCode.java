package com.ttc.ch2.common.ordering;

import java.util.Collections;
import java.util.List;

import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import com.ttc.ch2.domain.Brand;

public class OrderingBrandByCode extends Ordering<Brand> {

	@Override
	public int compare(Brand o1, Brand o2) {
		
		if(o1==null && o2==null)
			return 0;
		if(o1!=null && o2==null)
			return 1;
		if(o1==null && o2!=null)
			return -1;
				
		return o1.getCode().compareTo(o2.getCode());
	}
}