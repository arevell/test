package com.ttc.ch2.ui.common;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.google.common.collect.Sets;

public class CheckBoxModel<T> {

	private Set<CheckElement<T>> list;

		
	public CheckBoxModel(List<T> list)
	{
		this.list=Sets.newHashSet();
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			T t = (T) iterator.next();
			CheckElement<T> e=new CheckElement<T>((T)t, Boolean.FALSE);			
			list.add((T) e);
		}
	}
	
	
	class CheckElement<T>
	{
		private T element;		
		private Boolean check;
	
		
		public CheckElement(T element, Boolean check) {
			super();
			this.element = element;
			this.check = check;
		}
		
		
		public T getElement() {
			return element;
		}
		public void setElement(T element) {
			this.element = element;
		}
		public Boolean getCheck() {
			return check;
		}
		public void setCheck(Boolean check) {
			this.check = check;
		}
	}


	public Set<CheckElement<T>> getList() {
		return list;
	}


	public void setList(Set<CheckElement<T>> list) {
		this.list = list;
	}
}
