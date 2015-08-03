package com.ttc.ch2.common.predicates;

import java.util.Set;

import com.google.common.base.Predicate;
import com.google.common.collect.Sets;
import com.ttc.ch2.domain.common.EntityBase;

public class FindEntityByIdsPredicate<T extends EntityBase> implements Predicate<EntityBase> {

	private Set<Long> ids;
	
	public FindEntityByIdsPredicate(Set<T> list) {
		super();
		ids=Sets.newHashSet();
		for (EntityBase entityBase : list) {
		ids.add(entityBase.getId());	
		}
	}

	@Override
	public boolean apply(EntityBase input) {

		return ids.contains(input.getId());
	}

}


