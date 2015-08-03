package com.ttc.ch2.cucumber.rest.cr;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(format = { "pretty", "html:target/CrRest", "json:target/CrRest.json" })
public class CrRestCucumberTest {

	
}