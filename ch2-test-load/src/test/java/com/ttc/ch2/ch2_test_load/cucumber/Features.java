package com.ttc.ch2.ch2_test_load.cucumber;

import java.net.InetAddress;
import java.net.UnknownHostException;


public class Features {

	//public final static String PATH = "target/test-classes/com/ttc/ch2/ch2_test_load/cucumber/";
	public final static String PATH = "classpath:com/ttc/ch2/ch2_test_load/cucumber/";

	public static void main(String[] args) throws UnknownHostException {
		InetAddress addr = InetAddress.getLocalHost();
		String localhostname = addr.getHostName();
		String canhostname = addr.getCanonicalHostName();
		String iphostname = addr.getHostAddress();
		
		System.out.println(localhostname + " / " + canhostname + " / " + iphostname);
	}
	
}
