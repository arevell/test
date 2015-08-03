package com.ttc.ch2.ui.common.security;

import java.util.Map;

import com.google.common.collect.Maps;



public class UserContext {
	
	private Map<String,Object> context=Maps.newHashMap();

	public UserContext putObj(String key, Object value) {
		context.put(key, value);
		return this;
	}
	
	public Object getObject(String key) {
		return context.get(key);
	}
	

	public interface UserContextStaticName {
		public static final String LOGED_USER = "LOGED_USER";
		public static final String SORT_CONDITION_CR = "SORT_CONDITION_CR";
		public static final String SORT_CONDITION_USER = "SORT_CONDITION_USER";
		public static final String EXCEPTION_MSG = "EXCEPTION_MSG";
		public static final String EXCEPTION = "EXCEPTION";
		public static final String RELOAD_PAGE = "RELOAD_PAGE";
		
				
		//messages
		public static final String SORT_CONDITION_COMMENTS = "SORT_CONDITION_COMMENTS";
		public static final String FORM_LIST_COMMENTS = "FORM_LIST_COMMENTS";
		public static final String SORT_CONDITION_MESSAGES = "SORT_CONDITION_MESSAGES";
		
		
		// jobs
		public static final String SORT_CONDITION_JOB = "SORT_CONDITION_JOB";
		public static final String FORM_LIST_JOB = "FORM_LIST_JOB";
		
		// upload									  
		public static final String SORT_CONDITION_UPLOAD = "SORT_CONDITION_UPLOAD";
		public static final String FORM_LIST_UPLOAD = "FORM_LIST_UPLOAD";
		public static final String SELECTED_VALUE_UPLOAD = "SELECTED_VALUE_UPLOAD";
		public static final String UPLOADED_FILE = "UPLOADED_FILE";
		public static final String UPLOADED_FILE_STATUS_MSG = "UPLOADED_FILE_STATUS_MSG";
		public static final String UPLOADED_BY_USER_FLAG = "UPLOADED_BY_USER_FLAG";
		
	}
	
}
