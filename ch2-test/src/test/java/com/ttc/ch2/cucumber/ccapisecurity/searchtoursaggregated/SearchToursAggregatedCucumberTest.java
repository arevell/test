package com.ttc.ch2.cucumber.ccapisecurity.searchtoursaggregated;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ttc.ch2.common.SampleGenerator;
import com.ttc.ch2.dao.security.UserCCAPIDAO;
import com.ttc.ch2.domain.user.UserCCAPI;

@RunWith(Cucumber.class)
@CucumberOptions(format = { "pretty", "html:target/CcapiSecuritySearchToursAggregated", "json:target/CcapiSecuritySearchToursAggregated.json" })
public class SearchToursAggregatedCucumberTest {

	
}