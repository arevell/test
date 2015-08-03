package com.ttc.ch2.cucumber.searchtoursaggregated;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(format={"pretty", "html:target/SearchToursAggregated", "json:target/SearchToursAggregated.json"})
public class SearchToursAggregatedTest {
}