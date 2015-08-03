package com.ttc.ch2.cucumber.ccapisecurity;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.ttc.ch2.cucumber.ccapisecurity.getbrochure.GetBrochureCucumberTest;
import com.ttc.ch2.cucumber.ccapisecurity.getcontinentsandcountriesvisited.GetContinentsAndCountriesVisitedCucumberTest;
import com.ttc.ch2.cucumber.ccapisecurity.gettourcategories.GetTourCategoriesCucumberTest;
import com.ttc.ch2.cucumber.ccapisecurity.gettourdetailsfull.GetTourDetailsFullCucumberTest;
import com.ttc.ch2.cucumber.ccapisecurity.searchtours.SearchToursCucumberTest;
import com.ttc.ch2.cucumber.ccapisecurity.searchtoursaggregated.SearchToursAggregatedCucumberTest;
import com.ttc.ch2.cucumber.ccapisecurity.upload.UploadCucumberTest;


@RunWith(Suite.class)
@SuiteClasses({ SearchToursCucumberTest.class,
	            SearchToursAggregatedCucumberTest.class,
	            GetBrochureCucumberTest.class,
	            GetContinentsAndCountriesVisitedCucumberTest.class,
	            GetTourCategoriesCucumberTest.class,
	            GetTourDetailsFullCucumberTest.class,
	            UploadCucumberTest.class})
public class TestAllCucumberCCAPI {

}

