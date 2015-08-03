package com.ttc.ch2.cucumber.getbrochure;

import org.junit.Ignore;
import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;


@Ignore // to repair by pawel / lukasz 
@RunWith(Cucumber.class)
@CucumberOptions(format={"pretty", "html:target/GetBrochures", "json:target/GetBrochures.json"})
public class GetBrochuresTest {
}