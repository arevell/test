package com.tropics.test.common.load;

import java.util.HashMap;
import java.util.Map;

import org.junit.Ignore;

@Ignore
class TestLoadThreadsMap {

	private Map<Integer, Object[]> threadsDefs = new HashMap<Integer, Object[]> ();

	public Thread getThread(Integer key) {
		return (Thread)threadsDefs.get(key)[0];
	}
	public TestLoadRunner getRunner(Integer key) {
		return (TestLoadRunner)threadsDefs.get(key)[1];
	}

	public Object[] put(Integer key, TestLoadRunner testRunner) {
		return threadsDefs.put(key, new Object[] {new Thread(testRunner), testRunner});
	}

	public int size() {
		return threadsDefs.size();
	}

//	public boolean containsKey(Object key) {
//		return threadsDefs.containsKey(key);
//	}

//	public boolean containsThread(Thread thread) {
//		return threadsDefs.containsValue(thread);
//	}

}
