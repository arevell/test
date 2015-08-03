package com.ttc.ch2.common.predicates;

import com.google.common.base.Predicate;
import com.ttc.ch2.domain.common.EntityBase;

public class FindEntityByIdPredicate implements Predicate<EntityBase> {

	private Long id;
	
	public FindEntityByIdPredicate(Long id) {
		super();
		this.id = id;
	}

	@Override
	public boolean apply(EntityBase input) {

		return id.equals(input.getId());
	}

}


