package com.ttc.ch2.common.predicates;

import com.google.common.base.Predicate;
import com.ttc.ch2.domain.auth.Group;

public class FindGroupByNamePredicate implements Predicate<Group>
{
	String name;
	
	public FindGroupByNamePredicate(String name) {
		super();
		this.name = name;
	}

	@Override
	public boolean apply(Group input) {
		return name.equals(input.getGroupName());
	}		
}