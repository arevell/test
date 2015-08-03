package com.ttc.ch2.cucumber.ccapisecurity.getbrochure;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;


@RunWith(Cucumber.class)
@CucumberOptions(format = { "pretty", "html:target/CcapiSecurityGetBrochure", "json:target/CcapiSecurityGetBrochure.json" })
public class GetBrochureCucumberTest {

	
}