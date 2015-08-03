package com.ttc.ch2.cucumber.systemcompare.ch1vsch2;

import org.junit.Ignore;
import org.junit.runner.RunWith;

import com.ttc.ch2.cucumber.Features;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;


@RunWith(Cucumber.class)
@CucumberOptions(format = { "pretty", "html:target/SystemComparerCh2vsCh1CucumberTest", 
		                              "json:target/SystemComparerCh2vsCh1CucumberTest.json" },
features={Features.PATH+"systemcompare/SystemComparerCh2vsCh1CucumberTest.feature"})
public class SystemComparerCh2vsCh1CucumberTest {
}