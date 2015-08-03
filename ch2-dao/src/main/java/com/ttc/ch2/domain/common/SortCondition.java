package com.ttc.ch2.domain.common;

import java.util.List;

import com.google.common.collect.Lists;

public class SortCondition {

	public	enum Direction{
		ASC,DESC;
		
		public Direction invert(){
			return this==ASC ? DESC : ASC; 
		}
	}
		
	private Direction  direction;
	private String columnName;
	
	public SortCondition(String columnName,Direction direction) {
		this.direction = direction;
		this.columnName = columnName;
	}
	public Direction getDirection() {
		return direction;
	}
	public void setDirection(Direction direction) {
		this.direction = direction;
	}
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	
	public static List<SortCondition> getSortConditionList(SortCondition ... condition)
	{
		return Lists.newArrayList(condition);
	}		
}
