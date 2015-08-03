package com.ttc.ch2.ui.moduls.tour.common;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.zkoss.bind.BindContext;
import org.zkoss.bind.Converter;
import org.zkoss.zk.ui.Component;

public class SizeConvert implements Converter<String, Long, Component> {

	@Override
	public String coerceToUi(Long beanProp, Component component, BindContext ctx) {
		if(beanProp==null)
			return "no data";
		Double result=null;     			
		result=new Double(beanProp/1024d);		
		BigDecimal value=new BigDecimal(result).setScale(0,RoundingMode.HALF_UP);
		return value.toString()+" KB";
	}

	@Override
	public Long coerceToBean(String compAttr, Component component,
			BindContext ctx) {
		return 0l;
	}
}
