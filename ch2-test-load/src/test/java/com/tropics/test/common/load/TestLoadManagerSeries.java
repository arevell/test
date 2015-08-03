package com.tropics.test.common.load;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Ignore;
import org.slf4j.Logger;

@Ignore
public class TestLoadManagerSeries extends TestLoadManager {

	//private static Log logger = LogFactory.getLog(TestLoadManagerSeries.class);
	private static Logger logger;

	private LoadRunnerParamsList loadRunnerParamsList;

	public TestLoadManagerSeries(TestLoad test, Logger logger) {
		super(test, logger);
	}

	public void loadTest(LoadRunnerParamsList loadRunnerParamsList) {
		this.loadRunnerParamsList = loadRunnerParamsList;
		// running a set of managers one by one - not concurrently 
		for (int i = 0; i < loadRunnerParamsList.size(); i++) {
			LoadRunnerParams loadRunnerParams = loadRunnerParamsList.get(i);
			loadTest(loadRunnerParams);
		}
	}

	public String getResultReport(boolean withHeader) {
		if (loadRunnerParamsList == null)
			throw new RuntimeException("load method has to be called before getting results");

		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < loadRunnerParamsList.size(); i++) {
			LoadRunnerParams loadRunnerParams = loadRunnerParamsList.get(i);
			if (i == 0 && withHeader) {
				buffer.append(loadRunnerParams.getResultReportHeader());
			}
			buffer.append("\n");
			buffer.append(loadRunnerParams.getResultReport());
		}
		return buffer.toString();
	}

}
