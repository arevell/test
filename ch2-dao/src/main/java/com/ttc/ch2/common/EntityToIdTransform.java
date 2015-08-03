package com.ttc.ch2.common;

import com.google.common.base.Function;
import com.ttc.ch2.domain.common.EntityBase;

public class EntityToIdTransform implements Function<EntityBase, Long> {

	@Override
	public Long apply(EntityBase input) {
		return input.getId();
	}

}
