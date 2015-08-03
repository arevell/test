package com.ttc.ch2.cucumber.ccapisecurity.gettourcategories;

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
@CucumberOptions(format = { "pretty", "html:target/CcapiSecurityGetTourCategories", "json:target/CcapiSecurityGetTourCategories.json" })
public class GetTourCategoriesCucumberTest {


    @BeforeClass
public static void blomba() throws Throwable {
    ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/META-INF/spring/testOnlyCtx.xml","classpath:/META-INF/spring/dbCtx.xml");
    
}
	
}