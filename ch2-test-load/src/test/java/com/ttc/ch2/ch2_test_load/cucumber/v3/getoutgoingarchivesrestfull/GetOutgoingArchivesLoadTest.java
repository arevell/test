package com.ttc.ch2.ch2_test_load.cucumber.v3.getoutgoingarchivesrestfull;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

import com.ttc.ch2.ch2_test_load.cucumber.Features;

@RunWith(Cucumber.class)
@CucumberOptions(
		format={"pretty", "html:target/cucumber", "json:target/cucumber.json"},
		features={Features.PATH+"v3/getoutgoingarchivesrestfull/GetOutgoingArchivesLoadTest.feature"})
public class GetOutgoingArchivesLoadTest {
}