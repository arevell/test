package com.ttc.ch2.cucumber.rest.outgoingarchive;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(format = { "pretty", "html:target/OutgoingArchiveRest", "json:target/OutgoingArchiveRest.json" })
public class OutgoingArchiveRestCucumberTest {

	
}