package com.ttc.ch2.ui.moduls.audit.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.time.DateUtils;

import com.ttc.ch2.domain.common.QueryCondition;
import com.ttc.ch2.ui.common.AbstractFilterPagingListModel;


public class LoggingsModel extends AbstractFilterPagingListModel<LoggingEvent> {

    private static final long serialVersionUID = -1070933550377639125L;

    private static List<LoggingEvent> events = new ArrayList<>();
    static {List<LoggingEvent> list = new ArrayList<>();
        for (int i = 0; i < 25; i++) {
            LoggingEvent item = new LoggingEvent();
            item.setUser("User" + i);
            item.setStart(DateUtils.addMilliseconds(new Date(), (int) -(System.currentTimeMillis() % 1000L * 60 * 60)));
            item.setAddress("127.0.0.1");
            list.add(item);
        }
        events = list;
    }
	private static final int PAGE_SIZE=4;
	
	public LoggingsModel(int activePage) {
	    super(activePage, PAGE_SIZE, null);
	    loadData(null);
	}
		
	public LoggingsModel(QueryCondition condition,LoggingEvent filter) {
		super(0, PAGE_SIZE,filter);
		loadData(condition);
	}
	
	public LoggingsModel(QueryCondition condition,LoggingEvent filter,int activePage) {
		super(activePage, PAGE_SIZE,filter);
		loadData(condition);
	}

	@Override
	public int getTotalSize() {
	    List<LoggingEvent> list = getList();
	    if(list == null) {
	        return 0;
	    }
	    
        return list.size();
	}

	@Override
	protected List<LoggingEvent> getPageData(QueryCondition conditions, LoggingEvent filter) {
	    List<LoggingEvent> list = getList();
	    if (list == null) {
            return Collections.emptyList();
        }
	    
	    int page = getActivePage();
	    
	    int first = page*PAGE_SIZE;
	    int last = Math.min(first+PAGE_SIZE, list.size());
	    
        return  list.subList(first, last);
	}

    @Override
    protected void storeSortCondition(QueryCondition condition) {        
    }
   
    List<LoggingEvent> getList() {
        return events;
    }
}
