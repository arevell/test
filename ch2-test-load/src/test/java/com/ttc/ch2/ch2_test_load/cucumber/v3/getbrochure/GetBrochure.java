package com.ttc.ch2.ch2_test_load.cucumber.v3.getbrochure;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tropics.test.common.load.LoadRunnerParams;
import com.tropics.test.common.load.TestLoad;
import com.tropics.test.common.load.TestLoadManager;
import com.ttc.ch2.ch2_test_load.cucumber.DBClient;
import com.ttc.ch2.ch2_test_load.cucumber.v3.GetBrochureTest;

import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class GetBrochure {
	private static final Logger logger = LoggerFactory.getLogger(GetBrochure.class);
	
	private int callsPeriod;
	private int callsPerThread;
	private int numberOfThreads;
	private LoadRunnerParams loadTestParams;
	private TestLoadManager testManager;
	private String sellingCompanyCode;
	private String tourCode;
	private String brandCode;
	private boolean testResults=true;
	
	@Before("@Fetching_data_from_database_before_execution_of_test")
	public void obtain_current_data_from_db() throws IOException, ClassNotFoundException, SQLException{
		DBClient dbc = new DBClient();
		Map<String,Object> params = dbc.getBrochuresParams();
	    sellingCompanyCode = (String)params.get("sccode");
	    tourCode = (String)params.get("tourcode");
	    brandCode = (String)params.get("brandcode");
	}
	
	@Given("^Number of threads (.*)")
	public void populate_number_of_resocrds(int numberOfThreads) throws Throwable {
		this.numberOfThreads = numberOfThreads;
	}
	
	@Given("^calls per thread (.*)")
	public void populate_calls_per_thread(int callsPerThread) throws Throwable {
		this.callsPerThread = callsPerThread;
	}
	
	@Given("^calls period (.*)")
	public void populate_calls_period(int callsPeriod) throws Throwable {
		this.callsPeriod = callsPeriod;
	}
		
	@When("^I request for the get brochure load test")
	public void execute_search_tours_load_test() {
		loadTestParams = new LoadRunnerParams(numberOfThreads, callsPeriod, callsPerThread);
		final GetBrochureTest getBrochureTest = new GetBrochureTest(brandCode, sellingCompanyCode, tourCode); 
		TestLoad test = new TestLoad() {
			public void executeLoad() {
				getBrochureTest.testCcapiV3GetBrochure();
			}
		};
		testManager = new TestLoadManager(test, logger);
		testResults = testManager.loadTest(loadTestParams);
	}
	
	@Then("^Test result is valid")
	public void check_test_result() {
		assertTrue(testResults);
	}
	
	@And("^Max load test duration (.*)")
	public void compare_test_duration(Long testDuration) throws Throwable {
	    assertThat(testManager.getLoadTestDuration(), lessThanOrEqualTo(testDuration));
	}

	@And("^Average execution time (.*)")
	public void tour_code(Double avgExecTime) throws Throwable {
	    assertThat(testManager.getLoadTestCallAvgExecTime(), lessThanOrEqualTo(avgExecTime));
	}	
}
