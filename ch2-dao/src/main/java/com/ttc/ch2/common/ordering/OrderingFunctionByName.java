package com.ttc.ch2.common.ordering;

import com.google.common.collect.Ordering;
import com.ttc.ch2.domain.Function;

public class OrderingFunctionByName extends Ordering<Function> {

	@Override
	public int compare(Function o1, Function o2) {
		if(o1==null && o2==null)
			return 0;
		if(o1!=null && o2==null)
			return 1;
		if(o1==null && o2!=null)
			return -1;
		return o1.getName().compareTo(o2.getName());
	}
}