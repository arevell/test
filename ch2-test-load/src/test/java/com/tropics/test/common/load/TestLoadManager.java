package com.tropics.test.common.load;

import org.junit.Ignore;
import org.slf4j.Logger;

@Ignore
public class TestLoadManager {

	//private static Log logger = LogFactory.getLog(TestLoadManager.class);
	private Logger logger;
	
	private TestLoad test;

	long loadTestDuration = 0L;
	double loadTestCallAvgExecTime = 0.0;
	double loadTestCallAvgErrors = 0.0;
	int loadTestCallTotalErrors = 0;
	
	public TestLoadManager(TestLoad test, Logger logger) {
		this.test = test;
		this.logger = logger;
	}

	public boolean loadTest(LoadRunnerParams loadRunnerParams) {
		int threads = loadRunnerParams.getThreads();
		int callsPeriod = loadRunnerParams.getCallsPeriod();
		int callsPerThread = loadRunnerParams.getCallsPerThread();
		int threadDeltaTime = loadRunnerParams.getThreadDeltaTime();
		loadTest(threads, callsPeriod, callsPerThread, threadDeltaTime);
		loadRunnerParams.setLoadTestDuration(loadTestDuration);
		loadRunnerParams.setCallAvgExecTime(loadTestCallAvgExecTime);
		
		if (logger != null) {
			//if(loadTestCallAvgErrors > 0)
			logger.info(String.format("While running the load test; errors thrown: %s, average error rate: %4.4f", loadTestCallTotalErrors, loadTestCallAvgErrors));
			
			logger.info(loadRunnerParams.getResultReportHeader());
			logger.info(loadRunnerParams.getResultReport());
		}
		return loadTestCallTotalErrors == 0;
	}

	private long loadTest(int threads, int callsPeriod, int callsPerThread, int threadDeltaTime) {
		TestLoadThreadsMap loadThreadsMap = new TestLoadThreadsMap();
		// creating definitions of test threads
		logger.trace("loadTest - start - creating definitions of test threads");
		for (int i = 0; i < threads; i++) {
			// creating a thread
			TestLoadRunner testRunner = new TestLoadRunner(test, i, callsPerThread, callsPeriod);
			loadThreadsMap.put(new Integer(i), testRunner);
		}
		// running all threads -- core of the load test
		loadTestDuration = runThreads(loadThreadsMap, threadDeltaTime);

		// after successful load test get averages of execution times and errors faced per thread, and compute averages for the entire load test
		long sum = 0;
		int sumErrors = 0;
		for (int i = 0; i < loadThreadsMap.size(); i++) {
			TestLoadRunner testRunner = loadThreadsMap.getRunner(new Integer(i));
			sum += testRunner.getRunnerAvgExecTimes();
			sumErrors += testRunner.getRunnerTotalErrors();
		}
		loadTestCallAvgExecTime = sum / threads;
		loadTestCallTotalErrors = sumErrors;
		loadTestCallAvgErrors = (double)sumErrors / (threads * callsPerThread);
		
		logger.trace(String.format("loadTest - finished in : %s ms, average execution time of a call: %s ms, errors: %s", loadTestDuration, loadTestCallAvgExecTime, loadTestCallAvgErrors));
		return loadTestDuration;
	}

	private long runThreads(TestLoadThreadsMap loadThreadsMap, int threadDeltaTime) {
		long startTime = System.currentTimeMillis();
		// starting test threads
		logger.trace("loadTest - runThreads - starting test threads");
		for (int i = 0; i < loadThreadsMap.size(); i++) {
			// adding a new thread every delta time [ms] - increasing load
			sleep(threadDeltaTime);
			// taking a thread definition to run it
			Thread t = loadThreadsMap.getThread(new Integer(i));
			t.start();
		}
		// waiting with main thread for running test threads
		logger.trace("loadTest - runThreads - waiting with main thread for running test threads");
		for (int i = 0; i < loadThreadsMap.size(); i++) {
			Thread t = loadThreadsMap.getThread(new Integer(i));
			try {
				t.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		long endTime = System.currentTimeMillis();
		logger.trace("loadTest - runThreads - finished all threads joined main thread");
		
		return endTime - startTime; // execution time of the entire load test -- good for stress tests and measuring points of saturation
	}
	
	public long getLoadTestDuration() {
		return loadTestDuration;
	}
	public double getLoadTestCallAvgExecTime() {
		return loadTestCallAvgExecTime;
	}
	public double getLoadTestCallTotalErrors() {
		return loadTestCallTotalErrors;
	}

	public static void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void sleepRandom(long millis) {
		try {
			Thread.sleep(Math.round(millis * Math.random()));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
