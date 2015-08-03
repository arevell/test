package com.ttc.ch2.common;

import com.google.common.base.Function;
import com.ttc.ch2.domain.common.EntityBase;

public class DomainToEntityBase<F> implements Function<F, EntityBase> {

	@Override
	public EntityBase apply(F input) {		
		return (EntityBase)input;
	}

}
