package com.ttc.ch2.ui.common.validators;

import com.ttc.ch2.ui.common.exceptions.TourValidatorException;

public class TourValidator {
	
	private static final String INCORRECT_TOUR_CODE = "Tour Code is incorrect";
	private static final String TOUR_REGEXP = "^[A-Za-z0-9]+$";
	
	public static void validate(String tourCode) {
		if(!tourCode.matches(TOUR_REGEXP))
			throw new TourValidatorException(INCORRECT_TOUR_CODE);
	}
}
