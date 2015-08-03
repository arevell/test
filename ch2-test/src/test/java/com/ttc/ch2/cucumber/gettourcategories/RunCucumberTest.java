package com.ttc.ch2.cucumber.gettourcategories;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(format={"pretty", "html:target/GetTourCategories", "json:target/GetTourCategories.json"})
public class RunCucumberTest {
}