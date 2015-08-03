package com.ttc.ch2.cucumber.tourdeparturegen;

import org.junit.Ignore;
import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

/**need refactoring by Pawel*/
@Ignore
@RunWith(Cucumber.class)
@CucumberOptions(format={"pretty", "html:target/TourDepartureGen", "json:target/TourDepartureGen.json"})
public class TourDepartureGenCucumberTest {
}