package com.ttc.ch2.cucumber.tourdeparturegen;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(format={"pretty", "html:target/TourDepartureGen", "json:target/TourDepartureGen.json"})
public class TourDepartureGenCucumberTest {
}