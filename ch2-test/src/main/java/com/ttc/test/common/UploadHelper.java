package com.ttc.test.common;

import java.util.Date;
import java.util.Random;

import org.apache.commons.lang.time.DateUtils;

import com.ttc.ch2.common.DateHelper;

public class UploadHelper {

	private static Random rnd=new Random();
	public static String genFileName(){		
		return genFileName("CH");
	}
	
	public static String genFileName(String brandCode){		
		int h=rnd.nextInt(11);	
		int m=rnd.nextInt(59);	
		int s=rnd.nextInt(59);	
		Date d=new Date();
		d=DateUtils.setHours(d, h);
		d=DateUtils.setMinutes(d, m);
		d=DateUtils.setSeconds(d, s);		
//		CH-2014-Jul-24T09-48-53Z.zip
		StringBuilder sb=new StringBuilder();
		sb.append(brandCode+"-"+DateHelper.dateToString(d,"yyyy-MM-dd'T'HH-mm-ss")).append("Z").append(".zip");
		return sb.toString();
	}
}
