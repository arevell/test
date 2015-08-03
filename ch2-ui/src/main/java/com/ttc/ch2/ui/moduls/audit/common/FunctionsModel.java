package com.ttc.ch2.ui.moduls.audit.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;







import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.lang.StringUtils;
import org.elasticsearch.action.ActionFuture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ttc.ch2.domain.common.QueryCondition;
import com.ttc.ch2.ui.common.AbstractFilterPagingListModel;
import com.ttc.ch2.ui.moduls.audit.Period;


public class FunctionsModel extends AbstractFilterPagingListModel<AuditEvent> {

    private static final long serialVersionUID = -1070933550377639125L;
    
    private static Logger logger = LoggerFactory.getLogger(FunctionsModel.class);
    
    private Period period;


   private AuditUiManager auditManager;
  
	private static final int PAGE_SIZE=6;
	
    private String user;
    private String action;
    private String function;
    private String host;

    private List<AuditEvent> events;

    private List<String> userFilter;

    private List<String> actionFilter;

    private List<String> functionFilter;

    private List<String> hostFilter;
	
	public FunctionsModel(AuditUiManager auditManager, Period period, int activePage, String user, String action, String function, String host) {
	    super(activePage, PAGE_SIZE, null);
	    this.auditManager = auditManager;
	    this.period = period;
        this.period = period;
        this.user = user;
        this.action = action;
        this.function = function;
        this.host = host;
	    loadData(null);
	}

	@Override
	public int getTotalSize() {
        if (auditManager == null) {
            return 0;
        }
	    List<AuditEvent> list = getList();
	    if(list == null) {
	        return 0;
	    }
	    
        return list.size();
	}

	@Override
	protected List<AuditEvent> getPageData(QueryCondition conditions, AuditEvent filter) {
	    List<AuditEvent> list = getList();
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

    List<AuditEvent> getList() {
        if (events != null) {
            return events;
        }

        if (auditManager != null) {
            try {
                return events = auditManager.readFunctions(period, user, action, function, host);
            } catch (Exception e) {
                logger.error("", e);
            }
        }

        return events = Collections.emptyList();
    }

    public List<String> readFunctionsUserFilter() {
        if (userFilter == null) {
            Set<String> set = new TreeSet<>();
            for(AuditEvent event: getList()) {
                if (event != null) {
                    String value = event.getUser();
                    if (StringUtils.isNotBlank(value)) {
                        set.add(value);
                    }
                }
            }
            userFilter = new ArrayList<String>(set);
        }
        return userFilter;
    }

    public List<String> readFunctionsActionFilter() {
        if (actionFilter == null) {
            Set<String> set = new TreeSet<>();
            for(AuditEvent event: getList()) {
                if (event != null) {
                    String value = event.getAction();
                    if (StringUtils.isNotBlank(value)) {
                        set.add(value);
                    }
                }
            }
            actionFilter = new ArrayList<String>(set);
        }
        return actionFilter;
    }

    public List<String> readFunctionsFunctionFilter() {
        if (functionFilter == null) {
            Set<String> set = new TreeSet<>();
            for(AuditEvent event: getList()) {
                if (event != null) {
                    String value = event.getObject();
                    if (StringUtils.isNotBlank(value)) {
                        set.add(value);
                    }
                }
            }
            functionFilter = new ArrayList<String>(set);
        }
        return functionFilter;
    }

    public List<String> readFunctionsHostFilter() {
        if (hostFilter == null) {
            Set<String> set = new TreeSet<>();
            for(AuditEvent event: getList()) {
                if (event != null) {
                    String value = event.getHost();
                    if (StringUtils.isNotBlank(value)) {
                        set.add(value);
                    }
                }
            }
            hostFilter = new ArrayList<String>(set);
        }
        return hostFilter;
    }
    
    @Override
    public void setActivePage(int activePage) {
        super.setActivePage(activePage);
        loadData();
    }
}
