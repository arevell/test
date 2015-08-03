package com.ttc.ch2.ui.moduls.audit.common;

import java.util.Collections;
import java.util.List;

import com.ttc.ch2.domain.common.QueryCondition;
import com.ttc.ch2.ui.common.AbstractFilterPagingListModel;
import com.ttc.ch2.ui.moduls.audit.Period;


public class MetricsModel extends AbstractFilterPagingListModel<MetricsAggregate> {
    private static final long serialVersionUID = -1107469035070974201L;
    
    private AuditUiManager auditManager;
    
    private Period type;
    
	private static final int PAGE_SIZE=4;
	
	public MetricsModel(AuditUiManager auditManager, String type, int activePage) {
	    super(activePage, PAGE_SIZE, null);
	    this.auditManager = auditManager;
	    loadData(null);
	}
		
	public MetricsModel(AuditUiManager auditManager, String type, QueryCondition condition,MetricsAggregate filter) {
		super(0, PAGE_SIZE,filter);
        this.auditManager = auditManager;
		loadData(condition);
	}
	
	public MetricsModel(AuditUiManager auditManager, String type, QueryCondition condition, MetricsAggregate filter, int activePage) {
		super(activePage, PAGE_SIZE,filter);
        this.auditManager = auditManager;
		loadData(condition);
	}

	@Override
	public int getTotalSize() {
	    List<MetricsAggregate> list = auditManager.readMetrics(type);
	    if(list == null) {
	        return 0;
	    }
	    
        return list.size();
	}

	@Override
	protected List<MetricsAggregate> getPageData(QueryCondition conditions, MetricsAggregate filter) {
	    List<MetricsAggregate> list = auditManager.readMetrics(type);
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
   
}
