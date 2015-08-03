package com.ttc.ch2.cucumber.rest.brochure;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;


@RunWith(Cucumber.class)
@CucumberOptions(format = { "pretty", "html:target/BrochureRest", "json:target/BrochureRest.json" })
public class BrochureRestCucumberTest {

	
}