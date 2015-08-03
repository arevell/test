package com.ttc.ch2.ui.common;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.zkoss.zul.AbstractListModel;
import org.zkoss.zul.FieldComparator;
import org.zkoss.zul.ListitemComparator;
import org.zkoss.zul.event.ListDataEvent;
import org.zkoss.zul.ext.Sortable;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.ttc.ch2.domain.common.QueryCondition;
import com.ttc.ch2.domain.common.SortCondition;

public abstract class AbstractFilterPagingLenghtListModel<T> extends AbstractListModel<T> implements Sortable<T>
{
		
	private static final long serialVersionUID = -6273935766234075839L;
	
	protected int activePage;
	protected int pageSize;
	protected int itemStartNumber;
	protected T filter;
	
	protected List<T> items = new ArrayList<T>();
	
	public AbstractFilterPagingLenghtListModel(int activePage, int pageSize,T filter) {
		super();
		this.filter=filter;
		this.activePage = activePage;
		this.pageSize = pageSize;
		this.itemStartNumber = activePage * pageSize;
	}
	
	public abstract int getTotalSize();
	protected abstract List<T> getPageData(QueryCondition conditions,T filter);
	protected abstract void storeSortCondition(QueryCondition condition);

	
	public AbstractFilterPagingLenghtListModel<T> loadData(QueryCondition condition){		
		if(condition==null)
			condition=new QueryCondition(itemStartNumber, pageSize);
		
		QueryCondition conditionLocal=new QueryCondition(itemStartNumber, pageSize);
		conditionLocal.setSortConditions(condition.getSortConditions());
			
		this.items=getPageData(conditionLocal,filter);
		return this;
	}
	
	public AbstractFilterPagingLenghtListModel<T> loadData()
	{
		this.items=getPageData(new QueryCondition(itemStartNumber, pageSize),filter);
		return this;
	}
	
	
	@Override
	public void sort(Comparator<T> cmpr, boolean ascending) {
	
		if(cmpr instanceof FieldComparator)
		{
			FieldComparator f=(FieldComparator) cmpr;
			
			if(Lists.newArrayList(Splitter.on(",").split(f.getRawOrderBy())).size()>1)
			{
				throw new UnsupportedOperationException("To do - unexpected situation - please implement this functionality");
			}			
			QueryCondition conditions=new QueryCondition(itemStartNumber, pageSize,new SortCondition(f.getRawOrderBy(), ascending ? SortCondition.Direction.ASC : SortCondition.Direction.DESC));
			this.items=getPageData(conditions,filter);
			storeSortCondition(conditions);
		}
		else if(cmpr instanceof ListitemComparator)
		{
			QueryCondition conditions=new QueryCondition(itemStartNumber, pageSize,new SortCondition("tourCode", ascending ? SortCondition.Direction.ASC : SortCondition.Direction.DESC));
			this.items=getPageData(conditions,filter);
			storeSortCondition(conditions);
		}
		else
		{
			throw new UnsupportedOperationException("To do not used untill now - please implement this functionality");
		}
		fireEvent(ListDataEvent.CONTENTS_CHANGED, -1, -1);
	}
	
	@Override
	public String getSortDirection(Comparator<T> cmpr) {
		throw new UnsupportedOperationException("To do not used untill now - please implement this functionality");
	}
	
	
	@Override
	public T getElementAt(int index) {
		return items.get(index);
	}

	@Override
	public int getSize() {
		return items.size();
	}
	
	public int getActivePage() {
		return this.activePage;
	}
	
	public int getPageSize() {
		return this.pageSize;
	}
	
	public int getItemStartNumber() {
		return itemStartNumber;
	}

	public void setActivePage(int activePage) {
		this.activePage = activePage;
	}
	

}
