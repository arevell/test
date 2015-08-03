package com.ttc.ch2.ui.common.config;


class SimpleValue implements ConfigValue
{
	private Object val; 
	
	SimpleValue(Object val)
	{
		this.val=val;
	}

	@Override
	public Object getValue() {
		return val;
	}
}