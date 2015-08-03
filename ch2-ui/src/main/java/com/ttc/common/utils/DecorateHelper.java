package com.ttc.common.utils;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.UnhandledException;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;

public class DecorateHelper {
	
	
	public static String listToString(Collection<Object> list,String field)
	{
		String result=Joiner.on(",").join(Sets.newTreeSet(Iterables.transform(list, new ProperetyTransformer(field))));
		return result;
	}
		
	static class  ProperetyTransformer implements Function<Object,String>
	{		
		private String field;		
		public ProperetyTransformer(String field) {
			super();
			this.field=field;
		}
		
		@Override
		public String apply(Object input) {
			try {
				return (String) PropertyUtils.getSimpleProperty(input, field);
			} catch (IllegalAccessException e) {
				throw new UnhandledException(e);
			} catch (InvocationTargetException e) {
				throw new UnhandledException(e);
			} catch (NoSuchMethodException e) {
				throw new UnhandledException(e);
			}
		}	
	}
}
