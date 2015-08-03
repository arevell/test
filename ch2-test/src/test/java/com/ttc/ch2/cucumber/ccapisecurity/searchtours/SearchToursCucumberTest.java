package com.ttc.ch2.cucumber.ccapisecurity.searchtours;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(format = { "pretty", "html:target/CcapiSecuritySearchTours", "json:target/CcapiSecuritySearchTours.json" })
public class SearchToursCucumberTest {

	
}