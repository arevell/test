package com.tropics.test.common.load;

import java.util.ArrayList;

import org.junit.Ignore;

@Ignore
public class LoadRunnerParamsList {

	private ArrayList loadRunnerParamsList = new ArrayList();

	public LoadRunnerParamsList() {
	}

	public boolean add(LoadRunnerParams loadRunnerParams) {
		return loadRunnerParamsList.add(loadRunnerParams);
	}

	public LoadRunnerParams get(int index) {
		return (LoadRunnerParams) loadRunnerParamsList.get(index);
	}

	public int size() {
		return loadRunnerParamsList.size();
	}

}
