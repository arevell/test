package com.ttc.ch2.bl.constraints;

import java.util.Set;

public interface ConstraintService {

	
	public Set<String>  getToursWithoutBrand() throws ConstraintServiceException;
}
