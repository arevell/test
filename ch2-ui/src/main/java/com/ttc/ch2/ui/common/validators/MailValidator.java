package com.ttc.ch2.ui.common.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MailValidator {

	 private Pattern pattern;
	    private Matcher matcher;
	 
	    private static final String PATTERN =".+@.+\\.[a-z]+";
	 
	    public MailValidator(){
		  pattern = Pattern.compile(PATTERN);
	    }
	 
	    public boolean validate(final String email){		  
		  matcher = pattern.matcher(email);
		  return matcher.matches();	    	    
	    }
	
}
