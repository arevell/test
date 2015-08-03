package com.ttc.ch2.ui.tour;

import junit.framework.Assert;

import org.junit.Test;

import com.ttc.ch2.ui.common.exceptions.TourValidatorException;
import com.ttc.ch2.ui.common.validators.TourValidator;

public class TourValidatorTest {

	private static final String wrongTour1 = "#assdffg12";
	private static final String wrongTour2 = "assdffg12%";
	private static final String wrongTour3 = "#assdffg12";
	private static final String okTour = "ASD01";
	
	@Test
	public void validatorTest1() {
		try {
			TourValidator.validate(wrongTour1);
			Assert.fail("Validator failed");
		} catch(TourValidatorException e) {	}
	}
	
	@Test
	public void validatorTest2() {
		try {
			TourValidator.validate(wrongTour2);
			Assert.fail("Validator failed");
		} catch(TourValidatorException e) {	}
	}
	@Test
	public void validatorTest3() {
		try {
			TourValidator.validate(wrongTour3);
			Assert.fail("Validator failed");
		} catch(TourValidatorException e) {	}
	}
	
	@Test
	public void validatorTest4() {
		try {
			TourValidator.validate(okTour);
		} catch(TourValidatorException e) {	Assert.fail("Validator failed"); }
	}
}
