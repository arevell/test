package com.ttc.ch2.cucumber.rest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.ttc.ch2.cucumber.rest.brochure.BrochureRestCucumberTest;
import com.ttc.ch2.cucumber.rest.cr.CrRestCucumberTest;
import com.ttc.ch2.cucumber.rest.crpesy.CrRestPesCucumberTest;
import com.ttc.ch2.cucumber.rest.outgoingarchive.OutgoingArchiveRestCucumberTest;


@RunWith(Suite.class)
@SuiteClasses({ BrochureRestCucumberTest.class,
				CrRestCucumberTest.class,
				CrRestPesCucumberTest.class,
				OutgoingArchiveRestCucumberTest.class,
	            })
public class TestAllCucumberRest {

}

