package com.tropics.test.common.load;

import org.junit.Ignore;

@Ignore
public class LoadRunnerParams {

	private int threads = 1000;
	private int callsPeriod = 10 * 1000;
	private int callsPerThread = 1;
	private int threadDeltaTime = 0;
	private long loadTestDuration = -1L;
	private double loadTestCallAvgExecTime = -1L;


	public LoadRunnerParams(int threads, int callsPeriod) {
		this.threads = threads;
		this.callsPeriod = callsPeriod;
		validateParameters();
	}

	public LoadRunnerParams(int threads, int callsPeriod, int callsPerThread) {
		this.threads = threads;
		this.callsPeriod = callsPeriod;
		this.callsPerThread = callsPerThread;
		validateParameters();
	}

	public LoadRunnerParams(int threads, int callsPeriod, int callsPerThread, int threadDeltaTime) {
		this.threads = threads;
		this.callsPeriod = callsPeriod;
		this.callsPerThread = callsPerThread;
		this.threadDeltaTime = threadDeltaTime;
		validateParameters();
	}

	private void validateParameters() {
		if (threads <= 0)
			throw new RuntimeException();
		if (callsPeriod <= 0)
			throw new RuntimeException();
		if (callsPerThread <= 0)
			throw new RuntimeException();
		if (threadDeltaTime < 0)
			throw new RuntimeException();
	}

	public int getThreads() {
		return threads;
	}

	public int getCallsPeriod() {
		return callsPeriod;
	}

	public int getCallsPerThread() {
		return callsPerThread;
	}

	public int getThreadDeltaTime() {
		return threadDeltaTime;
	}

	public long getLoadTestDuration() {
		return loadTestDuration;
	}
	public void setLoadTestDuration(long loadTestDuration) {
		if (loadTestDuration < 0)
			throw new RuntimeException("The result time cannot be negative");
		this.loadTestDuration = loadTestDuration;
	}
	
	public double getCallAvgExecTime() {
		return loadTestCallAvgExecTime;
	}
	public void setCallAvgExecTime(double callAvgExecTime) {
		this.loadTestCallAvgExecTime = callAvgExecTime;
	}

	/**
	 * @return Input Throughput [calls(threads) per second]
	 */
	public double getThroughputIput() {
		return (double) (threads * callsPerThread) / (callsPeriod * 1000);
	}

	/**
	 * @return Throughput [calls(threads) per second]
	 */
	public double getThroughput() {
		if (loadTestDuration < 0)
			throw new RuntimeException("The result time has not been set yet");
		return (double) (threads * callsPerThread) / (loadTestDuration * 1000);
	}

	public String getResultReportHeader() {
		StringBuffer report = new StringBuffer();
		report.append("threads");
		report.append(" \t");
		report.append("callsPeriod");
		report.append(" \t");
		report.append("callsPerThread");
		report.append(" \t");
		report.append("threadDeltaTime");
		report.append(" \t");
		report.append("loadTestDuration");
		report.append(" \t");
		report.append("callAvgExecTime");
		report.append(" \t");
		report.append("throughput");
		return report.toString();
	}

	public String getResultReport() {
		StringBuffer report = new StringBuffer();
		report.append(threads);
		report.append(" \t");
		report.append(callsPeriod);
		report.append(" \t");
		report.append(callsPerThread);
		report.append(" \t");
		report.append(threadDeltaTime);
		report.append(" \t");
		report.append(loadTestDuration);
		report.append(" \t");
		report.append(loadTestCallAvgExecTime);
		report.append(" \t");
		report.append(getThroughput());
		return report.toString();
	}

}
