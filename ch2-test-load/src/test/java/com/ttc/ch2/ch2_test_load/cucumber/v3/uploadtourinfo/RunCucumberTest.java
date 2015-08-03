package com.ttc.ch2.ch2_test_load.cucumber.v3.uploadtourinfo;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(format={"pretty", "html:target/cucumber", "json:target/cucumber.json"})
public class RunCucumberTest {
}