package com.ttc.ch2.cucumber.systemcompare.ch2vshabs;

import org.junit.runner.RunWith;

import com.ttc.ch2.cucumber.Features;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;


@RunWith(Cucumber.class)
@CucumberOptions(format = { "pretty", "html:target/SystemComparerCh2vsHabsCucumberTest", 
		                              "json:target/SystemComparerCh2vsHabsCucumberTest.json" },
features={Features.PATH+"systemcompare/SystemComparerCh2vsHabsCucumberTest.feature"})
public class SystemComparerCh2vsHabsCucumberTest {
}