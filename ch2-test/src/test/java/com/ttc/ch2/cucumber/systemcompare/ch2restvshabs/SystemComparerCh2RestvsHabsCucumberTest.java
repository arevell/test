package com.ttc.ch2.cucumber.systemcompare.ch2restvshabs;

import org.junit.runner.RunWith;

import com.ttc.ch2.cucumber.Features;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;


@RunWith(Cucumber.class)
@CucumberOptions(format = { "pretty", "html:target/SystemComparerCh2RestvsHabsCucumberTest", 
		                              "json:target/SystemComparerCh2RestvsHabsCucumberTest.json" },
features={Features.PATH+"systemcompare/SystemComparerCh2RestvsHabsCucumberTest.feature"})
public class SystemComparerCh2RestvsHabsCucumberTest {
}