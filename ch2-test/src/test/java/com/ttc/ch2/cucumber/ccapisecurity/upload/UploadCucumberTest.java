package com.ttc.ch2.cucumber.ccapisecurity.upload;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(format = { "pretty", "html:target/CcapiSecurityUpload", "json:target/CcapiSecurityUpload.json" })
public class UploadCucumberTest {

	
}