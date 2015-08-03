package com.ttc.ch2.domain.common;

import java.util.List;

import com.google.common.collect.Lists;

public class QueryCondition 
{
	private int startNumberItem=-1;	
	private int pageSize=-1;
	private boolean isEqualsPrefered=false;
	private List<SortCondition> sortConditions;
		
	public QueryCondition()
	{
		super();
		this.sortConditions=Lists.newArrayList();
	}
	
	public QueryCondition(int startNumberItem, int pageSize) {
		this();
		this.startNumberItem = startNumberItem;
		this.pageSize = pageSize;
	}

	public QueryCondition(int startNumberItem, int pageSize,SortCondition sortCondition) 
	{
		this(startNumberItem, pageSize);
		this.sortConditions=Lists.newArrayList(sortCondition);
	}
	
	public void setSortConditions(List<SortCondition> sortConditions) {
		this.sortConditions = sortConditions;
	}

	public int getStartNumberItem() {
		return startNumberItem;
	}

	public int getPageSize() {
		return pageSize;
	}

	public List<SortCondition> getSortConditions() {
		return sortConditions;
	}

	public boolean isEqualsPrefered() {
		return isEqualsPrefered;
	}

	public void setEqualsPrefered(boolean isEqualsPrefered) {
		this.isEqualsPrefered = isEqualsPrefered;
	}
}
