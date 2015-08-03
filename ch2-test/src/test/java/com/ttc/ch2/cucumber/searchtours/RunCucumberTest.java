package com.ttc.ch2.cucumber.searchtours;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(format={"pretty", "html:target/SearchTours", "json:target/SearchTours.json"})
public class RunCucumberTest {
}