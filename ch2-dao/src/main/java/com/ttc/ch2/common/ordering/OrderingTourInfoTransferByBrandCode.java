package com.ttc.ch2.common.ordering;

import com.google.common.collect.Ordering;
import com.ttc.ch2.domain.transfer.TourInfoTransfer;

public class OrderingTourInfoTransferByBrandCode extends Ordering<TourInfoTransfer> {

	@Override
	public int compare(TourInfoTransfer o1, TourInfoTransfer o2) {
		
		if(o1==null && o2==null)
			return 0;
		if(o1!=null && o2==null)
			return 1;
		if(o1==null && o2!=null)
			return -1;
				
		return o1.getBrand().getCode().compareTo(o2.getBrand().getCode());
	}
}