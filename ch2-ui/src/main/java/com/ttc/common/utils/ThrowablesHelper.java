package com.ttc.common.utils;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.google.common.base.Predicate;
import com.google.common.base.Throwables;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

public class ThrowablesHelper {

	
	public static boolean throwableExist(Class<? extends Throwable> throwableClazz,Throwable throwable){
		List<Throwable> l= Throwables.getCausalChain(throwable);
		return Iterables.tryFind(l, new ThrowableClassFinder(throwableClazz)).isPresent();
	}
	
	public static List<String> getAllMessages(Throwable throwable){
		List<Throwable> l= Throwables.getCausalChain(throwable);
		List<String> msgs=Lists.newArrayList();
		for (Throwable t : l) {			
			if(StringUtils.isEmpty(t.getMessage())){				
					msgs.add(t.getClass().getName());				
			}else{
				msgs.add(t.getMessage());	
			}
		}
		return msgs;
	}
	
	private static class ThrowableClassFinder implements Predicate<Throwable>{

		Class<? extends Throwable> throwableClazz;
		
		public ThrowableClassFinder(Class<? extends Throwable> throwableClazz) {
			super();
			this.throwableClazz = throwableClazz;
		}

		@Override
		public boolean apply(Throwable input) {
			return input.getClass().equals(throwableClazz) ;
		}
		
	}
}
