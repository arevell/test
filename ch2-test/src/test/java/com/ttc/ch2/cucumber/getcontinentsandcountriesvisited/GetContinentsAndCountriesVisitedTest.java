package com.ttc.ch2.cucumber.getcontinentsandcountriesvisited;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

//@Ignore // to repair by pawel / lukasz
@RunWith(Cucumber.class)
@CucumberOptions(format={"pretty", "html:target/GetContinentsAndCountriesVisited", "json:target/GetContinentsAndCountriesVisited.json"})
public class GetContinentsAndCountriesVisitedTest {
}