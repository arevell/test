package com.ttc.ch2.ch2_test_load.cucumber.v3.gettourdetailsfull;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

import com.ttc.ch2.ch2_test_load.cucumber.Features;

@RunWith(Cucumber.class)
@CucumberOptions(format={"pretty", "html:target/cucumber", "json:target/cucumber.json"},
		features={Features.PATH+"v3/gettourdetailsfull/GetTourDetailsFullLoadTest.feature"})
public class GetTourDetailsFullLoadTest {
}