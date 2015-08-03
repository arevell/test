package com.ttc.ch2.cucumber.rest.crpesy;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(format = { "pretty", "html:target/CrRestPes", "json:target/CrRestPes.json" })
public class CrRestPesCucumberTest {

	
}