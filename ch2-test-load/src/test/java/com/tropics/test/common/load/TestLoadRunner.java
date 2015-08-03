package com.tropics.test.common.load;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Ignore;

@Ignore
public class TestLoadRunner implements Runnable {

	private static Log logger = LogFactory.getLog(TestLoadRunner.class);

	//private TestSuite testSuite;
	private TestLoad test;
	private int threadId;
	private int callsPerThread;
	private int testCallsPeriod;
	
	private int testRunErrors = 0;
	
	private List<Integer> calls = new ArrayList<Integer>();
	private List<Long> callsExecTimesMS = new ArrayList<Long>();
	
	public TestLoadRunner(TestLoad test, int threadId, int callsPerThread, int testCallsPeriod) {
		this.test = test;
		this.threadId = threadId;
		this.callsPerThread = callsPerThread;
		this.testCallsPeriod = testCallsPeriod;
		prepareCallsDistribution();
	}

	private void prepareCallsDistribution() {
		for (int i = 0; i < callsPerThread; i++) {
			calls.add(new Integer(Math.round(testCallsPeriod * (float) Math.random())));
			callsExecTimesMS.add(0L);
		}
		Collections.sort(calls);
	}

	public void run() {
		logger.trace("run - start - " + threadId);
		for (int i = 0; i < calls.size(); i++) {
			int currentCallTime = (i > 0) ? (calls.get(i - 1)).intValue() : 0;
			int nextCallTime = (calls.get(i)).intValue();
			int delay = nextCallTime - currentCallTime;
			TestLoadManager.sleep(delay);
			logger.trace("run - start - treadId: " + threadId + " call: " + i + " test suite; start: " + nextCallTime + "ms");
			// System.out.println("run - start - treadId: " + threadId + " call: " + i + " test suite; start: " + nextCallTime + "ms");
			long start = System.currentTimeMillis();
			//testSuite.run(new TestResult()); // TODO in the future - redirect testResults and store them
			try {
				test.executeLoad();
				// in case of an error thrown from the tested method, carry on the execution, but mark the load-test run as ending with an error 
				// in order to limit the intrusiveness error information are not stored in the process memory 
			} catch (Error e) {
				//if (verbose) // TODO
				logger.debug("An error occured when callign a test method: " + e, e);
				testRunErrors++;
			} catch (Exception e) {
				//if (verbose) // TODO
				logger.debug("An exception occured when callign a test method: " + e, e);
				testRunErrors++;
			}
			long end = System.currentTimeMillis();
			callsExecTimesMS.add(end - start);
			logger.trace("run - start - treadId: " + threadId + " call: " + i + " test suite; start: " + nextCallTime + "ms, execution time: " + (end - start) + "ms");
			//System.out.println("run - start - treadId: " + threadId + " call: " + i + " test suite; start: " + nextCallTime + "ms, execution time: " + (end - start) + "ms");
		}
		logger.trace("run - end - " + threadId);
	}
	
	double getRunnerAvgExecTimes() {
		long sum = 0;
		for (int i = 0; i < callsExecTimesMS.size(); i++) {
			sum += callsExecTimesMS.get(i);
		}
		return sum / callsExecTimesMS.size();
	}
	
	double getRunnerTotalErrors() {
		return testRunErrors;
	}
	double getRunnerAvgErrors() {
		return testRunErrors / callsPerThread;
	}

}