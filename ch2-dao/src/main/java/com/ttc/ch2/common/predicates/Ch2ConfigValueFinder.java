package com.ttc.ch2.common.predicates;

import com.google.common.base.Predicate;
import com.ttc.ch2.domain.Ch2ConfigValue;

public class Ch2ConfigValueFinder implements Predicate<Ch2ConfigValue>{
	private String propertyName;

	public Ch2ConfigValueFinder(String propertyName) {
		super();
		this.propertyName = propertyName;
	}
	
	public Ch2ConfigValueFinder(Ch2ConfigValue.PropName propertyName) {
		super();
		this.propertyName = propertyName.toString();
	}

	@Override
	public boolean apply(Ch2ConfigValue input) {

		return input.getPropertyName().equals(propertyName);
	}
}