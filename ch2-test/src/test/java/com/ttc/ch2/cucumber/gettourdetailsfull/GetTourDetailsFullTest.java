package com.ttc.ch2.cucumber.gettourdetailsfull;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(format={"pretty", "html:target/GetTourDetailsFull", "json:target/GetTourDetailsFull.json"})
public class GetTourDetailsFullTest {
}