package com.ttc.ch2.ch2_test_load.cucumber.v3.searchtoursaggregated;

import org.junit.runner.RunWith;

import com.ttc.ch2.ch2_test_load.cucumber.Features;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		format={"pretty", "html:target/cucumber", "json:target/cucumber.json"},
		features={Features.PATH+"v3/searchtoursaggregated/SearchToursAggregatedPeakTest.feature"})
public class SearchToursAggregatedPeakTest {
}